package zhaoyun.teckstack.android.plugin.stub.library;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Field;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author zhaoyun
 * @version 2020/6/30
 */
@SuppressLint("MissingSuperCall")
public class PluginBaseActivity extends AppCompatActivity implements PluginActivity {

    protected Activity mHostActivity;
    protected Context mPluginContext;

    @Override
    public void installHostActivity(Activity hostActivity) {
        Log.d("DEBUG", "PluginBaseActivity.installHostActivity()");
        mHostActivity = hostActivity;
        mPluginContext = new ContextThemeWrapper(hostActivity, 0);
        try {
            Field mResourcesField = ContextThemeWrapper.class.getDeclaredField("mResources");
            mResourcesField.setAccessible(true);
            mResourcesField.set(mPluginContext, PluginLoader.getInstance().getResources());
        } catch (Exception e) {
            Log.e("DEUBG", e.getMessage(), e);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG", "PluginBaseActivity.onCreate()");
    }

    @Override
    public void onStart() {
        Log.d("DEBUG", "PluginBaseActivity.onStart()");
    }

    @Override
    public void onResume() {
        Log.d("DEBUG", "PluginBaseActivity.onResume()");
    }

    @Override
    public void onPause() {
        Log.d("DEBUG", "PluginBaseActivity.onPause()");
    }

    @Override
    public void onStop() {
        Log.d("DEBUG", "PluginBaseActivity.onStop()");
    }

    @Override
    public void onDestroy() {
        Log.d("DEBUG", "PluginBaseActivity.onDestroy()");
    }

    @Override
    public void setContentView(int layoutResID) {
        Log.d("DEBUG", "PluginBaseActivity.setContentView()");
        View contentView = LayoutInflater.from(mPluginContext).inflate(layoutResID, null, false);
        mHostActivity.setContentView(contentView);
    }

    @Override
    public void startActivity(Intent intent) {
        ComponentName component = intent.getComponent();
        if (component != null) {
            PluginHostActivity.startActivity(mHostActivity, component.getClassName(), intent.getExtras());
        }
    }

    @Override
    public Intent getIntent() {
        return mHostActivity.getIntent();
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return mHostActivity.findViewById(id);
    }
}
