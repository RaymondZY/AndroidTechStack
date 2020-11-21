package zhaoyun.teckstack.android.kotlin.function

fun main() {
    FunctionPlayground.play()
}

private object FunctionPlayground {

    fun play() {
        playWithFunction()
        playWithFunctionExtension()
        playWithInfixFunction()
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

    private class ExtensionClass(var text: String)

    private fun ExtensionClass.extensionFunction() =
        println("This is a extension function for ${this.text}")

    private fun playWithFunctionExtension() {
        println("FunctionPlayground.playWithFunctionExtension()")

        val value = ExtensionClass("extension class")
        value.extensionFunction()
    }

    //endregion function extension

    //region infix function

    class Person(var name: String)

    infix fun Person.playWith(somebody: Person) {
        println("${this.name} is playing with ${somebody.name}")
    }

    private fun playWithInfixFunction() {
        println("FunctionPlayground.playWithInfixFunction()")

        val zhaoyun = Person("zhaoyun")
        val maodou = Person("maodou")

        zhaoyun playWith maodou
    }

    //endregion infix function
}


