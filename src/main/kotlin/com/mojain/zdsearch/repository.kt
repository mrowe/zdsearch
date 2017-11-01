package com.mojain.zdsearch

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 * A Repository
 */
abstract class Repository(resource: String) {
    private val data: String = this.javaClass.getResource(resource).readText()
    private val mapper : ObjectMapper = jacksonObjectMapper()

    open fun name(): String {
        return this.javaClass.toString()
    }
    open fun fields(): Sequence<String> {
        return deriveFields()
    }
    open fun getById(id: String): String {
        return ""
    }

    private fun deriveFields(): Sequence<String> {
        return mapper.readTree(data).first().fields().asSequence().map { entry -> entry.key }
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
