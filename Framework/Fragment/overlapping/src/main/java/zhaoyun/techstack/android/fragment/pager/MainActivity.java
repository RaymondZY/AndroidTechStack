package zhaoyun.techstack.android.fragment.pager;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private static int mInstanceCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG", "MainActivity.onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This will cause fragment overlapping
        /*
        createFragment();
         */

        // This will not cause fragment overlapping
        if (savedInstanceState == null) {
            createFragment();
        }

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

    private void createFragment() {
        mInstanceCount++;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, DynamicFragment.createInstance("DynamicFragment" + mInstanceCount))
                           .commit();
    }
}
