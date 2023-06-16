package com.img_processor.imageprocessing

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.angles.Degrees
import com.sksamuel.scrimage.filter.GrayscaleFilter
import com.sksamuel.scrimage.nio.JpegWriter


/**
 * Author: Alicia Garcia
 * Version: 1.0
 * Date: 2/12/2022 18:28
 *
 * Class for rotating an image by ## degrees. The rotate function uses
 * radians for step rotations, so we have a helper method for converting the degrees
 * to radians.
 */

class ManipulateImage(val image: ByteArray) {

    val immutableImage = convertToImmutableImage(image)

        /**
     * Scale the [image] based on the values for width and height passed
     * through. This is resizing as people want, but without cropping
     * it. Using pixel size
     *
     * Return the scaled [image]
     */
    fun resizeImage(width: Int, height: Int): ByteArray {
        return convertToByteArray(
            immutableImage.scaleTo(width, height)
        )
    }

    /**
     * Scale the [image] based on the value for width passed
     * through without cropping the image. Using pixel size
     *
     * Return the scaled [image]
     */
    fun resizeImageWidth(width: Int): ByteArray {
        return convertToByteArray(
            immutableImage.scaleToWidth(width)
        )
    }

    /**
     * Scale the [image] based on the value for height passed
     * through without cropping it. Using pixel size
     *
     * Return the scaled [image]
     */
    fun resizeImageHeight(height: Int): ByteArray {
        return convertToByteArray(
            immutableImage.scaleToHeight(height)
        )
    }

    /**
     * Scale the [image] to a thumbnail ratio of 1.49
     *
     * Return the thumbnail [image]
     */
    fun resizeImageToThumbnail(): ByteArray {
        val width = 160
        val height = 108

        return convertToByteArray(
            immutableImage.scaleTo(width, height)
        )
    }

    /**
     * Flip the [image] either horizontally or vertically
     * based on user input.
     *
     * Return the flipped [image]
     */
    fun flipImage(direction: String): ByteArray {
        // return the image flipped, else return same image unchanged
        return when (direction.lowercase()) {
            "horizontal" -> {
                convertToByteArray(
                    immutableImage.flipX()
                )
            }

            "vertical" -> {
                convertToByteArray(
                    immutableImage.flipY()
                )
            }

            else -> {
                image
            }
        }
    }

    /**
     * Helper function to convert [image] to ImmutableImage to utilize
     * the Sketch library
     */
    private fun convertToImmutableImage(image: ByteArray): ImmutableImage {
        // return an immutable image, only if it's a valid image
        return ImmutableImage.loader().fromBytes(image)
    }

    /**
     * Helper function to convert [image] to a bytearray
     *
     * Returns a ByteArray
     */
    private fun convertToByteArray(image: ImmutableImage): ByteArray {
        return image.bytes(JpegWriter.Default)
    }
}