package com.imageprocessor.imagelibrary.sketch.transformer

import java.awt.image.BufferedImage

interface ImageTransformer {

    fun transform(image: BufferedImage): BufferedImage
}
