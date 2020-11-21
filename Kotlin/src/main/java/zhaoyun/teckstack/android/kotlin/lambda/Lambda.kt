package zhaoyun.teckstack.android.kotlin.lambda

fun main() {
    LambdaPlayground.play()
}

private object LambdaPlayground {

    private data class Person(var name: String, var age: Int = 0)

    fun play() {
        playWithLambda()
        playWithWith()
        playWithApply()
        playWithLet()
        playWithAlso()
    }

    //region lambda

    private fun playWithLambda() {
        println("playWithLambda()")

        val people = listOf(
            Person("Alice", 21),
            Person("Bob", 23)
        )

        // 最desugar的写法是传入一个lambda参数
        people.maxByOrNull({ p: Person -> p.age })

        // kotlin约定：如果lambda是一个函数的最后一个参数，可以把它拿到括号外
        people.maxByOrNull() { p: Person -> p.age }

        // kotlin约定：如果一个函数只有lambda作为参数，可以把它拿到括号外，并且不写括号
        people.maxByOrNull { p: Person -> p.age }

        // 省略掉类型，让编译器自己推断类型
        people.maxByOrNull { p -> p.age }

        // 省略参数声明，使用默认的参数it
        people.maxByOrNull { it.age }

        // 使用::表示的方法或者成员引用代替
        people.maxByOrNull(Person::age)
    }

    //endregion lambda

    //region with

    private fun playWithWith() {
        println("LambdaPlayground.playWithWith()")

        val zhaoyun = Person("zhaoyun", 30)
        // with的返回值是block最后一句表达式的值
        val returnValue = with(zhaoyun) {
            name = "zhaoyun31"
            age = 31
            this
        }
        println(returnValue)
    }

    //endregion with

    //region apply

    private fun playWithApply() {
        println("LambdaPlayground.playWithApply()")

        val zhaoyun = Person("zhaoyun", 30)
        // apply的返回值是block的this对象
        val returnValue = zhaoyun.apply {
            name = "zhaoyun31"
            age = 31
        }
        println(returnValue)
    }

    //endregion apply

    //region let

    private fun playWithLet() {
        println("LambdaPlayground.playWithLet()")

        val zhaoyun = Person("zhaoyun", 30)
        // 与with的区别是，with中this代表相关的对象，let参数it代表相关的对象
        // let的返回值是block的最后一句
        val returnValue = zhaoyun.let {
            it.name = "zhaoyun31"
            it.age = 13
            "return value is 100"
        }
        println(returnValue)
    }

    //endregion let

    //region also

    private fun playWithAlso() {
        println("LambdaPlayground.playWithAlso()")

        val zhaoyun = Person("zhaoyun", 30)
        // 返回相关的对象，与apply的区别：是否改变this指针
        val returnValue = zhaoyun.also {
            it.name = "zhaoyun31"
            it.age = 13
        }
        println(returnValue)
    }

    //endregion also
}