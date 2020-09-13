package zhaoyun.techstack.android.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);

        SharedPreferences.Editor edit = sharedPreferences.edit();

        // thread-safe
        findViewById(R.id.button_add_sp).setOnClickListener(view -> {
            for (int i = 0; i < 10; i++) {
                final int index = i;
                new Thread(() -> {
                    edit.putString("test" + index, "test" + index);
                    edit.apply();
                }).start();
            }
        });
    }
}
