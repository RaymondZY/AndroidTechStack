package zhaoyun.techstack.android.mvvm

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable

class AnimeModel {

    fun fetchNewSeason(): Observable<MutableList<Anime>> {
        return Observable.just(mockAnimeList())
    }

    private fun mockAnimeList(): @NonNull MutableList<Anime> {
        return mutableListOf(
            Anime("路人女主的养成方法 Fine"),
            Anime("半妖的夜叉姬"),
            Anime("总之就是非常可爱")
        )
    }
}