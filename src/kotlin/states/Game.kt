package com.ericharm
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.input.KeyType

class Game(): State {
    lateinit var level: Level

    override fun render(screen: TerminalScreen) {
        screen.clear()
        screen.setCursorPosition(TerminalPosition(0, 0))
        val graphics = screen.newTextGraphics()
        level.render(graphics)
        screen.refresh()
    }

    override fun handleInput(key: KeyType) {
        if (key == KeyType.Escape) App.swapCurrentState(MainMenu())
        else level.handleInput(key)
    }

    override fun update() {
        level.update()
    }
}
