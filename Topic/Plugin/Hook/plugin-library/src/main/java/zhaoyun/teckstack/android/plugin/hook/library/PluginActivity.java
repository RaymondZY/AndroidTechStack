package zhaoyun.teckstack.android.plugin.hook.library;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author zhaoyun
 * @version 2020/6/30
 */
public interface PluginActivity {

    void installHostActivity(Activity hostActivity);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
