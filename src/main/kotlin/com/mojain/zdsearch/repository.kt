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
        val content = this.javaClass.getResource(resource)
        tree = when (content) {
            null -> JsonArray()
            else -> parse(resource) as JsonArray<JsonObject>
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

    private fun parse(path: String): Any? {
        return this.javaClass.getResourceAsStream(path)?.let {inputStream ->
            return Parser().parse(inputStream)
        }
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
