package com.er453r.plot

import kotlinx.browser.document
import org.khronos.webgl.Uint8ClampedArray
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.ImageData

class Image(width: Int, height: Int, private val colormap: Colormap, selector: String? = null, root: HTMLElement? = null) {
    private var image: ImageData
    private var context: CanvasRenderingContext2D
    private val canvas: HTMLCanvasElement

    init {
        canvas = document.createElement("canvas") as HTMLCanvasElement
        canvas.width = width
        canvas.height = height
        context = canvas.getContext("2d")!! as CanvasRenderingContext2D
        image = context.getImageData(0.0, 0.0, width.toDouble(), height.toDouble())

        selector?.let { attach(selector = it) }
        root?.let { attach(root = it) }
    }

    fun attach(selector: String? = null, root: HTMLElement? = null){
        selector?.let { document.querySelector(it)!!.appendChild(canvas) }
        root?.let { root.appendChild(canvas) }
    }

    fun <T> generic(vector: List<T>, collector: (T) -> Float) {
        val data: FloatArray = collect(vector, collector)

        val min: Float = PlotUtils.min(data)
        val max: Float = PlotUtils.max(data)

        val scale: Float = if (max == min) 0f else 1f / (max - min)

        val pixels: Uint8ClampedArray = image.data

        for (n in 0 until pixels.length / 4) {
            val color: Color = colormap.getColor((data[n] - min) * scale)

            pixels[4 * n + 0] = color.r // Red value
            pixels[4 * n + 1] = color.g//color.g.toByte() // Green value
            pixels[4 * n + 2] = color.b//color.b.toByte() // Blue value
            pixels[4 * n + 3] = 255//255.toByte() // Alpha value
        }

        context.putImageData(image, 0.0, 0.0)
    }

    private fun <T> collect(data: List<T>, collector: (T) -> Float): FloatArray {
        return FloatArray(data.size) { collector(data[it]) }
    }
}

inline operator fun Uint8ClampedArray.set(index: Int, value: Int) {
    asDynamic()[index] = value; }
