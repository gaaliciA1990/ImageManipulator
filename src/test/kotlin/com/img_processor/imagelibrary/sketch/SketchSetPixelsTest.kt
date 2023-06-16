package com.img_processor.imagelibrary.sketch

import com.img_processor.imagelibrary.sketch.geom.Pixel
import com.img_processor.imagelibrary.sketch.geom.Position
import com.img_processor.imagelibrary.sketch.util.SketchIO
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.awt.Color
import kotlin.test.assertTrue

class SketchSetPixelsTest : SketchTestBase() {

    @Test
    fun `Test set pixels`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        val width = orig.asBufferedImage().width
        val pixels = (0 until width).map { Pixel(Position(it, 10), Color.BLACK) }.toList()
        assertTrue {
            SketchIO.save(
                orig.setPixels(pixels),
                destImagePath("hummingbird_set_pixels.jpg")
            )
        }
    }
}
