package zhaoyun.teckstack.android.kotlin.variable

fun main() {
    VariablePlayground.play()
}

private object VariablePlayground {

    fun play() {
        playWithVal()
        playWithVar()
        playWithCustomGet()
        playWithGetterAndSetter()
        playWithLateInit()
        playWithByLazy()
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

    private class Rectangle(
        private val height: Int,
        private val width: Int
    ) {
        // isSquare不需要字段保存它的值，它的值每次都会调用get()方法进行计算
        val isSquare: Boolean
            get() {
                println("get()方法被执行")
                return height == width
            }

        // 简便的写法
        // val isSquare: Boolean get() = height == width
    }

    private fun playWithCustomGet() {
        println("VariablePlayground.playWithCustomGet()")

        val rectangle = Rectangle(4, 4)
        // 会调用两次get()方法
        println("rectangle is a square ? ${rectangle.isSquare}")
        println("rectangle is a square ? ${rectangle.isSquare}")
    }

    private class User(val name: String) {
        var address: String = "unknown address"
            set(value) {
                println(
                    """
                    Address was changed for $name:
                    "$field" -> "$value"
                """.trimIndent()
                )
                field = value
            }
    }

    private fun playWithGetterAndSetter() {
        println("VariablePlayground.playWithGetterAndSetter()")

        val zhaoyun = User("zhaoyun")
        zhaoyun.address = "YaSong"
        zhaoyun.address = "XiAnLanTing"
    }

    private class LateInitClass {
        lateinit var string: String
        // 这两种情况无法编译
        // lateinit var value: Int
        // lateinit val value: String
        // 可以使用：var value by Delegates。notNull<Int>()代替lateinit var value: Int

        fun init() {
            string = "blablabla"
        }

        fun print() {
            println(string)
        }
    }

    private fun playWithLateInit() {
        println("VariablePlayground.playWithLateInit()")
        LateInitClass().also {
            // 在初始化之前调用会触发异常
            // kotlin.UninitializedPropertyAccessException:
            // lateinit property string has not been initialized
            // it.print()
            it.init()
            it.print()
        }
    }

    private class ByLazyClass {
        // 不同于lateinit
        // by lazy 可以定义Int和val
        private val i: Int by lazy {
            0
        }
        private val string: String by lazy {
            // 只会调用一次
            println("string initialized")
            "blablabla"
        }

        init {
            // 并且此时也能调用
            println(string)
        }

        fun print() {
            println(string)
        }
    }


    private fun playWithByLazy() {
        println("VariablePlayground.playWithLateInit()")
        ByLazyClass().also {
            it.print()
        }
    }
}
