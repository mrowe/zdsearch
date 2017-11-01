package com.mojain.zdsearch

/**
 * A Repository
 */
interface Repository {
    fun name(): String {
        return this.javaClass.toString()
    }
    fun fields(): Array<String> {
        return arrayOf("_id")
    }
    fun getById(id: String): String {
        return ""
    }
}

class Users : Repository {
    override fun name(): String { return "Users" }
    override fun fields(): Array<String> {
        return arrayOf("_id", "name", "phone")
    }
    override fun getById(id: String): String {
        return ""
    }
}

class Organisations : Repository {
    override fun name(): String { return "Organisations" }
}

class Tickets : Repository {
    override fun name(): String { return "Tickets" }
}
