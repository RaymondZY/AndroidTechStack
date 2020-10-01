package zhaoyun.teckstack.android.kotlin.basic

fun main() {
    NullablePlayground.play()
}

private object NullablePlayground {

    fun play() {
        playWithQuestionMarkDot()
        playWithElvis()
        playWithAsQuestionMark()
        playWithExclamatoryMark()
        playWithLet()
        playWithLateInit()
    }

    private fun playWithQuestionMarkDot() {
        println("NullablePlayground.playWithQuestionMarkDot()")

        val s1: String? = null
        println(s1?.length)

        val s2: String? = "abc"
        println(s2?.length)
    }

    private fun playWithElvis() {
        println("NullablePlayground.playWithElvis()")

        val s1: String? = null
        println(s1 ?: "null string")

        val s2: String? = "abc"
        println(s2 ?: "null string")
    }

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

    private fun playWithLet() {
        println("NullablePlayground.playWithLet()")

        val s1: String? = "abc"
        s1?.let {
            println("first let")
        }

        val s2: String? = null
        s2?.let {
            println("second let")
        }
    }

    class Person {

        private lateinit var name: String

        fun initName() {
            name = "zhaoyun"
        }

        fun printName() {
            println(name)
        }
    }

    private fun playWithLateInit() {
        println("NullablePlayground.playWithLateInit()")

        val p1 = Person()
        p1.initName()
        p1.printName()

        val p2 = Person()
        // kotlin.UninitializedPropertyAccessException: lateinit property name has not been initialized
        p2.printName()
    }
}