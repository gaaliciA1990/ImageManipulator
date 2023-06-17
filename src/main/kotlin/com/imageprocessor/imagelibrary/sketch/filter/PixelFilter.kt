package com.imageprocessor.imagelibrary.sketch.filter

interface PixelFilter : ImageFilter {

    fun apply(rgb: Int): Int
}
