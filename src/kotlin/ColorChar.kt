package com.ericharm
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor

class ColorChar(character: Char, color: TextColor): TextCharacter(character, color, TextColor.ANSI.BLACK)
