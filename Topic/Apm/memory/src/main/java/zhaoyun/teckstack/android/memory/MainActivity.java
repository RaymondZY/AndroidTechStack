package zhaoyun.teckstack.android.memory;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_start_leak_activity).setOnClickListener(view -> startActivity(new Intent(this, LeakActivity.class)));
    }
}
