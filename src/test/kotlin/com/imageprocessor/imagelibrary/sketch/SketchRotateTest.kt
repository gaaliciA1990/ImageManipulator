package com.imageprocessor.imagelibrary.sketch

import com.imageprocessor.imagelibrary.sketch.util.SketchIO
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertTrue

//https://github.com/gaaliciA1990/ImageManipulator/issues/1
class SketchRotateTest : SketchTestBase() {

    @Test
    @Ignore
    fun `Test rotate image`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        assertTrue {
            SketchIO.save(
                orig.rotate(45),
                destImagePath("hummingbird_rotate.png")
            )
        }
    }
}
