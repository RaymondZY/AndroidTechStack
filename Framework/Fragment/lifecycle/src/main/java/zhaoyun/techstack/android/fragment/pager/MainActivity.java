package zhaoyun.techstack.android.fragment.pager;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG", "MainActivity.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Log.d("DEBUG", "MainActivity.onCreate() returned");
    }

    @Override
    protected void onStart() {
        Log.d("DEBUG", "MainActivity.onStart()");
        super.onStart();
        Log.d("DEBUG", "MainActivity.onStart() returned");
    }

    @Override
    protected void onResume() {
        Log.d("DEBUG", "MainActivity.onResume()");
        super.onResume();
        Log.d("DEBUG", "MainActivity.onResume() returned");
    }

    @Override
    protected void onPause() {
        Log.d("DEBUG", "MainActivity.onPause()");
        super.onPause();
        Log.d("DEBUG", "MainActivity.onPause() returned");
    }

    @Override
    protected void onStop() {
        Log.d("DEBUG", "MainActivity.onStop()");
        super.onStop();
        Log.d("DEBUG", "MainActivity.onStop() returned");
    }

    @Override
    protected void onDestroy() {
        Log.d("DEBUG", "MainActivity.onDestroy()");
        super.onDestroy();
        Log.d("DEBUG", "MainActivity.onDestroy() returned");
    }

    private void initView() {
        findViewById(R.id.button_add).setOnClickListener(view -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, new DynamicFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });
        findViewById(R.id.button_replace).setOnClickListener(view -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new DynamicFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }
}
