package com.ericharm
import com.googlecode.lanterna.graphics.TextGraphics

open class Entity(var location: Pair<Int, Int>) {
    val x: Int
        get() = location.first
    val y: Int
        get() = location.second
    open val character = '?'

    fun render(graphics: TextGraphics) {
        graphics.putString(x, y, character.toString())
    }

    fun move(byVector: Pair<Int, Int>) {
        location = Pair(x + byVector.first, y + byVector.second)
    }
}

