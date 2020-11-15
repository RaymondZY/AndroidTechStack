package zhaoyun.techstack.android.rxjava.diy

import java.util.concurrent.ExecutorService

/**
 *
 * @author zhaoyun
 * @version 2020/11/15
 */
class Scheduler(private val executor: ExecutorService) {

    fun createWorker(): Worker {
        return Worker(executor)
    }

    class Worker(private val executor: ExecutorService) {

        fun schedule(runnable: Runnable) {
            executor.execute(runnable)
        }
    }
}