package com.imageprocessor.imageprocessing

import com.imageprocessor.imagelibrary.sketch.SketchImage
import com.imageprocessor.imagelibrary.sketch.filter.GrayscaleFilter
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

/**
 * Author: Alicia Garcia
 * Version: 1.0
 *
 * Class for processing images to add filters or rotate based on the sketch library
 *
 **/
class SketchImageProcessing(val image: ByteArray) {
    // create image as bufferedimage
    val sketchImage = SketchImage(getBufferedImage(image))

    /**
     * Constant values
     */
    companion object {
        // values for rotations 90 degrees
        const val RIGHT = 90
        const val LEFT = 270
    }

    /**
     * Rotate the [sketchImage] by the given [degrees] and return
     * the processed [image] in a ByteArray
     */
    suspend fun rotateImage(degrees: Int): ByteArray {
        return if (degrees != 0) {
            getByteArrayImage(
                sketchImage.rotate(degrees).asBufferedImage()
            )
        } else {
            image
        }
    }

    /**
     * Rotate [image] clockwise/counterclockwise base in user input (left or right)
     */
    suspend fun rotate90LeftOrRight(direction: String): ByteArray {
        return when (direction.lowercase()) {
            "right" -> {
                getByteArrayImage(
                    sketchImage.rotate(RIGHT).asBufferedImage()
                )
            }

            "left" -> {
                getByteArrayImage(
                    sketchImage.rotate(LEFT).asBufferedImage()
                )
            }

            else -> {
                image
            }
        }
    }

    /**
     * Add a filter for grayscale to the [image]
     */
    suspend fun toGrayscale(): ByteArray {
        return getByteArrayImage(
            sketchImage.filter(GrayscaleFilter()).asBufferedImage()
        )
    }

    /**
     * Helper function to convert the ByteArray image to a Buffered Image to utilize
     * the Sketch library
     */
    private fun getBufferedImage(image: ByteArray): BufferedImage {
        return ImageIO.read(ByteArrayInputStream(image))
    }

    /**
     * Helper function to convert the Buffered Image back to a ByteArray
     */
    private fun getByteArrayImage(image: BufferedImage): ByteArray {
        val baImage = ByteArrayOutputStream()
        ImageIO.write(image, "png", baImage)
        return baImage.toByteArray()
    }
}
