package com.ericharm
import java.io.File
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyType

class Game(): State {
    val width = 50
    val height = 20
    val descriptor = File("./data/1a.lvl")
    val level = Level.fromDescriptor(descriptor)

    fun TextGraphics.drawRectangle(
        position: TerminalPosition, size: TerminalSize, horizontalChar: Char, verticalChar: Char
    ) {
        val width = size.columns
        val height = size.rows
        val x = position.column
        val y = position.row
        val bottom = TerminalPosition(x, y + height - 1)
        drawRectangle(position, size, verticalChar)
        putString(position, horizontalChar.toString().repeat(width))
        putString(bottom, horizontalChar.toString().repeat(width))
    }

    override fun render(screen: TerminalScreen) {
        screen.clear()
        val graphics = screen.newTextGraphics()
        val size = screen.getTerminal().getTerminalSize()
        graphics.drawRectangle(TerminalPosition(0, 0), size, '-', '|')
        level.render(graphics)
        screen.refresh()
    }

    override fun handleInput(key: KeyType) {
        level.handleInput(key)
    }

    override fun update() {
        level.update()
    }
}
