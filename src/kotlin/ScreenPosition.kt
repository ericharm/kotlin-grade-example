package com.ericharm
import com.googlecode.lanterna.TerminalSize

object ScreenPosition {
    var terminalSize = TerminalSize(0, 0)
    var offsetX = 0
    var offsetY = 0

    fun updateOffsetsForSize(size: TerminalSize) {
        offsetX = (terminalSize.getColumns() / 2) - (size.getColumns() / 2) - 1
        offsetY = (terminalSize.getRows() / 2) - (size.getRows() / 2) - 1
    }
}
