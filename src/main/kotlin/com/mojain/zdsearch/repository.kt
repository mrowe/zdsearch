package com.mojain.zdsearch

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.beust.klaxon.string

/**
 * A Repository
 */
abstract class Repository(resource: String) {
    private val tree: JsonArray<JsonObject>
    init {
        tree = when (javaClass.getResource(resource)) {
            null -> JsonArray()
            else -> this.javaClass.getResourceAsStream(resource)?.let {
                stream -> Parser().parse(stream)
            } as JsonArray<JsonObject>
        }
    }

    open fun name(): String {
        return javaClass.toString()
    }

    open fun fields(): Sequence<String> {
        return when {
            tree.none() -> emptySequence()
            // assumes that the first record in the file is representative
            else -> tree.first().keys.asSequence()
        }
    }

    fun getById(id: String): String {
        return tree.first { it.string("_id") == id }.toJsonString()
    }

    fun search(field: String, value: String): List<String> {
        return tree.filter { it.string(field) == value }.map { it.toJsonString(true) }
    }
}

class Users(resource: String) : Repository(resource) {
    override fun name(): String { return "Users" }
}

class Organisations(resource: String) : Repository(resource) {
    override fun name(): String { return "Organisations" }
}

class Tickets(resource: String) : Repository(resource) {
    override fun name(): String { return "Tickets" }
}
