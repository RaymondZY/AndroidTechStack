package zhaoyun.techstack.android.rxjava.diy

/**
 *
 * @author zhaoyun
 * @version 2020/11/14
 */
class Observable<T>(private val observableOnSubscribe: ObservableOnSubscribe<T>) {

    companion object {

        fun <T> create(observableOnSubscribe: ObservableOnSubscribe<T>): Observable<T> {
            return Observable(observableOnSubscribe)
        }
    }

    fun subscribe(observer: Observer<T>) {
        observableOnSubscribe.subscribe(observer)
    }

    fun <R> map(transform: Transform<T, R>): Observable<R> {
        return create(object : ObservableOnSubscribe<R> {
            override fun subscribe(observer: Observer<R>) {
                this@Observable.subscribe(object : Observer<T> {
                    override fun onSubscribe() {
                        observer.onSubscribe()
                    }

                    override fun onNext(value: T) {
                        observer.onNext(transform.apply(value))
                    }

                    override fun onError(throwable: Throwable) {
                        observer.onError(throwable)
                    }

                    override fun onComplete() {
                        observer.onComplete()
                    }
                })
            }
        })
    }

    fun subscribeOn(scheduler: Scheduler): Observable<T> {
        return create(object: ObservableOnSubscribe<T> {
            override fun subscribe(observer: Observer<T>) {
                scheduler.createWorker().schedule {
                    this@Observable.observableOnSubscribe.subscribe(observer)
                }
            }
        })
    }

    fun observeOn(scheduler: Scheduler): Observable<T> {
        return create(object : ObservableOnSubscribe<T> {
            override fun subscribe(observer: Observer<T>) {
                this@Observable.observableOnSubscribe.subscribe(object : Observer<T> {
                    override fun onSubscribe() {
                        scheduler.createWorker().schedule { observer.onSubscribe() }
                    }

                    override fun onNext(value: T) {
                        scheduler.createWorker().schedule { observer.onNext(value) }
                    }

                    override fun onError(throwable: Throwable) {
                        scheduler.createWorker().schedule { observer.onError(throwable) }
                    }

                    override fun onComplete() {
                        scheduler.createWorker().schedule { observer.onComplete() }
                    }
                })
            }
        })
    }

    interface ObservableOnSubscribe<T> {

        fun subscribe(observer: Observer<T>)
    }

    interface Transform<FROM, TO> {

        fun apply(from: FROM): TO
    }
}