package com.img_processor.imagelibrary.sketch.filter

interface PixelFilter : ImageFilter {

    fun apply(rgb: Int): Int
}
