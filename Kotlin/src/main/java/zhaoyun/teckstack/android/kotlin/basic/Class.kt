package zhaoyun.teckstack.android.kotlin.basic

fun main() {
    ClassPlayground.play()
}

private object ClassPlayground {

    fun play() {
        playWithClass()
        playWithInnerClass()
        playWithCustomGet()
        playWithGetterAndSetter()
        playWithDataClass()
        playWithBy()
        playWithLateInit()
        playWithByLazy()
    }

    // public是默认可见性，可以省略
    // open才能被继承 默认修饰符为final
    private open class Student(
        // public是默认可见性，可以省略
        var name: String
    ) {
        // override关键字是必须的
        override fun toString(): String {
            return "Student(name='$name')"
        }

        // 只有open的方法才能重写 默认是final的
        open fun printName() {
            println(name)
        }
    }

    private fun playWithClass() {
        println("ClassPlayground.playWithClass()")

        val student = Student("zhaoyun")
        println(student)

        // kotlin把一个getName()的getter方法暴露给java
        println("name is ${student.name}")

        // kotlin把一个setName()的setter方法暴露给java
        student.name = "maodou"
        println("name is ${student.name}")
    }

    private class OuterClass {

        val name = "outer class"

        inner class InnerClass {

            fun playWithInnerClass() {
                println("This is a inner class")
                println("Outer reference = ${this@OuterClass}")
            }
        }

        class NestedClass {

            fun playWithNestedClass() {
                // this won't compile
                // this@OuterClass.name
                println("This is a nested class")
                println("Can't access outer reference")
            }
        }
    }

    private fun playWithInnerClass() {
        println("ClassPlayground.playWithInnerClass()")

        OuterClass().InnerClass().playWithInnerClass()
        OuterClass.NestedClass().playWithNestedClass()
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
        println("ClassPlayground.playWithCustomGet()")

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
        println("ClassPlayground.playWithGetterAndSetter()")

        val zhaoyun = User("zhaoyun")
        zhaoyun.address = "YaSong"
        zhaoyun.address = "XiAnLanTing"
    }

    private data class DataClass(val name: String, val age: Int, val isMarried: Boolean)

    private fun playWithDataClass() {
        println("ClassPlayground.playWithDataClass()")

        val zhaoyun = DataClass("zhaoyun", 30, true)
        val zhaoyun2 = DataClass("zhaoyun", 30, true)
        println("zhaoyun.toString() = $zhaoyun")
        println("zhaoyun.hashCode() = ${zhaoyun.hashCode()}")
        println("zhaoyun2.hashCode() = ${zhaoyun2.hashCode()}")
        println("zhaoyun == zhaoyun2 = ${zhaoyun == zhaoyun2}")
        println("zhaoyun === zhaoyun2 = ${zhaoyun === zhaoyun2}")
    }

    private class DelegatingCollection<T>(
        private val innerList: Collection<T>
    ) : Collection<T> by innerList

    private fun playWithBy() {
        println("ClassPlayground.playWithBy()")

        val delegatingCollection = DelegatingCollection(arrayListOf("a", "b", "c"))
        println("delegatingCollection.size = ${delegatingCollection.size}")
        delegatingCollection.forEach(::println)
    }

    private class LateInitClass {
        lateinit var string: String
        // 这两种情况无法编译
        // lateinit var value: Int
        // lateinit val value: String

        fun init() {
            string = "blablabla"
        }

        fun print() {
            println(string)
        }
    }

    private fun playWithLateInit() {
        println("ClassPlayground.playWithLateInit()")
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
        println("ClassPlayground.playWithLateInit()")
        ByLazyClass().also {
            it.print()
        }
    }
}
