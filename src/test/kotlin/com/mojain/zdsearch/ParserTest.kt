package com.mojain.zdsearch

import org.junit.Assert.assertTrue
import org.junit.Test

class ParserTest {

    @Test fun `should parse help command`() {
        assertTrue(Parser().parse("help") is Help)
    }

    @Test fun `should parse quit command`() {
        assertTrue(Parser().parse("quit") is Quit)
    }

    @Test fun `should parse fields command`() {
        assertTrue(Parser().parse("fields") is Fields)
    }

    @Test fun `should return Help command if input unparsable`() {
        assertTrue(Parser().parse("foobar") is Help)
        assertTrue(Parser().parse("") is Help)
    }

}