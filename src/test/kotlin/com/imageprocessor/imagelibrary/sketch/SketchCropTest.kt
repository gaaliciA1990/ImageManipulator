package com.imageprocessor.imagelibrary.sketch

import com.imageprocessor.imagelibrary.sketch.geom.Position
import com.imageprocessor.imagelibrary.sketch.geom.Rectangle
import com.imageprocessor.imagelibrary.sketch.util.SketchIO
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import kotlin.test.assertTrue

//https://github.com/gaaliciA1990/ImageManipulator/issues/1
class SketchCropTest : SketchTestBase() {

    @Test
    @Ignore
    fun `Test crop image`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        val rectangle = Rectangle(
            40,
            30,
            orig.asBufferedImage().width / 2,
            orig.asBufferedImage().height / 2
        )
        assertTrue {
            SketchIO.save(
                orig.crop(rectangle),
                destImagePath("hummingbird_crop_rect.jpg")
            )
        }
    }

    @Test
    @Ignore
    fun `Test crop image as a circle`(): Unit = runBlocking {
        val orig = SketchIO.load(sourceImagePath)
        assertTrue {
            SketchIO.save(
                orig.crop(Position(65, 25), 50),
                destImagePath("hummingbird_crop_circle.png")
            )
        }
    }
}
