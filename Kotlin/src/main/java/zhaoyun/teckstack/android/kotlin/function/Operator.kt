package zhaoyun.teckstack.android.kotlin.function

fun main() {
    OperatorPlayground.play()
}

private object OperatorPlayground {

    fun play() {
        playWithOperator()
        playWithGetSet()
        playWithComponent()
    }

    private data class Point(var x: Int, var y: Int) {

        operator fun plus(p: Point): Point {
            return Point(x + p.x, y + p.y)
        }

        operator fun times(scale: Int): Point {
            return Point(x * scale, y * scale)
        }

        operator fun get(index: Int): Int {
            return when (index) {
                0 -> x
                1 -> y
                else -> throw IndexOutOfBoundsException("invalid index")
            }
        }

        operator fun set(index: Int, value: Int) {
            when (index) {
                0 -> x = value
                1 -> y = value
                else -> throw IndexOutOfBoundsException("invalid index")
            }
        }
    }

    // 可以使用扩展函数支持运算符重载
    operator fun Point.minus(p: Point): Point {
        return Point(x - p.x, y - p.y)
    }

    private fun playWithOperator() {
        println("OperatorPlayground.playWithOperator()")

        val p1 = Point(1, 2)
        val p2 = Point(2, 3)
        println(p1 + p2)
        println(p1 - p2)
        println(p1 * 3)
    }

    private fun playWithGetSet() {
        println("OperatorPlayground.playWithGetSet()")

        val p = Point(1, 2)
        println(p[0])
        println(p[1])

        p[0] = -1
        p[1] = -2
        println(p[0])
        println(p[1])
    }

    private class ManualComponentPoint(var x: Int, var y: Int) {
        operator fun component1(): Int = x
        operator fun component2(): Int = y
    }

    private fun playWithComponent() {
        println("OperatorPlayground.playWithComponent()")

        val (x1, y1) = Point(1, 2)
        println(x1)
        println(y1)

        val (x2, y2) = ManualComponentPoint(1, 2)
        println(x2)
        println(y2)
    }
}