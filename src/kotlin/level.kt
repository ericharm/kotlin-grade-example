package com.ericharm
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.input.KeyType

class Level () {
    object boulder : Entity(Pair(12, 13)) {
        override val character = '0'
    }

    object hero : Entity(Pair(10, 10)) {
        override val character = '@'
    }

    fun render(graphics: TextGraphics) {
        hero.render(graphics)
        boulder.render(graphics)
    }

    fun handleInput(key: KeyType) {
        if (key == KeyType.ArrowDown) hero.move(Pair(0, 1))
        if (key == KeyType.ArrowUp) hero.move(Pair(0, -1))
        if (key == KeyType.ArrowLeft) hero.move(Pair(-1, 0))
        if (key == KeyType.ArrowRight) hero.move(Pair(1, 0))
    }

    fun update()
}
