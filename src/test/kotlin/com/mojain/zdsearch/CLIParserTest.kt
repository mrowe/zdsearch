package com.mojain.zdsearch

import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertEquals

class CLIParserTest {

    @Test fun `should parse help command`() {
        assertTrue(CommandParser().parse("help") is Help)
    }

    @Test fun `should parse quit command`() {
        assertTrue(CommandParser().parse("quit") is Quit)
    }

    @Test fun `should parse fields command`() {
        assertTrue(CommandParser().parse("fields") is Fields)
    }

    @Test fun `should return Help command if input unparsable`() {
        assertTrue(CommandParser().parse("foobar") is Help)
        assertTrue(CommandParser().parse("") is Help)
    }

    @Test fun `should parse a search command`() {
        val command = CommandParser().parse("search")
        assertTrue(command is Search)
    }

    @Test fun `should parse a search command's arguments`() {
        val search = CommandParser().parse("search Tickets subject Hungary") as Search
        assertEquals("Tickets", search.repository)
        assertEquals("subject", search.field)
        assertEquals("Hungary", search.value)
    }

    @Test fun `should parse a search command with quotes around value`() {
        val search = CommandParser().parse("search Tickets subject \"A Catastrophe in Hungary\"") as Search
        assertEquals("A Catastrophe in Hungary", search.value)
    }

    @Test fun `should parse a search command with an empty quoted value`() {
        val search = CommandParser().parse("search Tickets description \"\"") as Search
        assertEquals("", search.value)
    }
}