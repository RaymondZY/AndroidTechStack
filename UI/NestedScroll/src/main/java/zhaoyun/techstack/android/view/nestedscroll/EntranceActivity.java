package zhaoyun.techstack.android.view.nestedscroll;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import zhaoyun.techstack.android.view.nestedscroll.approach1.Approach1Activity;
import zhaoyun.techstack.android.view.nestedscroll.approach2.Approach2Activity;

public class EntranceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        findViewById(R.id.button_approach_1).setOnClickListener(view -> startActivity(new Intent(this, Approach1Activity.class)));
        findViewById(R.id.button_approach_2).setOnClickListener(view -> startActivity(new Intent(this, Approach2Activity.class)));
    }
}
