package com.ericharm
import java.io.File
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyType

class Game(val level: Level): State {
    override fun render(screen: TerminalScreen) {
        screen.clear()
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
