package com.mojain.zdsearch

import org.junit.Assert.assertTrue
import org.junit.Test

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

}