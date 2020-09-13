package zhaoyun.teckstack.android.plugin.stub.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class PluginHostActivity extends AppCompatActivity {

    private static final String INTENT_EXTRA_CLASS_NAME = "class_name";

    public static void startActivity(Context context, String className, Bundle extras) {
        Intent intent = new Intent(context, PluginHostActivity.class);
        intent.putExtra(INTENT_EXTRA_CLASS_NAME, className);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    private PluginActivity mPluginActivity;

    @Override
    public ClassLoader getClassLoader() {
        return PluginLoader.getInstance().getDexClassLoader();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG", "PluginHostActivity.onCreate()");
        super.onCreate(savedInstanceState);

        initPlugin();
        mPluginActivity.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.d("DEBUG", "PluginHostActivity.onStart()");
        super.onStart();
        mPluginActivity.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("DEBUG", "PluginHostActivity.onResume()");
        super.onResume();
        mPluginActivity.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("DEBUG", "PluginHostActivity.onPause()");
        super.onPause();
        mPluginActivity.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("DEBUG", "PluginHostActivity.onStop()");
        super.onStop();
        mPluginActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("DEBUG", "PluginHostActivity.onDestroy()");
        super.onDestroy();
        mPluginActivity.onDestroy();
    }

    private void initPlugin() {
        String className = getIntent().getStringExtra(INTENT_EXTRA_CLASS_NAME);
        if (className != null) {
            try {
                Class<?> activityClass = getClassLoader().loadClass(className);
                Object activity = activityClass.newInstance();
                if (activity instanceof PluginActivity) {
                    mPluginActivity = (PluginActivity) activity;
                    mPluginActivity.installHostActivity(this);
                } else {
                    Log.e("DEBUG", "PluginHostActivity.initPlugin() PluginActivity not found");
                }
            } catch (Exception e) {
                Log.e("DEBUG", e.getMessage(), e);
            }
        }
    }
}
