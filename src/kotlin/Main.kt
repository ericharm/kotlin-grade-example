package com.ericharm
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.input.KeyType

class Hero(var location: Pair<Int, Int>) {
    val x: Int
        get() = location.first
    val y: Int
        get() = location.second
    val character = "@"

    fun move(byVector: Pair<Int, Int>) {
        location = Pair(x + byVector.first, y + byVector.second)
    }
}

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

fun main(args: Array<String>) {
    val terminal = DefaultTerminalFactory().createTerminal()
    val screen = TerminalScreen(terminal)
    val tg = screen.newTextGraphics()
    screen.startScreen()

    val hero = Hero(Pair(10,10))

    while (true) {
        // render
        screen.clear()
        tg.drawRectangle(TerminalPosition(0, 0), TerminalSize(22, 18), '-', '|')
        tg.putString(hero.x, hero.y, hero.character)
        screen.refresh()
        // handle input
        val key = terminal.readInput().getKeyType()
        // update
        if (key == KeyType.ArrowDown) hero.move(Pair(0, 1))
        if (key == KeyType.ArrowUp) hero.move(Pair(0, -1))
        if (key == KeyType.ArrowLeft) hero.move(Pair(-1, 0))
        if (key == KeyType.ArrowRight) hero.move(Pair(1, 0))
    }
}
