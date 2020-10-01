package zhaoyun.teckstack.android.kotlin.basic
fun main() {
    LambdaPlayground.play()
}

private object LambdaPlayground {

    fun play() {
        playWithLambda()
        playWithMap()
        playWithFlatMap()
        playWithFlatten()
        playWithFilter()
        playWithMaxBy()
        playWithSequence()
        playWithWith()
        playWithApply()
    }

    private fun playWithLambda() {
        println("playWithLambda()")
        val people = listOf(
            Person("Alice", 21),
            Person("Bob", 23)
        )

        people.maxBy({ p: Person -> p.age })
        people.maxBy() { p: Person -> p.age }
        people.maxBy { p: Person -> p.age }
        people.maxBy { p -> p.age }
        people.maxBy { it.age }
        people.maxBy(Person::age)
    }

    private fun playWithMap() {
        println("LambdaPlayGround.playWithMap()")
        val people = listOf(
            Person("Alice", 21),
            Person("Bob", 23)
        )
        val names = people.map(Person::name)
        val ages = people.map(Person::age)
        println(names)
        println(ages)
    }

    private class Book(val authors: List<String>)

    private fun playWithFlatMap() {
        println("LambdaPlayGround.playWithFlatMap()")
        val books = listOf(
            Book(listOf("Jasper Fforde")),
            Book(listOf("Terry Pratchett")),
            Book(listOf("Terry Pratchett", "Neil Gaiman"))
        )
        println(books.flatMap(Book::authors))
        println(books.flatMap(Book::authors).toSet())
    }

    private fun playWithFlatten() {
        println("LambdaPlayGround.playWithFlatten()")
        val listOfLists = listOf(
            listOf("a", "b", "c"),
            listOf("1", "2", "3"),
            listOf("!", "?", "~")
        )
        println(listOfLists.flatten())
    }

    private fun playWithFilter() {
        println("LambdaPlayGround.playWithFilter()")
        val people = listOf(
            Person("Alice", 21),
            Person("Bob", 23)
        )
        val peopleWithAge21 = people.filter { it.age == 21 }
        println(peopleWithAge21)
    }

    private data class Person(var name: String, var age: Int = 0)

    private fun playWithMaxBy() {
        println("LambdaPlayGround.playWithMaxBy()")
        val personList = listOf(
            Person("zhaoyun", 30),
            Person("xuxu", 9),
            Person("maodou", 0)
        )
        println(personList.maxBy(Person::age))
    }

    private fun playWithSequence() {
        println("LambdaPlayGround.playWithSequence()")
        val bigPersonList = ArrayList<Person>()
        for (i in 0..100000) {
            bigPersonList.add(Person("person $i"))
        }
        val filterHalf = { index: Int, _: String ->
            index % 2 == 0
        }
        var startTime = System.currentTimeMillis()
        bigPersonList.map(Person::name)
            .filterIndexed(filterHalf)
            .filterIndexed(filterHalf)
            .filterIndexed(filterHalf)
        println("time cost = ${System.currentTimeMillis() - startTime}")

        startTime = System.currentTimeMillis()
        bigPersonList.asSequence()
            .map(Person::name)
            .filterIndexed(filterHalf)
            .filterIndexed(filterHalf)
            .filterIndexed(filterHalf)
            .toList()
        println("time cost with sequence = ${System.currentTimeMillis() - startTime}")
    }

    private fun playWithWith() {
        println("LambdaPlayGround.playWithWith()")
        val zhaoyun = Person("zhaoyun", 30)
        with(zhaoyun) {
            name = "zhaoyun31"
            age = 31
        }
        println(zhaoyun)
    }

    private fun playWithApply() {
        println("LambdaPlayGround.playWithApply()")
        val zhaoyun = Person("zhaoyun", 30)
        zhaoyun.apply {
            name = "zhaoyun31"
            age = 31
        }
        println(zhaoyun)
    }
}