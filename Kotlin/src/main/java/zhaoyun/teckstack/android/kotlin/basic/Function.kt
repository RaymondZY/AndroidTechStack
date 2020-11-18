package zhaoyun.teckstack.android.kotlin.basic

fun main() {
    FunctionPlayground.play()
    HighOrderFunctionPlayground.play()
}

private object FunctionPlayground {

    fun play() {
        playWithFunction()
        playWithFunctionExtension()
    }

    private fun playWithFunction() {
        println("FunctionPlayground.playWithFunction()")

        val list = listOf(1, 2, 3)

        // 有默认值的参数可以不传递
        println(joinToString(list))
        // 可以使用命名参数增加代码可读性，并且可以改变参数传递顺序
        println(joinToString(list, prefix = "[", postFix = "]", separator = " | "))
    }

    private fun <T> joinToString(
        collection: Collection<T>,
        // 可以设置参数的默认值
        separator: String = ", ",
        prefix: String = "(",
        postFix: String = ")"
    ): String {
        val stringBuilder = StringBuilder(prefix)
        for ((index, value) in collection.withIndex()) {
            if (index > 0) {
                stringBuilder.append(separator)
            }
            stringBuilder.append(value)
        }
        stringBuilder.append(postFix)
        return stringBuilder.toString()
    }

    private class SomeClass(var text: String)

    private fun SomeClass.extensionFunction() =
        println("This is a extension function for ${this.text}")

    private fun playWithFunctionExtension() {
        println("FunctionPlayground.playWithFunctionExtension()")

        val value = SomeClass("foo")
        value.extensionFunction()
    }

}

private object HighOrderFunctionPlayground {

    fun play() {
        playWithHighOrderFunction()
        playWithCurryingLikeFunction()
    }

    private fun createAddFunction(offset: Int) = { input: Int -> offset + input }

    // 与上面的函数是等价的
    // 上面的函数是一种简写
    private fun createAddFunction2(offset: Int): (Int) -> Int {
        return { input: Int -> offset + input }
    }

    private fun playWithHighOrderFunction() {
        println("HighOrderFunctionPlayground.playWithHighOrderFunction()")
        val function = createAddFunction(offset = 5)
        println(function(1))
    }

    private fun curryingLikeFunction(firstValue: Int) = { secondValue: Int ->
        { thirdValue: Int ->
            {
                firstValue + secondValue + thirdValue
            }
        }
    }

    private fun playWithCurryingLikeFunction() {
        println("HighOrderFunctionPlayground.playWithCurryingLikeFunction()")
        println(curryingLikeFunction(1)(2)(3))
    }
}

