package com.ericharm
import java.io.File
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.TextColor

class MainMenu(): State {
    init {
        ScreenPosition.updateOffsetsForSize(TerminalSize(15, 4))
    }

    enum class MenuOption { PLAY, INSTRUCTIONS }

    val menuOptions = mapOf(MenuOption.PLAY to Point(0, 1), MenuOption.INSTRUCTIONS to Point(0, 3))

    var selectedOption = MenuOption.PLAY

    fun drawTitle(graphics: TextGraphics) {
        graphics.setForegroundColor(TextColor.ANSI.GREEN)
        graphics.putString(ScreenPosition.offsetX, ScreenPosition.offsetY - 3, "Sokoban")
        graphics.putString(ScreenPosition.offsetX, ScreenPosition.offsetY - 2, "-------")
    }

    fun drawOptions(graphics: TextGraphics) {
        graphics.setForegroundColor(TextColor.ANSI.DEFAULT)
        menuOptions.forEach { text, point ->
            val x = point.x + ScreenPosition.offsetX
            val y = point.y + ScreenPosition.offsetY
            graphics.putString(x, y, text.toString())
        }
    }

    fun drawCursor(screen: TerminalScreen) {
        val point = menuOptions[selectedOption]
        if (point != null) {
            val x = point.x + ScreenPosition.offsetX - 2
            val y = point.y + ScreenPosition.offsetY
            screen.setCursorPosition(TerminalPosition(x, y))
        }
    }

    override fun render(screen: TerminalScreen) {
        screen.clear()
        val graphics = screen.newTextGraphics()
        drawTitle(graphics)
        drawOptions(graphics)
        drawCursor(screen)
        screen.refresh()
    }

    override fun handleInput(key: KeyType) {
        val ordinal = selectedOption.ordinal
        when (key) {
            KeyType.ArrowUp -> if (ordinal > 0) selectedOption = MenuOption.values()[ordinal - 1]
            KeyType.ArrowDown -> {
                if (ordinal < MenuOption.values().size - 1) selectedOption = MenuOption.values()[ordinal + 1]
            }
            KeyType.Enter -> {
                if (selectedOption == MenuOption.PLAY) App.swapCurrentState(ChooseLevel())
                if (selectedOption == MenuOption.INSTRUCTIONS) App.swapCurrentState(Instructions())
            }
            else -> {}
        }
    }

    override fun update() {}
}
