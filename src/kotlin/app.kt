package com.ericharm
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory

fun main(args: Array<String>) {
    val terminal = DefaultTerminalFactory().createTerminal()
    val screen = TerminalScreen(terminal)
    screen.startScreen()

    val hero = Hero(Pair(10,10))
    val game = Game()

    while (true) {
        game.render(screen, hero)
        val key = game.handleInput(terminal)
        game.update(key, hero)
    }
}
