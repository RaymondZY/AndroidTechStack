package zhaoyun.techstack.android.draw.shader;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import zhaoyun.techstack.android.draw.R;

public class ShaderExampleActivity extends AppCompatActivity {

    public static final String INTENT_KEY_INDEX = "intent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int exampleIndex = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            exampleIndex = extras.getInt(INTENT_KEY_INDEX);
        }
        switch (exampleIndex) {
            case 1:
                setContentView(R.layout.activity_shader_example1);
                break;

            case 2:
                setContentView(R.layout.activity_shader_example2);
                break;

            case 3:
                setContentView(R.layout.activity_shader_example3);
                break;
        }
    }
}
