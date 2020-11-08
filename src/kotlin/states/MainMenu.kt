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
        ScreenPosition.updateOffsetsForSize(TerminalSize(20, 4))
    }

    enum class MenuOption {
        PLAY, INSTRUCTIONS
    }

    val menuOptions = mapOf(
        MenuOption.PLAY to Point(0, 1),
        MenuOption.INSTRUCTIONS to Point(0, 3)
    )

    var selectedOption = MenuOption.PLAY

    fun drawTitle(graphics: TextGraphics) {
        graphics.setForegroundColor(TextColor.ANSI.GREEN)
        graphics.putString(ScreenPosition.offsetX, ScreenPosition.offsetY - 4, "Sokoban")
        graphics.putString(ScreenPosition.offsetX, ScreenPosition.offsetY - 3, "-------")
    }

    fun drawOptions(graphics: TextGraphics) {
        graphics.setForegroundColor(TextColor.ANSI.DEFAULT)
        menuOptions.forEach { text, point ->
            graphics.putString(point.x + ScreenPosition.offsetX, point.y + ScreenPosition.offsetY, text.toString())
        }
    }

    fun drawCursor(screen: TerminalScreen) {
        val point = menuOptions[selectedOption]
        if (point != null) {
            val cursorX = point.x + ScreenPosition.offsetX - 2
            val cursorY = point.y + ScreenPosition.offsetY
         screen.setCursorPosition(TerminalPosition(cursorX, cursorY))
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
        if (key == KeyType.ArrowDown) {
            val ordinal = selectedOption.ordinal
            if (ordinal < MenuOption.values().size - 1) selectedOption = MenuOption.values()[ordinal + 1]
        }
        if (key == KeyType.ArrowUp) {
            val ordinal = selectedOption.ordinal
            if (ordinal > 0) selectedOption = MenuOption.values()[ordinal - 1]
        }
        if (key == KeyType.Enter) {
            if (selectedOption == MenuOption.PLAY) App.swapCurrentState(ChooseLevel())
            if (selectedOption == MenuOption.INSTRUCTIONS) App.swapCurrentState(Instructions())
        }
    }

    override fun update() {}
}
