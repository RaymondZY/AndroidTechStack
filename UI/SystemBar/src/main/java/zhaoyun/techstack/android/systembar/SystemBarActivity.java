package zhaoyun.techstack.android.systembar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SystemBarActivity extends AppCompatActivity {

    private static final String TAG = "SystemBarActivity";

    private static final String EXTRA_KEY_IMPLEMENTATION = "implementation";
    private static final String EXTRA_KEY_SET_STATUS_BAR = "set_status_bar";
    private static final String EXTRA_KEY_SET_NAVIGATION_BAR = "set_navigation_bar";
    private static final String EXTRA_KEY_FIT_WINDOW = "fit_window";
    private static final String EXTRA_KEY_ALPHA = "alpha";

    private Implementation mImplementation;
    private boolean mSetStatusBar;
    private boolean mSetNavigationBar;
    private boolean mFitWindow;
    private int mAlpha;
    private boolean mStatusBarLightMode;
    private boolean mNavigationBarLightMode;

    public enum Implementation {
        TRANSPARENT("完全透明"),
        SYSTEM_TRANSLUCENT("系统默认半透明"),
        CUSTOM_TRANSLUCENT("自定义半透明");

        private String mDescription;

        Implementation(String description) {
            this.mDescription = description;
        }

        @NonNull
        @Override
        public String toString() {
            return mDescription;
        }
    }

    public static void start(Context context, Implementation implementation, boolean setStatusBar, boolean setNavigationBar, boolean fitWindow, int alpha) {
        Intent intent = new Intent(context, SystemBarActivity.class);
        intent.putExtra(EXTRA_KEY_IMPLEMENTATION, implementation.ordinal());
        intent.putExtra(EXTRA_KEY_SET_STATUS_BAR, setStatusBar);
        intent.putExtra(EXTRA_KEY_SET_NAVIGATION_BAR, setNavigationBar);
        intent.putExtra(EXTRA_KEY_FIT_WINDOW, fitWindow);
        intent.putExtra(EXTRA_KEY_ALPHA, alpha);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_bar);

        mImplementation = Implementation.values()[getIntent().getIntExtra(EXTRA_KEY_IMPLEMENTATION, 0)];
        mSetStatusBar = getIntent().getBooleanExtra(EXTRA_KEY_SET_STATUS_BAR, true);
        mSetNavigationBar = getIntent().getBooleanExtra(EXTRA_KEY_SET_NAVIGATION_BAR, true);
        mFitWindow = getIntent().getBooleanExtra(EXTRA_KEY_FIT_WINDOW, true);
        mAlpha = getIntent().getIntExtra(EXTRA_KEY_ALPHA, 0);

        SeekBar alphaSeekBar = findViewById(R.id.seekBar_alpha);
        TextView alphaLabelTextView = findViewById(R.id.textView_alphaLabel);
        Switch statusBarLightModeSwitch = findViewById(R.id.switch_statusBarMode);
        Switch navigationBarLightModeSwitch = findViewById(R.id.switch_navigationBarMode);

        mStatusBarLightMode = statusBarLightModeSwitch.isChecked();
        mNavigationBarLightMode = navigationBarLightModeSwitch.isChecked();

        alphaSeekBar.setProgress(getIntent().getIntExtra(EXTRA_KEY_ALPHA, 0));
        alphaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAlpha = progress;
                updateSystemBar();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        statusBarLightModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mStatusBarLightMode = isChecked;
            updateSystemBar();
        });
        navigationBarLightModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mNavigationBarLightMode = isChecked;
            updateSystemBar();
        });

        if (Implementation.CUSTOM_TRANSLUCENT.equals(mImplementation)) {
            alphaLabelTextView.setVisibility(View.VISIBLE);
            alphaSeekBar.setVisibility(View.VISIBLE);
        } else {
            alphaLabelTextView.setVisibility(View.GONE);
            alphaSeekBar.setVisibility(View.GONE);
        }

        updateSystemBar();
    }

    private void updateSystemBar() {
        Log.i(TAG, "updateSystemBar()");
        Log.i(TAG, "mImplementation = " + mImplementation);
        Log.i(TAG, "mSetStatusBar = " + mSetStatusBar);
        Log.i(TAG, "mSetNavigationBar = " + mSetNavigationBar);
        Log.i(TAG, "mFitWindow = " + mFitWindow);
        Log.i(TAG, "mAlpha = " + mAlpha);
        Log.i(TAG, "mStatusBarLightMode = " + mStatusBarLightMode);
        Log.i(TAG, "mNavigationBarLightMode = " + mNavigationBarLightMode);

        switch (mImplementation) {
            case TRANSPARENT:
                SystemBarUtil.setTransparent(this, mSetStatusBar, mSetNavigationBar, mFitWindow);
                break;

            case SYSTEM_TRANSLUCENT:
                SystemBarUtil.setSystemTranslucent(this, mSetStatusBar, mSetNavigationBar, mFitWindow);
                break;

            case CUSTOM_TRANSLUCENT:
                SystemBarUtil.setCustomTranslucent(this, mSetStatusBar, mSetNavigationBar, mFitWindow, mAlpha);
                break;
        }

        SystemBarUtil.setDarkMode(this, mStatusBarLightMode, mNavigationBarLightMode);
    }
}
