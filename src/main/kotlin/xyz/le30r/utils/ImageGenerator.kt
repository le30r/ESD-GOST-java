package xyz.le30r.utils

import java.awt.Color
import java.awt.image.BufferedImage
import java.math.BigInteger

fun generateImage(s1: BigInteger, s2: BigInteger, message: String): BufferedImage {


    val hash = Hasher.hash(s1 * s2, message)

    // Создание пустой картинки с заданными размерами
    val blocks = Array(4) { hash.copyOfRange(it * 4, (it + 1) * 4) }
    // Convert each block to Int
    val blockInts = blocks.map { bytes -> bytes.fold(0) { acc, byte -> (acc shl 8) or (byte.toInt() and 0xff) } }

    val image = BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB)

    // Draw rectangles
    blockInts.forEachIndexed { i, value ->
        val x = (value ushr 16) and 0xff
        val y = (value and 0xff)
        val width = ((value ushr 8) and 0xff) / 2
        val height = ((value ushr 24) and 0xff) / 2
        val color = Color.getHSBColor(i.toFloat() / 4, 1f, 0.8f)
        val graphics = image.createGraphics()
        graphics.color = color
        graphics.fillRect(x, y, width, height)
        graphics.dispose()
    }

    // Draw circles
    hash.forEachIndexed { i, byte ->
        val size = byte.toInt() and 0xf
        val color = Color(byte.toInt() and 0xf0 or 0xff, byte.toInt() and 0xf or 0xff, i * 17 and 0xff)
        val x = ((i ushr 2) * 17 + 8) % 256
        val y = ((i ushr 2) * 3 + 13) % 256
        val graphics = image.createGraphics()
        graphics.color = color
        graphics.fillOval(x - size / 2, y - size / 2, size, size)
        graphics.dispose()
    }

    return image
}