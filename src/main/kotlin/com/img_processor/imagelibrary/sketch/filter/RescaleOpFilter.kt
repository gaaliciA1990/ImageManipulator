package com.img_processor.imagelibrary.sketch.filter

interface RescaleOpFilter : ImageFilter {

    fun apply(builder: RescaleOpBuilder)
}
