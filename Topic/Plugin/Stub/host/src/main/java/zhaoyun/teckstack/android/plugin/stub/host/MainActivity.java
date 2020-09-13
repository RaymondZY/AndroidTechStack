package zhaoyun.teckstack.android.plugin.stub.host;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import zhaoyun.teckstack.android.plugin.stub.library.PluginHostActivity;
import zhaoyun.teckstack.android.plugin.stub.library.PluginLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadPlugin(View view) {
        PluginLoader.getInstance().loadPlugin(this);
    }

    public void startPlugin(View view) {
        String className = PluginLoader.getInstance().getLauncherActivityClassName(this);

        Log.d("DEBUG", "MainActivity.startPlugin() className = " + className);

        Bundle extras = new Bundle();
        extras.putString("text", "Greetings from MainActivity.");

        if (className != null) {
            PluginHostActivity.startActivity(this, className, extras);
        }
    }
}
