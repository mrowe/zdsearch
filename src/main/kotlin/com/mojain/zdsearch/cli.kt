package com.mojain.zdsearch

class CLI {

    fun welcome() {
        println("""
Welcome to Zendesk Search!
Type 'quit' to exit at any time.

Please enter one of the following commands: search, fields, help, quit


You can search for data by entering commands in the form:

    > search <repository> <fieldname> <value>

e.g.
    > search users alias "Miss Joni"

Note that if <value> contains spaces it must be enclosed in double quotes.

Type 'help' to repeat these instructions.
    """)
    }

    fun acceptCommand(): Command {
        print("> ")
        val input = readLine()
        return CommandParser().parse(input ?: "")
    }

    fun goodbye() {
        println("Thanks for using zd-search!")
    }

    fun displayFields(repo: String, fields: Sequence<String>) {
        println("========== Searchable fields in $repo ==========")
        println(fields.joinToString(", "))
    }

    fun displayResult(results: List<String>) {
        println("========== Search results ==========")
        when (results) {
            emptyList<List<String>>() -> println("No results")
            else -> results.forEach(::println)
        }
    }
}

class CommandParser {
    private operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)

    fun parse(s: String): Command {
        return when (s) {
            in "^quit".toRegex() -> Quit()
            in "^help".toRegex() -> Help()
            in "^fields".toRegex() -> Fields()
            in "^search.*".toRegex() -> parseSearch(s)
            else -> Help()
        }
    }

    private fun parseSearch(s: String): Search {
        val match = "^search (\\w+) (\\w+) \"?([^\"]*)\"?$".toRegex().matchEntire(s)
        return when (match) {
            null -> Search("","","")
            else -> {
                val (repository, field, value) = match.destructured
                Search(repository, field, value)
            }
        }
    }
}

interface Command

class Help : Command
class Quit : Command
class Fields : Command
class Search(val repository: String, val field: String, val value: String) : Command