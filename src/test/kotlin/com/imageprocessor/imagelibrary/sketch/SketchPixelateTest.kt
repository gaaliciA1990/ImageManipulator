package com.imageprocessor.imagelibrary.sketch

import com.imageprocessor.imagelibrary.sketch.geom.Rectangle
import com.imageprocessor.imagelibrary.sketch.util.SketchIO
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertTrue

//https://github.com/gaaliciA1990/ImageManipulator/issues/1
class SketchPixelateTest : SketchTestBase() {

    @Test
    @Ignore
    fun `Test pixelate entire image`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        assertTrue {
            SketchIO.save(
                orig.pixelate(10),
                destImagePath("hummingbird_pixelate_full.jpg")
            )
        }
    }

    @Test
    @Ignore
    fun `Test pixelate image part`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        assertTrue {
            SketchIO.save(
                orig.pixelate(Rectangle(0, 0, 100, 50), 10),
                destImagePath("hummingbird_pixelate_partial.jpg")
            )
        }
    }
}
