package zhaoyun.teckstack.android.kotlin.type

/**
 *
 * @author zhaoyun
 * @version 2020/11/21
 */

fun main() {
    GenericsPlayground.play()
}

private object GenericsPlayground {

    fun play() {
        playWithGenerics()
        playWithInAndOutGenerics()
    }

    //region Generics

    // 类的泛型参数写在ClassName之后
    private class ValueTransformer<T>(private val value: T) {

        // 方法泛型参数写在fun关键字后
        fun <R> transform(transform: (T) -> R): R {
            return transform(value)
        }
    }

    private fun playWithGenerics() {
        println("GenericsPlayground.playWithGenerics()")

        val valueTransformer = ValueTransformer(1)
        val transformed = valueTransformer.transform { "value = $it" }
        println(transformed)
    }

    //endregion Generics

    //region In and Out Generics

    private open class Person
    private open class Student : Person()
    private open class HighSchoolStudent: Student()

    private interface Setter<T> {

        fun setValue(value: T)
    }

    private interface Getter<T> {

        fun getValue() : T
    }

    private class ValueHolder<T>(private var value: T) {

        // 逆变 同Java中的 <? super T>
        fun registerSetter(setter: Setter<in T>) {
            setter.setValue(value)
        }

        // 协变 同Java中的 <? extends T>
        fun registerGetter(getter: Getter<out T>) {
            value = getter.getValue()
            println("value changed to $value")
        }
    }

    private fun playWithInAndOutGenerics() {
        println("GenericsPlayground.playWithInAndOutGenerics()")

        val valueHolder : ValueHolder<Student> = ValueHolder(Student())

        // in的泛型接口，可以传入比T更原始的父类P
        // ValueHolder中调用它时，可以把入参T向上转型成P
        valueHolder.registerSetter(object : Setter<Person> {
            override fun setValue(value: Person) {
                println("value changed to $value")
            }
        })
        /*
        无法编译，ValueHolder调用它时，无法把入参T向下转型成C
        holder.registerSetter(object : Setter<HighSchoolStudent> {})
         */

        // out的泛型接口，可以传入比T更精确的子类C
        // ValueHolder使用它的返回结果C时，可以把C向上转型成T
        valueHolder.registerGetter(object : Getter<HighSchoolStudent> {
            override fun getValue(): HighSchoolStudent {
                return HighSchoolStudent()
            }
        })
        /*
        无法编译，ValueHolder调用它时，无法把返回结果P向下转型成T
        holder.registerGetter(object : Getter<Person> {})
         */
    }

    //endregion In and Out Generics
}