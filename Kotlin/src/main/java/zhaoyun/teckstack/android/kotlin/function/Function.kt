package zhaoyun.teckstack.android.kotlin.function

fun main() {
    FunctionPlayground.play()
}

private object FunctionPlayground {

    fun play() {
        playWithFunction()
        playWithFunctionExtension()
    }

    //region function

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

    //endregion function

    //region function extension

    private class SomeClass(var text: String)

    private fun SomeClass.extensionFunction() =
        println("This is a extension function for ${this.text}")

    private fun playWithFunctionExtension() {
        println("FunctionPlayground.playWithFunctionExtension()")

        val value = SomeClass("foo")
        value.extensionFunction()
    }

    //endregion function extension
}


