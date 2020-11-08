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
        rows.eachLineEachChar { char: Char, position: Point ->
            val x = ScreenPosition.offsetX + position.x
            val y = ScreenPosition.offsetY + position.y
            when (char) {
                '0' -> graphics.setCharacter(x, y, ColorChar('0', Boulder.color))
                '@' -> graphics.setCharacter(x, y, ColorChar('@', Hero.color))
                '^' -> graphics.setCharacter(x, y, ColorChar('^', Pit.color))
                'X' -> graphics.setCharacter(x, y, ColorChar('X', Exit.color))
                else -> graphics.setCharacter(x, y, ColorChar(char, Entity.color))
            }
        }
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

    override fun update() {}
}
