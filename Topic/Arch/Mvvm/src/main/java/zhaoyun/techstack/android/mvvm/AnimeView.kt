package zhaoyun.techstack.android.mvvm

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

class AnimeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val TAG = "AnimeView"
    }

    private val lifecycleOwner: LifecycleOwner? = context as? LifecycleOwner

    fun setData(liveData: LiveData<List<Anime>>) {
        lifecycleOwner?.let { lifecycleOwner ->
            liveData.observe(lifecycleOwner) { list ->
                list.forEach { anime ->
                    Log.d(TAG, anime.toString())
                }
            }
        }
    }
}