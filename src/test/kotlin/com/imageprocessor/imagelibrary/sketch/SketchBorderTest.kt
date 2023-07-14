package com.imageprocessor.imagelibrary.sketch

import com.imageprocessor.imagelibrary.sketch.util.SketchIO
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import java.awt.Color
import kotlin.test.assertTrue

//https://github.com/gaaliciA1990/ImageManipulator/issues/1

class SketchBorderTest : SketchTestBase() {

    @Test
    @Ignore
    fun `Test image border`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        assertTrue {
            SketchIO.save(
                orig.border(3, Color.red),
                destImagePath("hummingbird_border.png")
            )
        }
    }

    @Test
    @Ignore
    fun `Test image border with radius`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        assertTrue {
            SketchIO.save(
                orig.borderRadius(30).border(3, Color.red).borderRadius(30),
                destImagePath("hummingbird_border_with_radius.png")
            )
        }
    }

    @Test
    @Ignore
    fun `Test image radius`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        assertTrue {
            SketchIO.save(
                orig.borderRadius(30),
                destImagePath("hummingbird_border_radius.png")
            )
        }
    }
}
