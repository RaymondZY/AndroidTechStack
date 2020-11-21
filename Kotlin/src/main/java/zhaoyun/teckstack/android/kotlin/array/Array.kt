package zhaoyun.teckstack.android.kotlin.array

/**
 *
 * @author zhaoyun
 * @version 2020/11/21
 */
fun main() {
    ArrayPlayground.play()
}

private object ArrayPlayground {

    fun play() {
        playWithArray()
    }

    private fun playWithArray() {
        println("ArrayPlayground.playWithArray()")

        val array = arrayOf(1, 2, 3)
        // 使用intArray可以避免拆装箱
        val intArray = intArrayOf(1, 2, 3)
        array.forEach(::println)
        intArray.forEach(::println)

        //与List的相互转换
        intArray.toList().toIntArray()
    }
}