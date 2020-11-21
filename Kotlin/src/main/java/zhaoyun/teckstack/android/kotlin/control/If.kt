package zhaoyun.teckstack.android.kotlin.control

import kotlin.random.Random

/**
 *
 * @author zhaoyun
 * @version 2020/11/21
 */

fun main() {
    IfPlayground.play()
}

private object IfPlayground {

    fun play() {
        playWithIf()
    }

    private fun playWithIf() {
        println("IfPlayground.playWithIf()")

        val intValue = Random(System.currentTimeMillis()).nextInt()
        println("intValue = $intValue")
        val text = if (intValue % 2 != 0) {
            "odd"
        } else {
            "even"
        }
        println(text)
    }
}