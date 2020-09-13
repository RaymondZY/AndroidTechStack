package zhaoyun.teckstack.android.plugin.hook.plugin;

import android.os.Bundle;
import android.widget.Toast;

import zhaoyun.teckstack.android.plugin.hook.library.PluginBaseActivity;

public class SecondPluginActivity extends PluginBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_plugin);

        String text = getIntent().getStringExtra("text");
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
