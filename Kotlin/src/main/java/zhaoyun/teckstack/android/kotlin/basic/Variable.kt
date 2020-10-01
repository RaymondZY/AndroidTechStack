package zhaoyun.teckstack.android.kotlin.basic

fun main() {
    VariablePlayground.play()
}

private object VariablePlayground {

    fun play() {
        playWithVal()
        playWithVar()
    }

    private class Person(
        var name: String,
        var isMarried: Boolean = false
    ) {
        override fun toString(): String {
            return "Person(name='$name', isMarried=$isMarried)"
        }
    }

    private fun playWithVal() {
        println("VariablePlayground.playWithVal()")

        // val是value的意思，相当于Java中的final变量，只能被赋值一次。
        val value = 1
        // value1 = 2 无法编译
        println(value)

        // 和Java一样 val变量引用不可更改，但是引用的对象内的值是可以改变的。
        val zhaoyun = Person("zhaoyun")
        println(zhaoyun)

        zhaoyun.isMarried = true
        println(zhaoyun)
    }

    private fun playWithVar() {
        println("VariablePlayground.playWithVar()")

        var variable = 1
        println(variable)

        variable = 2
        println(variable)
    }
}
