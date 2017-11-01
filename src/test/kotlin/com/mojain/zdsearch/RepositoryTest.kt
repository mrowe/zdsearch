package com.mojain.zdsearch

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class RepositoryTest {
    private lateinit var users: Repository
    private lateinit var organisations: Repository
    private lateinit var tickets: Repository

    @Before fun setup() {
        users = Users("/users.json")
        organisations = Organisations("/organizations.json")
        tickets = Tickets("/tickets.json")
    }

    @Test fun `should return name of repository`() {
        assertEquals("Users", users.name())
        assertEquals("Organisations", organisations.name())
        assertEquals("Tickets", tickets.name())
    }

    @Test fun `users should have a name field`() {
        assertTrue(users.fields().contains("name"))
    }

    @Test fun `organisations should have an external_id field`() {
        assertTrue(organisations.fields().contains("external_id"))
    }

    @Test fun `tickets should have a subject field`() {
        assertTrue(tickets.fields().contains("subject"))
    }
}