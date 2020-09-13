package zhaoyun.teckstack.android.plugin.hook.library;

import android.app.Activity;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author zhaoyun
 * @version 2020/6/30
 */
public class PluginBaseActivity extends Activity {

    @Override
    public Resources getResources() {
        return PluginLoader.getInstance().getResources();
    }
}
