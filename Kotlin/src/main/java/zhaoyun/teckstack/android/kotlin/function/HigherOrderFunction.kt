package zhaoyun.teckstack.android.kotlin.function

/**
 *
 * @author zhaoyun
 * @version 2020/11/21
 */
fun main() {
    HigherOrderFunctionPlayground.play()
}

private object HigherOrderFunctionPlayground {

    fun play() {
        playWithHigherOrderFunction()
        playWithCurryingLikeFunction()
    }

    //region higher-order function

    @Suppress("SameParameterValue")
    private fun createAddFunction(offset: Int) = { input: Int -> offset + input }

    // 与上面的函数是等价的
    // 上面的函数是一种简写
    @Suppress("unused")
    private fun createAddFunction2(offset: Int): (Int) -> Int {
        return { input: Int -> offset + input }
    }

    private fun playWithHigherOrderFunction() {
        println("HighOrderFunctionPlayground.playWithHighOrderFunction()")

        val function5 = createAddFunction(offset = 5)
        println("function5(1) = ${function5(1)}")
    }

    //endregion higher-order function

    //region currying-like function

    @Suppress("SameParameterValue")
    private fun curryingLikeFunction(firstValue: Int) = { secondValue: Int ->
        { thirdValue: Int ->
            {
                firstValue + secondValue + thirdValue
            }
        }
    }

    private fun playWithCurryingLikeFunction() {
        println("HighOrderFunctionPlayground.playWithCurryingLikeFunction()")

        println(curryingLikeFunction(1)(2)(3))
    }

    //endregion currying-like function
}