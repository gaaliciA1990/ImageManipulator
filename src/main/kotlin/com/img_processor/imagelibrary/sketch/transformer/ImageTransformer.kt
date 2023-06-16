package com.img_processor.imagelibrary.sketch.transformer

import java.awt.image.BufferedImage

interface ImageTransformer {

    fun transform(image: BufferedImage): BufferedImage
}
