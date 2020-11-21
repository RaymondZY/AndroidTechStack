package zhaoyun.teckstack.android.kotlin.collection

fun main() {
    ListPlayground.play()
}

private object ListPlayground {

    fun play() {
        playWithList()
    }

    //region list

    private fun playWithList() {
        println("ListPlayground.playWithList()")

        // 底层使用的是Java的ArrayList
        val list = listOf(1, 2, 3)
        println(list.javaClass)
    }

    //endregion list
}

