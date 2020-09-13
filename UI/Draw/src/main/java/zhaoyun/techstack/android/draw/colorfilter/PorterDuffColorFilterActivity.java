package zhaoyun.techstack.android.draw.colorfilter;

import androidx.appcompat.app.AppCompatActivity;
import zhaoyun.techstack.android.draw.R;

import android.os.Bundle;

public class PorterDuffColorFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porter_duff_color_filter);
        setTitle("PorterDuff ColorFilter");
    }
}
