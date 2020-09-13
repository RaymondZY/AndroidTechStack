package zhaoyun.techstack.android.animation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class EntranceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        findViewById(R.id.button_view_animation).setOnClickListener(view -> startActivity(new Intent(this, ViewAnimationActivity.class)));
        findViewById(R.id.button_frame_animation).setOnClickListener(view -> startActivity(new Intent(this, FrameAnimationActivity.class)));
        findViewById(R.id.button_property_animation).setOnClickListener(view -> startActivity(new Intent(this, PropertyAnimationActivity.class)));
    }
}
