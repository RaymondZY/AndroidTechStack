package zhaoyun.teckstack.android.kotlin.`class`

fun main() {
    ObjectPlayground.play()
}

private object ObjectPlayground {

    fun play() {
        playWithSingleton()
        playWithFactory()
        playWithAnonymousClass()
    }

    //region object单例

    private object SingletonObject {

        fun sayHello() = println("Hello, I am $this")

    }

    private fun playWithSingleton() {
        println("ObjectPlayground.playWithSingleton()")

        // 指向的同一个对象
        val object1 = SingletonObject
        val object2 = SingletonObject
        object1.sayHello()
        object2.sayHello()
    }

    //endregion object单例

    //region companion object简单工厂

    // 实现一个private修饰的构造方法
    private class PrivateConstructorClass private constructor(private val name: String) {

        // 对象的创建只能通过调用companion object声明的静态方法实现
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

    //endregion companion object简单工厂

    //region object匿名类

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

    //endregion 匿名类
}
