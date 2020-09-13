package zhaoyun.techstack.android.tablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class EntranceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        findViewById(R.id.button_approach_1).setOnClickListener(view -> startActivity(new Intent(this, Approach1Activity.class)));
        findViewById(R.id.button_approach_2).setOnClickListener(view -> startActivity(new Intent(this, Approach2Activity.class)));
    }
}
