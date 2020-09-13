package zhaoyun.techstack.android.appbarlayout;

import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;

import androidx.appcompat.app.AppCompatActivity;

public class EntranceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        findViewById(R.id.button_scroll).setOnClickListener(view -> MainActivity.startActivity(
                this,
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
        ));

        findViewById(R.id.button_scroll_enterAlways).setOnClickListener(view -> MainActivity.startActivity(
                this,
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
        ));

        findViewById(R.id.button_scroll_enterAlways_enterAlwaysCollapsed).setOnClickListener(view -> MainActivity.startActivity(
                this,
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED
        ));

        findViewById(R.id.button_scroll_enterAlways_exitUtilCollapsed).setOnClickListener(view -> MainActivity.startActivity(
                this,
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
        ));

        findViewById(R.id.button_scroll_enterAlways_snap).setOnClickListener(view -> MainActivity.startActivity(
                this,
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
        ));
    }
}
