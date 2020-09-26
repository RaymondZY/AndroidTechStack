package zhaoyun.techstack.android.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import zhaoyun.techstack.mvvm.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animeViewModel: AnimeViewModel = ViewModelProviders.of(this).get(AnimeViewModel::class.java)

        anime_view.apply {
            setOnClickListener {
                animeViewModel.fetchNewSeason()
            }
            setData(animeViewModel.animeList)
        }
    }
}
