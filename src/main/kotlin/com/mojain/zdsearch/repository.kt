package com.mojain.zdsearch

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser

/**
 * A repository of data. <name> is assumed to be the base name
 * of a json file that is loadable as a system resource, and has
 * the extension ".json".
 */
class Repository(resource: String) {
    private val tree: JsonArray<JsonObject>
    val name: String = resource

    init {
        val path = "/$resource.json"
        tree = when (javaClass.getResource(path)) {
            null -> JsonArray()
            else -> this.javaClass.getResourceAsStream(path)?.let {
                stream -> Parser().parse(stream)
            } as JsonArray<JsonObject>
        }
    }

    fun fields(): Sequence<String> {
        return when {
            tree.none() -> emptySequence()
            // assumes that the first record in the file is representative
            else -> tree.first().keys.asSequence()
        }
    }

    fun search(field: String, value: String): List<String> {
        return tree
                .filter { it.getOrDefault(field, "").toString() == value }
                .map { it.toJsonString(true) }
    }
}
