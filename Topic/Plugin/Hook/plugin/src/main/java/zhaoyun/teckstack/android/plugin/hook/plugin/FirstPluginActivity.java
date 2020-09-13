package zhaoyun.teckstack.android.plugin.hook.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import zhaoyun.teckstack.android.plugin.hook.library.PluginBaseActivity;

public class FirstPluginActivity extends PluginBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_plugin);

        String text = getIntent().getStringExtra("text");
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void startSecondPluginActivity(View view) {
        Intent intent = new Intent(this, SecondPluginActivity.class);
        intent.putExtra("text", "Greetings from FirstPluginActivity");
        startActivity(intent);
    }
}
