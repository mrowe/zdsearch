package com.mojain.zdsearch

fun main(args: Array<String>) {
    val cli = CLI()

    val repositories = arrayOf(
            Users("/users.json"),
            Organisations("/organizations.json"),
            Tickets("/tickets.json")
    )

    cli.welcome()
    var running = true
    while (running) {
        val command = cli.acceptCommand()
        when (command) {
            is Quit -> running = false
            is Help -> cli.welcome()
            is Fields -> repositories.forEach { cli.displayFields(it.name(), it.fields()) }
            is Search -> cli.displayResult(search(repositories, command))
        }
    }
    cli.goodbye()
}

fun search(repositories: Array<Repository>, command: Search): List<String> {
    val repo = repositories.firstOrNull { it.name() == command.repository }
    return when (repo) {
        null -> emptyList()
        else -> repo.search(command.field, command.value)
    }
}
