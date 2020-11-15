package zhaoyun.techstack.android.rxjava.diy

/**
 *
 * @author zhaoyun
 * @version 2020/11/14
 */
interface Observer<T> {

    fun onSubscribe()

    fun onNext(value: T)

    fun onError(throwable: Throwable)

    fun onComplete()
}