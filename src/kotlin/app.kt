package com.ericharm
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.terminal.DefaultTerminalFactory

fun main(args: Array<String>) {
    val terminal = DefaultTerminalFactory().createTerminal()
    val screen = TerminalScreen(terminal)
    screen.startScreen()
    screen.setCursorPosition(TerminalPosition(999, 999))
    val game = Game()

    while (true) {
        game.render(screen)
        game.handleInput(terminal.readInput().getKeyType())
        game.update()
    }
}
