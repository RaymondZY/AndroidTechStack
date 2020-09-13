package zhaoyun.techstack.android.draw.bezier;

import androidx.appcompat.app.AppCompatActivity;
import zhaoyun.techstack.android.draw.R;

import android.os.Bundle;

public class BezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
        setTitle("Bezier");
    }
}
