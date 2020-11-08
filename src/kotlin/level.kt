package com.ericharm
import java.io.File
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.input.KeyType

class Level (val width: Int, val height: Int) {
    companion object {
        fun fromDescriptor(descriptor: String): Level {
            val rows = descriptor.split("\n").filter { it.length > 0 }
            val width = if (rows.size > 0) rows[0].length else 0
            val level = Level(width, rows.size)
            level.generate(descriptor)
            return level
        }

        fun fromDescriptor(descriptor: File): Level {
            val contents = descriptor.readText()
            return Level.fromDescriptor(contents)
        }
    }

    object hero : Entity(Point(0, 0)) {
        override val character = ColorChar('@', TextColor.ANSI.MAGENTA)

        override open fun onCollidesWith(entities: List<Entity>, level: Level, vector: Point): Boolean {
            entities.forEach {
                if (it is Boulder && it.moveThroughLevel(level, vector)) {
                    move(vector.x, vector.y)
                    return true
                }
            }
            return false
        }
    }

    var entities: List<Entity> = listOf(hero)

    fun generate(descriptor: String) {
        val rows = descriptor.split("\n").filter { it.length > 0 }
        for (y: Int in 0..rows.size - 1) {
            for (x: Int in 0..rows[y].length - 1) {
                if (rows[y][x] == '0') entities += Boulder(Point(x, y))
                if (rows[y][x] == '#') entities += Wall(Point(x, y))
                if (rows[y][x] == '^') entities += Pit(Point(x, y))
                if (rows[y][x] == '@') hero.moveTo(x, y)
            }
        }
        val width = if (rows.size > 0) rows[0].length else 0
        ScreenPosition.updateOffsetsForSize(TerminalSize(width, rows.size))
    }

    fun pluckEntity(entity: Entity) {
        entities = entities.filter { it != entity }
    }

    fun render(graphics: TextGraphics) {
        entities.forEach { it.render(graphics) }
    }

    fun handleInput(key: KeyType) {
        val direction = hashMapOf(
            KeyType.ArrowDown to Point(0, 1), KeyType.ArrowUp to Point(0, -1),
            KeyType.ArrowLeft to Point(-1, 0), KeyType.ArrowRight to Point(1, 0)
        )[key]
        if (direction != null) hero.moveThroughLevel(this, Point(direction.x, direction.y))
    }

    fun update() {
        val remainingPits = entities.filter { it is Pit }
        if (remainingPits.size == 0) App.swapCurrentState(Victory())
    }
}
