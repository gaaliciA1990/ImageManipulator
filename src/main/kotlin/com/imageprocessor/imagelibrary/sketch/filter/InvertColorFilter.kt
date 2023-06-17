package com.imageprocessor.imagelibrary.sketch.filter

class InvertColorFilter : PixelFilter {

    override fun apply(rgb: Int): Int {
        return rgb.inv()
    }
}
