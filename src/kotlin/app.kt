package com.ericharm
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory

object ScreenPosition {
    var terminalSize = TerminalSize(0, 0)
    var offsetX = 0
    var offsetY = 0

    fun updateOffsetsForSize(size: TerminalSize) {
        offsetX = (terminalSize.getColumns() / 2) - (size.getColumns() / 2) - 1
        offsetY = (terminalSize.getRows() / 2) - (size.getRows() / 2) - 1
    }
}

fun main(args: Array<String>) {
    val terminal = DefaultTerminalFactory().createTerminal()
    val screen = TerminalScreen(terminal)
    val size = screen.getTerminal().getTerminalSize()
    ScreenPosition.terminalSize = size
    screen.startScreen()
    screen.setCursorPosition(TerminalPosition(999, 999))
    val game = Game()

    while (true) {
        game.render(screen)
        game.handleInput(terminal.readInput().getKeyType())
        game.update()
    }
}
