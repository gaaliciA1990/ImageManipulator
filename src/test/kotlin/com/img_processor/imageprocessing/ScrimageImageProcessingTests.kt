package com.img_processor.imageprocessing

import com.sksamuel.scrimage.angles.Degrees
import io.mockk.mockk
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.unmockkAll
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * Author: Alicia Garcia
 * Version: 1.0
 * Date: 2/27/2022 22:46
 */

class ManipulateImageTest {
    val testImage = "resources/images/testImg1.png"

    @BeforeTest
    fun beforeEach() {

    }

    @AfterTest
    fun afterEach() {
        clearAllMocks()
        unmockkAll()
    }

    @Test
    fun `resizeImage_returns_new_image_based_on_width_and_height`() {
        // SET UP
        val scrimageImageProcessing = ScrimageImageProcessing(testImage)
        val width = 1280
        val height = 720

        every { mockImg. } returns mockk()

        // DO
        val returnedImage =  scrimageImageProcessing.rotateImage(degree)

        // ASSERT - since we can't see the image, we will verify a method was called instead to rotate the image
        // verify(exactly = 1) {mockImg.rotate(Degrees(degree))}
    }
}