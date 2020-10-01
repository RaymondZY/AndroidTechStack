package zhaoyun.teckstack.android.kotlin.basic

fun main() {
    ObjectPlayground.play()
}

private object ObjectPlayground {

    fun play() {
        playWithSingleton()
        playWithFactory()
        playWithAnonymousClass()
    }

    private object SingletonObject {

        fun sayHello() = println("Hello, I am $this")

    }

    private fun playWithSingleton() {
        println("ObjectPlayground.playWithSingleton()")

        val object1 = SingletonObject
        val object2 = SingletonObject
        object1.sayHello()
        object2.sayHello()
    }

    private class PrivateConstructorClass private constructor(private val name: String) {

        companion object {

            fun createFromInt(int: Int) = PrivateConstructorClass("Int : $int")
            fun createFromString(string: String) = PrivateConstructorClass("String : $string")
        }

        fun sayHello() = println("Hello, I am $name")

    }

    private fun playWithFactory() {
        println("ObjectPlayground.playWithFactory()")

        val object1 = PrivateConstructorClass.createFromInt(1)
        val object2 = PrivateConstructorClass.createFromString("string")
        object1.sayHello()
        object2.sayHello()
    }

    private interface OnClickListener {
        fun onClick()
    }

    private class Button {

        private var listeners: ArrayList<OnClickListener> = ArrayList()

        fun addOnClickListener(onClickListener: OnClickListener) {
            listeners.add(onClickListener)
        }

        fun click() = listeners.forEach(OnClickListener::onClick)
    }

    private fun playWithAnonymousClass() {
        println("ObjectPlayground.playWithAnonymousClass()")

        val button = Button().apply {
            addOnClickListener(object : OnClickListener {

                override fun onClick() = println("First OnClickListener triggered")
            })
            addOnClickListener(object : OnClickListener {

                override fun onClick() = println("Second OnClickListener triggered")
            })
        }
        button.click()
    }
}
