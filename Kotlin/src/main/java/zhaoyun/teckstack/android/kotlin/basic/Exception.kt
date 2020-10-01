package zhaoyun.teckstack.android.kotlin.basic

import java.lang.NumberFormatException

fun main() {
    ExceptionPlayground.play()
}

private object ExceptionPlayground {

    fun play() {
        playWithException()
    }

    private fun playWithException() {
        println("ExceptionPlayground.playWithException()")

        // try表达式有返回值，为try代码段中最后一个表达式的值
        val number = try {
            println("try")
            Integer.parseInt("123")
            Integer.parseInt("abc")
        } catch (e: NumberFormatException) {
            println("catch")
            println(e)
            // 发生异常时使用这个值
            -1
        } finally {
            println("finally")
        }

        println(number)
    }

}
