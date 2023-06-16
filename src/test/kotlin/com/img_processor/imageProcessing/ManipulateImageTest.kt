package com.img_processor.ImgManipulators

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.angles.Degrees
import io.mockk.mockk
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.unmockkAll
import io.mockk.verify
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
/**
 * Author: Alicia Garcia
 * Version: 1.0
 * Date: 2/27/2022 22:46
 */

class ManipulateImageTest {
    lateinit var mockImg: ImmutableImage
    @BeforeTest
    fun beforeEach() {
        mockImg = mockk()
    }

    @AfterTest
    fun afterEach() {
        clearAllMocks()
        unmockkAll()
    }

    @Test
    fun `rotateImage_returns_image_rotated_by_X_degrees`() {
        // SET UP
        val manipulateImage = ManipulateImage(mockImg)
        val degree: Int = 15

        every { mockImg.rotate(any<Degrees>()) } returns mockk()

        // DO
        val returnedImage =  manipulateImage.rotateImage(degree)

        // ASSERT - since we can't see the image, we will verify a method was called instead to rotate the image
        verify(exactly = 1) {mockImg.rotate(Degrees(degree))}
    }
}