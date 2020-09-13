package zhaoyun.teckstack.android.plugin.stub.plugin;

import zhaoyun.teckstack.android.plugin.stub.library.PluginBaseActivity;

import android.os.Bundle;
import android.widget.Toast;

public class SecondPluginActivity extends PluginBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_plugin);

        String text = getIntent().getStringExtra("text");
        Toast.makeText(mHostActivity, text, Toast.LENGTH_SHORT).show();
    }
}
