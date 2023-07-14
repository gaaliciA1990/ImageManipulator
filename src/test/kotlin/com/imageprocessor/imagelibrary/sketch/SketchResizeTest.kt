package com.imageprocessor.imagelibrary.sketch

import com.imageprocessor.imagelibrary.sketch.util.SketchIO
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertTrue

// https://github.com/gaaliciA1990/ImageManipulator/issues/1
class SketchResizeTest : SketchTestBase() {

    @Test
    @Ignore
    fun `Test image resize`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        assertTrue {
            SketchIO.save(
                orig.resize(0.5f, 0.5f),
                destImagePath("hummingbird_resized.jpg")
            )
        }
    }
}
