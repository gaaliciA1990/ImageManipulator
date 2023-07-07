package com.imageprocessor.imageprocessing

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.nio.JpegWriter

/**
 * Author: Alicia Garcia
 * Version: 1.0
 * Date: 2/12/2022 18:28
 *
 * Class for processing images to resize or flip horizontally/vertically
 */

class ScrimageImageProcessing(val image: ByteArray) {

    val immutableImage = convertToImmutableImage(image)

    /**
     * Constant values
     */
    companion object {
        // thumbnails values
        const val TN_WIDTH = 160
        const val TN_HEIGHT = 108
    }

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
        return convertToByteArray(
            immutableImage.scaleTo(Companion.TN_WIDTH, TN_HEIGHT)
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
     * the Scrimage library
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
