package zhaoyun.techstack.android.rxjava.diy

/**
 *
 * @author zhaoyun
 * @version 2020/11/14
 */

fun main() {
    Observable.create(object : Observable.ObservableOnSubscribe<Int> {
        override fun subscribe(observer: Observer<Int>) {
            println("${Thread.currentThread().name} : subscribe")
            observer.onNext(1)
            observer.onNext(2)
            observer.onNext(3)
            observer.onComplete()
        }
    }).subscribeOn(
        Schedulers.IO
    ).observeOn(
        Schedulers.COMPUTATION
    ).map(object : Observable.Transform<Int, String> {
        override fun apply(from: Int): String {
            println("${Thread.currentThread().name} : transform")
            return "String : $from"
        }
    }).observeOn(
        Schedulers.MAIN
    ).subscribe(object : Observer<String> {
        override fun onSubscribe() {
            println("${Thread.currentThread().name} : onSubscribe()")
        }

        override fun onNext(value: String) {
            println("${Thread.currentThread().name} : onNext() value = $value")
        }

        override fun onError(throwable: Throwable) {
            println("${Thread.currentThread().name} : onError() throwable = $throwable")
        }

        override fun onComplete() {
            println("${Thread.currentThread().name} : onComplete()")
        }
    })
}