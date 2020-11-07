package com.ericharm
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.input.KeyType

interface State {
    fun render(screen: TerminalScreen)
    fun handleInput(key: KeyType)
    fun update()
}
