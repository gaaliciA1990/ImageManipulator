package com.imageprocessor.plugins

import ConstantAPI
import ConstantAPI.INVALID_IMAGE
import ConstantAPI.INVALID_PARAM
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.request.receiveChannel
import io.ktor.server.response.respondBytes
import io.ktor.server.response.respondText
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

/**
 * Function for mapping the routing for the API
 */
@Suppress(
    "LongMethod",
    "CyclomaticComplexMethod"
)
fun Application.configureRouting() {
    val controller = ApiController()

    /**
     * Starting point for a Ktor app:
     */
    routing {
        /**
         * set default route mapping
         */
        route(ConstantAPI.API_PATH) {
            /**
             * call to manipulate an image with any/all options
             */
            post(ConstantAPI.API_COMBO) {
                // upload the image to be manipulated
                val uploadedImage = getByteArray(call)

                // verify we have a valid image
                if (uploadedImage == null) {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        INVALID_IMAGE
                    }
                    return@post
                }

                // parse the string of commands to a list of string, based on commas
                val commands: List<String>? = call.request.queryParameters["combo"]?.split(",")

                // check our commands list is not null.
                if (commands == null) {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        "Missing Commands: No commands found to process image"
                    }
                    return@post
                }

                val returnedImage = controller.combinationImage(commands, uploadedImage)

                // verify the commands are valid, return bad request if not
                if (returnedImage == null) {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        "Invalid Command Found: Please check your command parameters match our requirements"
                    }
                    return@post
                } else {
                    // return processed image
                    call.respondBytes(returnedImage)
                }
            }

            /**
             * access call for rotate any degree
             */
            post(ConstantAPI.API_ROTATE) {
                // upload the image to be manipulated
                val uploadedImage = getByteArray(call)

                // store the passed value for degrees and convert to an integer
                // If unable to convert to int, set to null
                val degree = call.request.queryParameters["degree"]?.toIntOrNull()

                // verify we have a valid image
                if (uploadedImage == null) {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        INVALID_IMAGE
                    }
                    return@post
                }

                // call api controller to manipulate the image if applicable
                if (degree != null) {
                    // rotate the image by degrees and return rotated image
                    call.respondBytes(
                        controller.rotateDegreesImage(degree, uploadedImage)
                    )
                } else {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        String.format(INVALID_PARAM, "degree")
                    }
                }
            }

            /**
             * access call for rotating left or right 90degrees
             */
            post(ConstantAPI.API_ROTATE90) {
                val uploadedImage = getByteArray(call)

                // parameters for rotation left or right
                val direction = call.request.queryParameters["direction"]

                // verify we have a valid image
                if (uploadedImage == null) {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        INVALID_IMAGE
                    }
                    return@post
                }

                // call api controller to rotate the image left or right
                if (!direction.isNullOrBlank()) {
                    // rotate the image left or right and return rotated image
                    call.respondBytes(
                        controller.rotateImage(direction, uploadedImage)
                    )
                } else {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        String.format(INVALID_PARAM, "direction")
                    }
                }
            }

            /**
             * access call for adding grayscale filter to image
             */
            post(ConstantAPI.API_GRAY) {
                val uploadedImage = getByteArray(call)

                // verify we have a valid image
                if (uploadedImage == null) {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        INVALID_IMAGE
                    }
                    return@post
                }
                // call controller to add grayscale filter and return filtered image
                call.respondBytes(
                    controller.grayscaleImage(uploadedImage)
                )
            }

            /**
             * access call for resize image based on width and height values, optionally
             * based on pixel size
             */
            post(ConstantAPI.API_RESIZE) {
                val uploadedImage = getByteArray(call)

                // store parameter for width and height as integers
                val width = call.request.queryParameters["width"]?.toIntOrNull()
                val height = call.request.queryParameters["height"]?.toIntOrNull()

                // verify we have a valid image
                if (uploadedImage == null) {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        INVALID_IMAGE
                    }
                    return@post
                }

                // resize the image based on width and/or height
                if (width == null && height == null) {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        String.format(INVALID_PARAM, "width/height")
                    }
                } else {
                    // call api controller to resize image and return resized image
                    call.respondBytes(
                        controller.resizeImage(width, height, uploadedImage)
                    )
                }
            }

            /**
             * access call for converting image to a thumbnail size
             */
            post(ConstantAPI.API_THUMBNAIL) {
                val uploadedImage = getByteArray(call)

                // verify we have a valid image
                if (uploadedImage == null) {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        INVALID_IMAGE
                    }
                    return@post
                }

                // call controller to convert image to thumbnail and convert image to bytes
                call.respondBytes(
                    controller.thumbnailImage(uploadedImage)
                )
            }

            /**
             * access call for flipping image
             */
            post(ConstantAPI.API_FLIP) {
                val uploadedImage = getByteArray(call)

                val direction = call.request.queryParameters["direction"]

                // verify we have a valid image
                if (uploadedImage == null) {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        INVALID_IMAGE
                    }
                    return@post
                }

                // flip the image based on direction
                if (!direction.isNullOrBlank()) {
                    // call controller to flip image and convert image to bytes
                    call.respondBytes(
                        controller.flipImage(direction, uploadedImage)
                    )
                } else {
                    call.respondText(status = HttpStatusCode.BadRequest) {
                        String.format(INVALID_PARAM, "direction")
                    }
                }
            }
        }
    }
}

suspend fun getByteArray(call: ApplicationCall): ByteArray {
    // channel to read the image bytes from
    val image = call.receiveChannel()

    // create an array of byes
    var byteArray = byteArrayOf()

    // populate the byte array with data from the image file
    while (!image.isClosedForRead) {
        byteArray += image.readByte()
    }

    return byteArray
}
