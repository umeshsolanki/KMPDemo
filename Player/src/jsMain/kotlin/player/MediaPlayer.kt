package player

import logger.jsLog
import player.howler.Howl
import player.howler.HowlOptions
import player.howler.HowlerEvent

object MediaPlayer {

    private var player: Howl? = null
    private val playing: MutableSet<Int> = mutableSetOf()
    private val queue: MutableSet<String> = mutableSetOf()

    fun play(urls: Array<String>) {
        if (player != null) {
            jsLog("Stopping previous sound")
            stop()
        }
        createPlayer(urls)
        listenPlayerEvents()
    }

    fun play(url: String) {
        jsLog("Playing: $url")
//        if (queue.contains(url)) {
//            jsLog("Already playing: $url")
//            return
//        }
        if (player != null) {
            jsLog("Stopping previous sound")
            stop()
        }
//        queue.add(url)
        createPlayer(arrayOf(url))
        listenPlayerEvents()
    }

    fun listenPlayerEvents() {
        player?.apply {
            play()
            on(HowlerEvent.PLAY.value) { id ->
                playing.add(id)
            }
            on(HowlerEvent.STOP.value) { id ->
//                queue.remove(url)
                playing.remove(id)
                jsLog("Sound with ID $id stopped.")
            }
            on(HowlerEvent.PLAY_ERROR.value) { id ->
//                queue.remove(url)
                playing.remove(id)
                jsLog("Sound with ID $id PLAY_ERROR.")
            }
            on(HowlerEvent.LOAD_ERROR.value) { id ->
//                queue.remove(url)
                playing.remove(id)
                jsLog("Sound with ID $id LOAD_ERROR.")
            }
            on(HowlerEvent.END.value) { id ->
//                queue.remove(url)
                playing.remove(id)
                jsLog("Sound with ID $id ended.")
            }
        }
    }

    private fun createPlayer(url: Array<String>) {
        player = Howl(options = HowlOptions().apply {
            src = url
        })
    }

    fun isPlaying() = playing.isNotEmpty()

    fun pause() {
        playing.forEach { id ->
            jsLog("Paused sound with ID: $id")
            player?.pause(id)
        }
    }

    fun stop() {
        playing.forEach { id ->
            jsLog("Stopped sound with ID: $id")
            player?.stop(id)
        }
    }
}