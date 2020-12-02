package zhaoyun.techstack.android.rxjava.error_handling.api

import io.reactivex.rxjava3.core.Observable
import zhaoyun.techstack.android.rxjava.error_handling.bean.User
import zhaoyun.techstack.android.rxjava.error_handling.bean.Game

/**
 *
 * @author zhaoyun
 * @version 2020/12/3
 */
object ApiManager {

    fun getUser(): Observable<User> {
        println("ApiManager.getUser()")

        return Observable.just(User("zhaoyun"))
    }

    fun getUserError(): Observable<User> {
        println("ApiManager.getUserError()")

        return Observable.error(ApiException(
            ApiException.ERROR_CODE_FOUND_NO_USER,
            "found no user"
        ))
    }

    fun getGame(user: User): Observable<Pair<User, Game>> {
        println("ApiManager.getGame()")

        return Observable.just(Game("Hearth Stone"))
            .map { user to it }
    }

    fun getGameLoL(user: User): Observable<Pair<User, Game>> {
        println("ApiManager.getGameLoL()")

        return Observable.just(Game("LoL"))
            .map { user to it }
    }

    fun getGameError(): Observable<Game> {
        println("ApiManager.getGameError()")

        return Observable.error(ApiException(
            ApiException.ERROR_CODE_FOUND_NO_GAME,
            "found no game"
        ))
    }
}