package com.imageprocessor.imagelibrary.sketch.filter

import java.awt.AlphaComposite
import java.awt.Graphics2D

class OpacityFilter(private val opacity: Float) : Graphics2DFilter {

    override fun apply(g: Graphics2D) {
        g.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity)
    }
}
