package zhaoyun.techstack.android.rxjava.error_handling.api

/**
 *
 * @author zhaoyun
 * @version 2020/12/3
 */
class ApiException(val errorCode: Int, val errorMessage: String) : Exception() {

    companion object {

        const val ERROR_CODE_FOUND_NO_USER = 1
        const val ERROR_CODE_FOUND_NO_GAME = 2
        const val ERROR_CODE_LOL_HATER = 3
    }
}