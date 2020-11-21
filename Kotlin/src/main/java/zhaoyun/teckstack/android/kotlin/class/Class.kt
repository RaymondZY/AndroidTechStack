package zhaoyun.teckstack.android.kotlin.`class`

fun main() {
    ClassPlayground.play()
}

private object ClassPlayground {

    fun play() {
        playWithClass()
        playWithInnerClass()
        playWithDataClass()
        playWithBy()
    }

    //region class

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
        @Suppress("unused")
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

    //endregion class

    //region inner class

    private class OuterClass {

        val name = "outer class"

        // 使用inner关键字修饰，让内部类可以获取到外部的引用
        inner class InnerClass {

            fun playWithInnerClass() {
                println("Outer class = ${this@OuterClass.name}")
            }
        }

        // 默认是嵌套内部类
        class NestedClass {

            fun playWithNestedClass() {
                // 编译错误，无法获取
                // this@OuterClass.name
                println("Can't access outer reference")
            }
        }
    }

    private fun playWithInnerClass() {
        println("ClassPlayground.playWithInnerClass()")

        OuterClass().InnerClass().playWithInnerClass()
        OuterClass.NestedClass().playWithNestedClass()
    }

    //endregion inner class

    //region data class

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

    //endregion data class

    //region by

    private class DelegatingCollection<T>(
        private val innerList: Collection<T>
    ) : Collection<T> by innerList

    private fun playWithBy() {
        println("ClassPlayground.playWithBy()")

        val delegatingCollection = DelegatingCollection(arrayListOf("a", "b", "c"))
        println("delegatingCollection.size = ${delegatingCollection.size}")
        delegatingCollection.forEach(::println)
    }

    //endregion by
}
