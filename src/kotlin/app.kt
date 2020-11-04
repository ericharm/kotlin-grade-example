package com.ericharm
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory

data class Point(val x: Int, val y: Int)

fun main(args: Array<String>) {
    val terminal = DefaultTerminalFactory().createTerminal()
    val screen = TerminalScreen(terminal)
    screen.startScreen()
    val game = Game()

    while (true) {
        game.render(screen)
        game.handleInput(terminal.readInput().getKeyType())
        game.update()
    }
}
