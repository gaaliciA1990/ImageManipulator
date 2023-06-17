package com.imageprocessor.imagelibrary.sketch.filter

interface RescaleOpFilter : ImageFilter {

    fun apply(builder: RescaleOpBuilder)
}
