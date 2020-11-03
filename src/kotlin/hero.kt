package com.ericharm

class Hero(var location: Pair<Int, Int>) {
    val x: Int
        get() = location.first
    val y: Int
        get() = location.second
    val character = "@"

    fun move(byVector: Pair<Int, Int>) {
        location = Pair(x + byVector.first, y + byVector.second)
    }
}
