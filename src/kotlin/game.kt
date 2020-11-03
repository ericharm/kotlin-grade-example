package com.ericharm
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.Terminal
import com.googlecode.lanterna.input.KeyType

class Game() {
    object boulder : Entity(Pair(12, 13)) {
        override val character = '0'
    }

    object hero : Entity(Pair(10, 10)) {
        override val character = '@'
    }

    fun TextGraphics.drawRectangle(position: TerminalPosition, size: TerminalSize, horizontalChar: Char,verticalChar: Char) {
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
        graphics.drawRectangle(TerminalPosition(0, 0), TerminalSize(22, 18), '*', '*')
        hero.render(graphics)
        boulder.render(graphics)
        screen.refresh()
    }

    fun handleInput(terminal: Terminal) = terminal.readInput().getKeyType()

    fun update(key: KeyType) {
        if (key == KeyType.ArrowDown) hero.move(Pair(0, 1))
        if (key == KeyType.ArrowUp) hero.move(Pair(0, -1))
        if (key == KeyType.ArrowLeft) hero.move(Pair(-1, 0))
        if (key == KeyType.ArrowRight) hero.move(Pair(1, 0))
    }
}
