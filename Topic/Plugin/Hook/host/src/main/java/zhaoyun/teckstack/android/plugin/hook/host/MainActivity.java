package zhaoyun.teckstack.android.plugin.hook.host;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import zhaoyun.teckstack.android.plugin.hook.library.PluginLoader;

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
        Bundle extras = new Bundle();
        extras.putString("text", "Greetings from MainActivity.");

        Intent intent = new Intent();
        ComponentName componentName = new ComponentName(
                "zhaoyun.teckstack.android.plugin.hook.plugin",
                "zhaoyun.teckstack.android.plugin.hook.plugin.FirstPluginActivity"
        );
        intent.setComponent(componentName);
        startActivity(intent);
    }
}
