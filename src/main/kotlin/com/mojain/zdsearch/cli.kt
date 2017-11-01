package com.mojain.zdsearch

class CLI {

    fun welcome() {
        println("""
Welcome to Zendesk Search!
Type 'quit' to exit at any time.

Please enter one of the following commands: search, fields, help, quit



You can search for data by entering commands in the form:

> search

Type 'help' to repeat these instructions.
    """)
    }

    fun acceptCommand(): Command {
        print("> ")
        val input = readLine()
        return Parser().parse(input ?: "")
    }

    fun goodbye() {
        println("Thanks for using zd-search!")
    }

    fun fields(repo: String, fields: Sequence<String>) {
        println("========== Searchable fields in $repo ==========")
        println(fields.joinToString(", "))
    }
}

class Parser {
    private operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)

    fun parse(s: String): Command {
        return when (s) {
            in "^quit".toRegex() -> Quit()
            in "^help".toRegex() -> Help()
            in "^fields".toRegex() -> Fields()
            else -> Help()
        }
    }
}

interface Command

class Help : Command
class Quit : Command
class Fields : Command