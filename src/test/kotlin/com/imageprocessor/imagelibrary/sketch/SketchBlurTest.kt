package com.imageprocessor.imagelibrary.sketch

import com.imageprocessor.imagelibrary.sketch.geom.Rectangle
import com.imageprocessor.imagelibrary.sketch.util.SketchIO
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertTrue

//https://github.com/gaaliciA1990/ImageManipulator/issues/1
class SketchBlurTest : SketchTestBase() {

    @Test
    @Ignore
    fun `Test blur entire image`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        assertTrue {
            SketchIO.save(
                orig.blur(10),
                destImagePath("hummingbird_blur_full.jpg")
            )
        }
    }

    @Test
    @Ignore
    fun `Test blur image part`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        assertTrue {
            SketchIO.save(
                orig.blur(Rectangle(10, 10, 50, 50), 10),
                destImagePath("hummingbird_blur_partial.jpg")
            )
        }
    }
}
