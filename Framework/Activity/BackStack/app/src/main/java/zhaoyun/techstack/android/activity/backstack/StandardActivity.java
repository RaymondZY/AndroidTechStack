package zhaoyun.techstack.android.activity.backstack;

import android.os.Bundle;

public class StandardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        init();
    }
}
