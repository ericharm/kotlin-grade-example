package com.ericharm

fun List<String>.eachLineEachChar(action: (char: Char, position: Point) -> Unit) {
    for (row in 0..this.size - 1) {
        for (column in 0..this[row].length - 1) {
            val char = this[row][column]
            val position = Point(column, row)
            action(char, position)
        }
    }
}
