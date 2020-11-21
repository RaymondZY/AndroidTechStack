package zhaoyun.teckstack.android.kotlin.control

fun main() {
    WhenPlayground.play()
}

private object WhenPlayground {

    fun play() {
        playWithWhen()
    }

    //region when语句

    private enum class Color(
        private val r: Int,
        private val g: Int,
        private val b: Int
    ) {
        RED(255, 0, 0),
        GREEN(0, 255, 0),
        BLUE(0, 0, 255);

        fun intValue() = (r * 256 + g) * 256 + b
    }

    private fun playWithWhen() {
        println("WhenPlayground.playWithWhen()")

        println("int value of ${convertColorToString(Color.RED)} is ${Color.RED.intValue()}")
        println("int value of ${convertColorToString(Color.GREEN)} is ${Color.GREEN.intValue()}")
        println("int value of ${convertColorToString(Color.BLUE)} is ${Color.BLUE.intValue()}")

        println("Mixture of ${Color.RED} and ${Color.RED} is ${mixColor(Color.RED, Color.RED)}")
        println("Mixture of ${Color.RED} and ${Color.BLUE} is ${mixColor(Color.RED, Color.BLUE)}")

        println("Mixture of ${Color.RED} and ${Color.RED} is ${mixColorOptimized(Color.RED, Color.RED)}")
        println("Mixture of ${Color.RED} and ${Color.BLUE} is ${mixColorOptimized(Color.RED, Color.BLUE)}")
    }

    // when是一个有返回值的表达式
    // 可以直接操作或者return
    private fun convertColorToString(color: Color) =

        "color " + when (color) {
            Color.RED -> "red"
            Color.GREEN -> "green"
            Color.BLUE -> "blue"
        }

    @Suppress("SameParameterValue")
    private fun mixColor(color1: Color, color2: Color) =
        // when结构可以使用任意对象
        when (setOf(color1, color2)) {
            setOf(Color.RED, Color.RED) -> "red"
            // 使用else来区分其它情况
            else -> "unknown color"
        }

    // when结构可以没有参数
    // 避免上面的实现中频繁创建set对象
    @Suppress("SameParameterValue")
    private fun mixColorOptimized(color1: Color, color2: Color) =
        when {
            // 前半部分是一个boolean类型的表达式
            color1 == Color.RED && color2 == Color.RED -> "red"
            else -> "unknown color"
        }

    //endregion when语句
}
