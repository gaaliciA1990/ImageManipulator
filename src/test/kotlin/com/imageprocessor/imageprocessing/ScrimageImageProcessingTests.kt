package com.imageprocessor.imageprocessing

import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

/**
 * Author: Alicia Garcia
 * Version: 1.0
 * Date: 2/27/2022 22:46
 */

class ScrimageImageProcessingTests {
    val image1 = "images/testImg1.png"
    val image2 = "images/testImg2.png"

    lateinit var byteImg1: ByteArray
    lateinit var byteImg2: ByteArray

    @BeforeTest
    fun beforeEach() {
        byteImg1 = Files.readAllBytes(Paths.get(image1))
        byteImg2 = Files.readAllBytes(Paths.get(image2))
    }

    @AfterTest
    fun afterEach() {
        clearAllMocks()
        unmockkAll()
    }
}
