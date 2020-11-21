package zhaoyun.teckstack.android.kotlin.type

fun main() {
    NullablePlayground.play()
}

private object NullablePlayground {

    fun play() {
        playWithQuestionMarkDot()
        playWithElvis()
        playWithAsQuestionMark()
        playWithExclamatoryMark()
    }

    //region ?.

    private fun playWithQuestionMarkDot() {
        println("NullablePlayground.playWithQuestionMarkDot()")

        val s1: String? = null
        println(s1?.length)

        val s2: String? = "abc"
        println(s2?.length)
    }

    //endregion ?.

    //region ?:

    private fun playWithElvis() {
        println("NullablePlayground.playWithElvis()")

        val s1: String? = null
        println(s1 ?: "null string")

        val s2: String? = "abc"
        println(s2 ?: "null string")
    }

    //endregion ?:

    //region as?

    private fun playWithAsQuestionMark() {
        println("NullablePlayground.playWithAsQuestionMark()")

        safeLength(null)
        safeLength(123)
        safeLength("abc")
    }

    private fun safeLength(param: Any?) {
        val s = param as? String
        println(s?.length ?: "not supported")
    }

    //endregion as?

    //region !!

    private fun playWithExclamatoryMark() {
        println("NullablePlayground.playWithExclamatoryMark()")

        val s1: String? = "abc"
        println(s1!!.length)

        /*
        KotlinNullPointerException
        val s2 : String? = null
        println(s2!!.length)
         */
    }

    //endregion !!
}