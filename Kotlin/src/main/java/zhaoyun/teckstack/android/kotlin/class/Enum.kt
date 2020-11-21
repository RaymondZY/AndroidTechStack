package zhaoyun.teckstack.android.kotlin.`class`

fun main() {
    EnumPlayground.play()
}

private object EnumPlayground {

    fun play() {
        playWithEnum()
    }

    //region enum

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

    private fun playWithEnum() {
        println("EnumPlayground.playWithEnum()")

        for (color in Color.values()) {
            println("The ordinal of $color is ${color.ordinal}")
            println("IntValue of $color is ${color.intValue()}")
        }
    }

    //endregion enum
}
