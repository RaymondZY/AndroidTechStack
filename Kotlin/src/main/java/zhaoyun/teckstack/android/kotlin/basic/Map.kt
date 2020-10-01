package zhaoyun.teckstack.android.kotlin.basic

fun main() {
    MapPlayground.play()
}

private object MapPlayground {

    fun play() {
        playWithMap()
    }

    private fun playWithMap() {
        println("MapPlayground.playWithMap()")

        // 底层使用的是java的LinkedHashMap
        val map = mapOf(
            1 to "a",
            2 to "b",
            3 to "c"
        )
        println(map)
        println(map.javaClass)

        println("map[0] = ${map[0]}")
        println("map[1] = ${map[1]}")
        println("map[2] = ${map[2]}")
        println("map[3] = ${map[3]}")

        val map2 = HashMap<Int, String>()
        map2[1] = "a"
        map2[2] = "b"
        map2[3] = "c"
        println(map2)
        println(map2.javaClass)

        println("map2[0] = ${map2[0]}")
        println("map2[1] = ${map2[1]}")
        println("map2[2] = ${map2[2]}")
        println("map2[3] = ${map2[3]}")
    }
}

