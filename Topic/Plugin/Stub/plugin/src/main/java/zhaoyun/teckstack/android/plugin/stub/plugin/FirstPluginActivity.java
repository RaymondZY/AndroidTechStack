package zhaoyun.teckstack.android.plugin.stub.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import zhaoyun.teckstack.android.plugin.stub.library.PluginBaseActivity;

public class FirstPluginActivity extends PluginBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_plugin);

        String text = getIntent().getStringExtra("text");
        Toast.makeText(mHostActivity, text, Toast.LENGTH_SHORT).show();

        findViewById(R.id.button_start_second_plugin_activity).setOnClickListener(view -> startSecondPluginActivity());
    }

    private void startSecondPluginActivity() {
        Intent intent = new Intent(mHostActivity, SecondPluginActivity.class);
        intent.putExtra("text", "Greetings from FirstPluginActivity");
        startActivity(intent);
    }
}
