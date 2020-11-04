package com.ericharm

class Point(val x: Int, val y: Int) {
    fun inLevel(level: Level): Boolean {
        val inHorizontalBounds = x > 0 && x < level.width - 1
        val inVerticalBounds = y > 0 && y < level.height - 1
        return inHorizontalBounds && inVerticalBounds
    }
}
