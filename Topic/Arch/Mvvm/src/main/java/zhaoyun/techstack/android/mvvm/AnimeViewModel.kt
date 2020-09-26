package zhaoyun.techstack.android.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class AnimeViewModel : ViewModel() {

    private val animeModel: AnimeModel = AnimeModel()

    private val _animeList: MutableLiveData<List<Anime>> = MutableLiveData()
    val animeList: LiveData<List<Anime>>
        get() = _animeList

    fun fetchNewSeason() {
        animeModel.fetchNewSeason()
            .delay(2000, TimeUnit.MILLISECONDS, Schedulers.io())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                _animeList.value = list
            }
    }
}