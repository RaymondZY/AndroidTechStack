package zhaoyun.techstack.android.rxjava.diy

import java.util.concurrent.Executors

/**
 *
 * @author zhaoyun
 * @version 2020/11/15
 */
class Schedulers {

    companion object {

        val IO: Scheduler = Scheduler(Executors.newSingleThreadExecutor {
            Thread(it, "IO")
        })

        val COMPUTATION: Scheduler = Scheduler(Executors.newSingleThreadExecutor {
            Thread(it, "COMPUTATION")
        })

        val MAIN: Scheduler = Scheduler(Executors.newSingleThreadExecutor {
            Thread(it, "MAIN")
        })
    }
}