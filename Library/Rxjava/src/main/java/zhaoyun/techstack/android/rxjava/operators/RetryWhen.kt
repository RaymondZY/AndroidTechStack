package zhaoyun.techstack.android.rxjava.operators

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun main() {
    var value = 0
    Observable.create<Int> { emitter ->
        value++
        println("${Thread.currentThread().name} subscribing ..")
        println("${Thread.currentThread().name} emit($value)")
        emitter.onNext(value)
        emitter.onComplete()
    }.subscribeOn(
        Schedulers.io()
    ).map {
        println("${Thread.currentThread().name} mapValue")
        if (it < 100) {
            throw RuntimeException("not yet")
        } else {
            it
        }
    }.retryWhen { attempts ->
        println("${Thread.currentThread().name} retryWhen")
        attempts.zipWith(
            Observable.range(1, 3),
            { _, i -> i }
        ).flatMap { i ->
            Observable.timer(
                i.toLong(),
                TimeUnit.SECONDS
            ).map(Long::toString)
        }.concatWith(
            Observable.error(RuntimeException(""))
        ).doOnNext {
            println("${Thread.currentThread().name} retryWhen onNext()")
        }
    }.onErrorReturn {
        100
    }.doOnNext {
        println("${Thread.currentThread().name} doOnNext")
    }.blockingSubscribe(
        {
            println("${Thread.currentThread().name} onNext $it")
        }, {
            println("${Thread.currentThread().name} onError $it")
        }, {
            println("${Thread.currentThread().name} onComplete")
        }
    )
}