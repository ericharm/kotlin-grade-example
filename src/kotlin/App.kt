package com.ericharm
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger
import java.util.Stack

class App {
    companion object {
        val states = Stack<State>()
        fun swapCurrentState(state: State) {
            states.pop()
            states.push(state)
        }
    }

    init {
        val factory = DefaultTerminalFactory()
        factory.setTerminalEmulatorTitle("Sokoban")
        factory.setTerminalEmulatorFrameAutoCloseTrigger(TerminalEmulatorAutoCloseTrigger.CloseOnExitPrivateMode)
        val terminal = factory.createTerminal()
        val screen = TerminalScreen(terminal)
        ScreenPosition.terminalSize = screen.getTerminal().getTerminalSize()
        screen.startScreen()
        states.push(MainMenu())

        while (true) {
            states.forEach { it.render(screen) }
            states.forEach { it.handleInput(terminal.readInput().getKeyType()) }
            states.forEach { it.update() }
        }
    }
}

fun main() {
    App()
}
