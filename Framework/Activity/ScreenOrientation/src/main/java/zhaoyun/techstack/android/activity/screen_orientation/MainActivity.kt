package zhaoyun.techstack.android.activity.screen_orientation

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("zhaoyun", "MainActivity.onCreate() requestedOrientation = $requestedOrientation")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Orientation.values()
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val orientation = adapter.getItem(position)
                orientation?.let { requestedOrientation = it.code }
                Log.d("zhaoyun", "onItemClicked orientation = $orientation")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("zhaoyun", "MainActivity.onConfigurationChanged() requestedOrientation = $requestedOrientation")
    }
}

enum class Orientation(val code: Int) {
    SCREEN_ORIENTATION_UNSET(-2),
    SCREEN_ORIENTATION_UNSPECIFIED(-1),
    SCREEN_ORIENTATION_LANDSCAPE(0),
    SCREEN_ORIENTATION_PORTRAIT(1),
    SCREEN_ORIENTATION_USER(2),
    SCREEN_ORIENTATION_BEHIND(3),
    SCREEN_ORIENTATION_SENSOR(4),
    SCREEN_ORIENTATION_NOSENSOR(5),
    SCREEN_ORIENTATION_SENSOR_LANDSCAPE(6),
    SCREEN_ORIENTATION_SENSOR_PORTRAIT(7),
    SCREEN_ORIENTATION_REVERSE_LANDSCAPE(8),
    SCREEN_ORIENTATION_REVERSE_PORTRAIT(9),
    SCREEN_ORIENTATION_FULL_SENSOR(10),
    SCREEN_ORIENTATION_USER_LANDSCAPE(11),
    SCREEN_ORIENTATION_USER_PORTRAIT(12),
    SCREEN_ORIENTATION_FULL_USER(13),
    SCREEN_ORIENTATION_LOCKED(14)
}