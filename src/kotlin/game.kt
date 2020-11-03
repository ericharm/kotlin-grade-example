package com.ericharm
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.Terminal
import com.googlecode.lanterna.input.KeyType

fun TextGraphics.drawRectangle(position: TerminalPosition, size: TerminalSize, horizontalChar: Char,verticalChar: Char) {
    val width = size.columns
    val height = size.rows
    val x = position.column
    val y = position.row
    val bottom = TerminalPosition(x, position.row + size.rows - 1)
    drawRectangle(position, size, verticalChar)
    putString(position, horizontalChar.toString().repeat(width))
    putString(bottom, horizontalChar.toString().repeat(width))
}

class Game() {
    fun render(screen: TerminalScreen, hero: Hero) {
        val tg = screen.newTextGraphics()
        screen.clear()
        tg.drawRectangle(TerminalPosition(0, 0), TerminalSize(22, 18), '*', '*')
        tg.putString(hero.x, hero.y, hero.character)
        screen.refresh()
    }

    fun handleInput(terminal: Terminal) = terminal.readInput().getKeyType()

    fun update(key: KeyType, hero: Hero) {
        if (key == KeyType.ArrowDown) hero.move(Pair(0, 1))
        if (key == KeyType.ArrowUp) hero.move(Pair(0, -1))
        if (key == KeyType.ArrowLeft) hero.move(Pair(-1, 0))
        if (key == KeyType.ArrowRight) hero.move(Pair(1, 0))
    }
}
