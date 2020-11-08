package com.ericharm
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyType
import kotlin.random.*

class Victory(): State {

    val victoryMessages = listOf(
        "Nice.", "Well done.", "Good work.", "You did it."
    )

    val message = victoryMessages[Random.nextInt(0, victoryMessages.size - 1)]

    override fun render(screen: TerminalScreen) {
        val width = message.length + 4
        ScreenPosition.updateOffsetsForSize(TerminalSize(width, 5))
        screen.clear()
        val graphics = screen.newTextGraphics()
        graphics.drawRectangle(
            TerminalPosition(ScreenPosition.offsetX, ScreenPosition.offsetY),
            TerminalSize(width, 5),
            '+'
        )
        graphics.putString(ScreenPosition.offsetX + 2, ScreenPosition.offsetY + 2, message)
        screen.refresh()
    }

    override fun handleInput(key: KeyType) {
        if (key == KeyType.Escape || key == KeyType.Enter) App.swapCurrentState(MainMenu())
    }

    override fun update() {}
}
