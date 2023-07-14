package com.imageprocessor.imagelibrary.sketch

import com.imageprocessor.imagelibrary.sketch.geom.Pixel
import com.imageprocessor.imagelibrary.sketch.geom.Position
import com.imageprocessor.imagelibrary.sketch.util.SketchIO
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import java.awt.Color
import kotlin.test.assertTrue

//https://github.com/gaaliciA1990/ImageManipulator/issues/1
class SketchSetPixelsTest : SketchTestBase() {

    @Test
    @Ignore
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
