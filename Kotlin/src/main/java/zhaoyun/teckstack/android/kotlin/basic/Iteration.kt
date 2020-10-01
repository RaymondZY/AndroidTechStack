package zhaoyun.teckstack.android.kotlin.basic
fun main() {
    IterationPlayground.play()
}

private object IterationPlayground {

    fun play() {
        playWithForIteration()
        playWithWhile()
    }

    private fun playWithForIteration() {
        println("IterationPlayground.playWithForIteration()")

        // in begin..end end也包括在内
        println("for (i in 0..10 step 2)")
        for (i in 0..10 step 2) {
            println(i)
        }

        // in end downTo begin begin包括在内
        println("for (i in 10 downTo 0 step 2)")
        for (i in 10 downTo 0 step 2) {
            println(i)
        }

        // in begin util end end不包括在内
        println("for (i in 0 until 10 step 2)")
        for (i in 0 until 10 step 2) {
            println(i)
        }
    }

    private fun playWithWhile() {
        println("IterationPlayground.playWithWhile()")

        var i = 0
        while (i < 10) {
            println(i)
            i++
        }
    }

}

