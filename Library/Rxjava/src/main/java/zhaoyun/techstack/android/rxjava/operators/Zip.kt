package zhaoyun.techstack.android.rxjava.operators

import io.reactivex.rxjava3.core.Observable

/**
 *
 * @author zhaoyun
 * @version 2020/11/18
 */

fun main() {
    val observable1 = Observable.create<Int> { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
        emitter.onComplete()
    }
    val observable2 = Observable.create<Int> { emitter ->
        emitter.onNext(4)
        emitter.onNext(5)
        emitter.onNext(6)
        // 这个值会被抛弃
        emitter.onNext(7)
        emitter.onComplete()
    }
    observable1.zipWith(observable2) { value1, value2->
        println("value1 = $value1 value2 = $value2")
        value1 + value2
    }.subscribe(::println)
}