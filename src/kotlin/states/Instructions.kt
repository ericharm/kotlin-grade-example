package com.ericharm
import java.io.File
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.TextColor

class Instructions(): State {
    init {
        ScreenPosition.updateOffsetsForSize(TerminalSize(40, 6))
    }

    override fun render(screen: TerminalScreen) {
        screen.clear()
        val graphics = screen.newTextGraphics()
        val instructions = listOf(
            "- Use the arrow keys to move the hero “@“",
            "- Push the boulders “0” into the pits ^",
            "- Escape to return to the main menu"
        )
        instructions.forEachIndexed { index, text ->
            graphics.putString(ScreenPosition.offsetX, ScreenPosition.offsetY + index * 2, text)
        }
        screen.refresh()
    }

    override fun handleInput(key: KeyType) {
        if (key == KeyType.Escape) App.swapCurrentState(MainMenu())
    }

    override fun update() {
        // level.update()
    }
}
