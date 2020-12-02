package zhaoyun.techstack.android.rxjava.error_handling

import zhaoyun.techstack.android.rxjava.error_handling.api.ApiException
import zhaoyun.techstack.android.rxjava.error_handling.api.ApiManager

/**
 *
 * @author zhaoyun
 * @version 2020/12/3
 */
fun main() {
    ApiManager.getUser()
        .flatMap {
            ApiManager.getGame(it)
        }.subscribe({ pair->
            println(pair.first)
            println(pair.second)
        }, {
            if (it is ApiException) {
                println("errorCode = ${it.errorCode}")
                println("errorMessage = ${it.errorMessage}")
            } else {
                println(it)
            }
        })

    ApiManager.getUser()
        .flatMap {
            ApiManager.getGameLoL(it)
        }.map { pair->
            if (pair.second.name == "LoL") {
                throw ApiException(
                    ApiException.ERROR_CODE_LOL_HATER,
                    "I don't play LoL."
                )
            } else {
                pair
            }
        }.subscribe({ pair->
            println(pair.first)
            println(pair.second)
        }, {
            if (it is ApiException) {
                println("errorCode = ${it.errorCode}")
                println("errorMessage = ${it.errorMessage}")
            } else {
                println(it)
            }
        })

    ApiManager.getUserError()
        .flatMap {
            ApiManager.getGame(it)
        }.subscribe({ pair ->
            println(pair.first)
            println(pair.second)
        }, {
            if (it is ApiException) {
                println("errorCode = ${it.errorCode}")
                println("errorMessage = ${it.errorMessage}")
            } else {
                println(it)
            }
        })

    ApiManager.getUser()
        .flatMap {
            ApiManager.getGameError()
        }.subscribe({
            println(it)
        }, {
            if (it is ApiException) {
                println("errorCode = ${it.errorCode}")
                println("errorMessage = ${it.errorMessage}")
            } else {
                println(it)
            }
        })

    ApiManager.getUserError()
        .flatMap {
            ApiManager.getGameError()
        }.subscribe({
            println(it)
        }, {
            if (it is ApiException) {
                println("errorCode = ${it.errorCode}")
                println("errorMessage = ${it.errorMessage}")
            } else {
                println(it)
            }
        })
}