@file:JsModule("howler") @file:JsNonModule

package player.howler

external class Howl(options: HowlOptions) {
    fun play(sprite: String? = definedExternally): Int
    fun pause(id: Int? = definedExternally)
    fun stop(id: Int? = definedExternally)
    fun load()
    fun unload()
    fun seek(seek: Double? = definedExternally, id: Int? = definedExternally): Double
    fun volume(volume: Double? = definedExternally, id: Int? = definedExternally): Double
    fun rate(rate: Double? = definedExternally, id: Int? = definedExternally): Double
    fun fade(from: Double, to: Double, duration: Int, id: Int? = definedExternally)
    fun on(event: String, listener: (id: Int) -> Unit)
    fun off(event: String, listener: (id: Int) -> Unit = definedExternally)
    fun once(event: String, listener: (id: Int) -> Unit)
    fun playing(id: Int? = definedExternally): Boolean
    fun duration(id: Int? = definedExternally): Double
}

external interface HowlOptions {
    var src: Array<String>
    var format: Array<String>?
    var autoplay: Boolean?
    var loop: Boolean?
    var volume: Double?
    var sprite: dynamic
    var preload: Boolean?
    var mute: Boolean?
    var rate: Double?
    var onload: (() -> Unit)?
    var onplay: ((id: Int) -> Unit)?
    var onend: ((id: Int) -> Unit)?
    var onpause: ((id: Int) -> Unit)?
    var onstop: ((id: Int) -> Unit)?
    var onloaderror: ((id: Int, error: String) -> Unit)?
    var onplayerror: ((id: Int, error: String) -> Unit)?
}

external object Howler {
    var volume: Double
    fun mute(muted: Boolean)
    fun stop()
    fun unload()
}