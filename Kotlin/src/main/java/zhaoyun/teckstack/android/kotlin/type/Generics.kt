package zhaoyun.teckstack.android.kotlin.type

/**
 *
 * @author zhaoyun
 * @version 2020/11/21
 */

fun main() {
    GenericsPlayground.play()
}

private object GenericsPlayground {

    fun play() {
        playWithGenerics()
    }

    // 类的泛型参数写在ClassName之后
    private class ValueHolder<T>(private val value: T) {

        // 方法泛型参数写在fun关键字后
        fun <R> map(transform: (T) -> R) : R {
            return transform(value)
        }
    }

    private fun playWithGenerics() {
        println("GenericsPlayground.playWithGenerics()")

        val valueHolder = ValueHolder(1)
        val mapped = valueHolder.map { "value = $it" }
        println(mapped)
    }
}