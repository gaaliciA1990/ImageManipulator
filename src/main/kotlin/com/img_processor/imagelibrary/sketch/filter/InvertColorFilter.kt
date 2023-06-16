package com.img_processor.imagelibrary.sketch.filter

class InvertColorFilter : PixelFilter {

    override fun apply(rgb: Int): Int {
        return rgb.inv()
    }
}
