package zhaoyun.teckstack.android.kotlin.basic

fun main() {
    InterfacePlayground.play()
}

private object InterfacePlayground {

    fun play() {
        playWithInterface()
    }

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

    private interface Programmer {

        fun writeProgram()

        // 接口可以有默认实现
        // 如果只有一行实现可以使用=进行简写
        fun sayHello() = println("Hi, I am a programmer")

        // 接口可以有默认实现
        // 如果有多行可以使用{}代码块
        fun debug() {
            println("I am debugging")
        }

        fun learnKotlin() = println("I am learning kotlin")
    }

    private interface HearthStonePlayer {

        fun sayHello() = println("Hi, I am a HearthStone player")
    }

    // Kotlin使用:来表示Java中的extends或者implements
    private class ComputerScienceStudent(name: String) : Student(name), Programmer, HearthStonePlayer {

        override fun writeProgram() {
            println("$name is writing a program")
        }

        // 实现两个接口有相同签名的方法时，需要显示重写这个方法
        override fun sayHello() {
            // 可以使用这样的语法调用接口的默认方法
            super<Programmer>.sayHello()
            super<HearthStonePlayer>.sayHello()
        }

        override fun printName() {
            println("I am $name")
        }
    }

    private fun playWithInterface() {
        println("playWithInterface()")
        val zhaoyun = ComputerScienceStudent("zhaoyun")
        zhaoyun.printName()
        zhaoyun.sayHello()
        zhaoyun.learnKotlin()
        zhaoyun.writeProgram()
        zhaoyun.debug()
    }
}
