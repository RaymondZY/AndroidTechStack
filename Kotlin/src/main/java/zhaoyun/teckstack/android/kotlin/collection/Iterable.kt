package zhaoyun.teckstack.android.kotlin.collection

/**
 *
 * @author zhaoyun
 * @version 2020/11/21
 */

fun main() {
    IterablePlayground.play()
}

private object IterablePlayground {

    private data class Person(var name: String, var age: Int = 0)

    private data class Book(val authors: List<String>)

    fun play() {
        playWithMap()
        playWithFlatMap()
        playWithFlatten()
        playWithFilter()
        playWithMaxBy()
        playWithSequence()
    }

    //region map

    private fun playWithMap() {
        println("IterablePlayground.playWithMap()")

        val people = listOf(
            Person("Alice", 21),
            Person("Bob", 23)
        )
        val names = people.map(Person::name)
        val ages = people.map(Person::age)
        println(names)
        println(ages)
    }

    //endregion map

    //region flatMap

    private fun playWithFlatMap() {
        println("IterablePlayground.playWithFlatMap()")

        val books = listOf(
            Book(listOf("Jasper Fforde")),
            Book(listOf("Terry Pratchett")),
            Book(listOf("Terry Pratchett", "Neil Gaiman"))
        )
        println(books.flatMap(Book::authors))
        println(books.flatMap(Book::authors).toSet())
    }

    //endregion flatMap

    //region flatten

    private fun playWithFlatten() {
        println("IterablePlayground.playWithFlatten()")
        val listOfLists = listOf(
            listOf("a", "b", "c"),
            listOf("1", "2", "3"),
            listOf("!", "?", "~")
        )
        println(listOfLists.flatten())
    }

    //endregion flatten

    //region filter

    private fun playWithFilter() {
        println("IterablePlayground.playWithFilter()")
        val people = listOf(
            Person("Alice", 21),
            Person("Bob", 23)
        )
        val peopleWithAge21 = people.filter { it.age == 21 }
        println(peopleWithAge21)
    }

    //endregion filter

    // region maxBy

    private fun playWithMaxBy() {
        println("IterablePlayground.playWithMaxBy()")

        val personList = listOf(
            Person("zhaoyun", 30),
            Person("xuxu", 9),
            Person("maodou", 0)
        )
        println(personList.maxByOrNull(Person::age))
    }

    //endregion maxBy

    //region sequence

    private fun playWithSequence() {
        println("IterablePlayground.playWithSequence()")

        val bigPersonList = mutableListOf<Person>()
        for (i in 0..100000) {
            bigPersonList.add(Person("person $i"))
        }

        val filterHalf = { index: Int, _: String -> index % 2 == 0 }
        bigPersonList.asSequence()
            .map(Person::name)
            .filterIndexed(filterHalf)
            .filterIndexed(filterHalf)
            .filterIndexed(filterHalf)
            .toList()
    }

    //region sequence
}