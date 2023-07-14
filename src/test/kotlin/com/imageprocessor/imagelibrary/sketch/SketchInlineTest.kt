package com.imageprocessor.imagelibrary.sketch

import com.imageprocessor.imagelibrary.sketch.geom.Position
import com.imageprocessor.imagelibrary.sketch.util.SketchIO
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import java.awt.Color
import java.awt.Font
import kotlin.test.assertTrue

//https://github.com/gaaliciA1990/ImageManipulator/issues/1

class SketchInlineTest : SketchTestBase() {

    @Test
    @Ignore
    fun `Test inline image`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        assertTrue {
            SketchIO.save(
                orig.inline(Position(10, 10), SketchImage(rectangleImage())),
                destImagePath("hummingbird_inline_image.jpg")
            )
        }
    }

    @Test
    @Ignore
    fun `Test inline text`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        assertTrue {
            SketchIO.save(
                orig.inline(
                    Position(30, 36),
                    Font("Arial", Font.BOLD, 32),
                    Color.BLACK,
                    "Sketch"
                ),
                destImagePath("hummingbird_inline_text.jpg")
            )
        }
    }
}
