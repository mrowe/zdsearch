package com.mojain.zdsearch

fun main(args: Array<String>) {
    val cli = CLI()

    val repositories = arrayOf(Users("/users.json"), Organisations("/organizations.json"), Tickets("/tickets.json"))

    cli.welcome()
    var running = true
    while (running) {
        when (cli.acceptCommand()) {
            is Quit -> running = false
            is Help -> cli.welcome()
            is Fields -> repositories.forEach { r -> cli.displayFields(r.name(), r.fields()) }
        }
    }
    cli.goodbye()
}
