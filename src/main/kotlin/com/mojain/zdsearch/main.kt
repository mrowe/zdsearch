package com.mojain.zdsearch

fun main(args: Array<String>) {
    val cli = CLI()

    val repositories = arrayOf(Users(), Organisations(), Tickets())

    cli.welcome()
    var running = true
    while (running) {
        when (cli.acceptCommand()) {
            is Quit -> running = false
            is Help -> cli.welcome()
            is Fields -> repositories.map { r -> cli.fields(r.name(), r.fields()) }
        }
    }
    cli.goodbye()
}
