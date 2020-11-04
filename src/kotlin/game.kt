package com.ericharm
import java.io.File
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyType

class Game() {
    val levelWidth = 50
    val levelHeight = 20
    val descriptor = File("./data/1a.lvl")
    val level = Level(levelWidth, levelHeight, descriptor)

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

    fun render(screen: TerminalScreen) {
        screen.clear()
        val graphics = screen.newTextGraphics()
        graphics.drawRectangle(TerminalPosition(0, 0), TerminalSize(levelWidth, levelHeight), '-', '|')
        level.render(graphics)
        screen.refresh()
    }

    fun handleInput(key: KeyType) {
        level.handleInput(key)
    }

    fun update() {
        level.update()
    }
}
