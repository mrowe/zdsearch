package com.mojain.zdsearch

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 * A Repository
 */
abstract class Repository(resource: String) {
    private val data: String
    private val mapper: ObjectMapper

    init {
        mapper = jacksonObjectMapper()
    }

    init {
        val content = this.javaClass.getResource(resource)
        data = when (content) {
            null -> ""
            else -> content.readText()
        }
    }

    open fun name(): String {
        return javaClass.toString()
    }

    open fun fields(): Sequence<String> {
        val tree = mapper.readTree(data)
        return when {
            tree.none() -> emptySequence()
            // assumes that the first record in the file is representative
            else -> tree.first().fields().asSequence().map { entry -> entry.key }
        }
    }

    fun getById(id: String): String {
        return ""
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
