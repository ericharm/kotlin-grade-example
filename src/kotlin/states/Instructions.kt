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

    fun formatInstructions(graphics: TextGraphics) {
        val rows = File("./data/instructions.txt").readText().split("\n")
        for (y in 0..rows.size - 1) {
            for (x in 0..rows[y].length - 1) {
                val charX = ScreenPosition.offsetX + x
                val charY = ScreenPosition.offsetY + y
                if (rows[y][x] == '0') graphics.setCharacter(charX, charY, ColorChar('0', Boulder.color))
                else if (rows[y][x] == '@') graphics.setCharacter(charX, charY, ColorChar('@', Hero.color))
                else if (rows[y][x] == '^') graphics.setCharacter(charX, charY, ColorChar('^', Pit.color))
                else graphics.setCharacter(charX, charY, ColorChar(rows[y][x], TextColor.ANSI.DEFAULT))
            }
        }
        val width = if (rows.size > 0) rows[0].length else 0
        ScreenPosition.updateOffsetsForSize(TerminalSize(width, rows.size))
    }

    override fun render(screen: TerminalScreen) {
        screen.clear()
        screen.setCursorPosition(TerminalPosition(0, 0))
        val graphics = screen.newTextGraphics()
        formatInstructions(graphics)
        screen.refresh()
    }

    override fun handleInput(key: KeyType) {
        if (key == KeyType.Escape || key == KeyType.Enter) App.swapCurrentState(MainMenu())
    }

    override fun update() {
        // level.update()
    }
}
