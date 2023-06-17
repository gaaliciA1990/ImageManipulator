package com.imageprocessor.imagelibrary.sketch

import com.imageprocessor.imagelibrary.sketch.util.SketchIO
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

class SketchResizeTest : SketchTestBase() {

    @Test
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
