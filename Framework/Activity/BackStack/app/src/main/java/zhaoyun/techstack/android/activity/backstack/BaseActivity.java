package zhaoyun.techstack.android.activity.backstack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author zhaoyun
 * @version 2020/4/28
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(getClass().getSimpleName(), "onCreate() savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.d(getClass().getSimpleName(), "onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(getClass().getSimpleName(), "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(getClass().getSimpleName(), "onDestroy()");
        super.onDestroy();
    }

    protected void init() {
        findViewById(R.id.button_standard).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, StandardActivity.class);
                    startActivity(intent);
                }
        );

        findViewById(R.id.button_single_top).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, SingleTopActivity.class);
                    startActivity(intent);
                }
        );

        findViewById(R.id.button_single_task).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, SingleTaskActivity.class);
                    startActivity(intent);
                }
        );

        findViewById(R.id.button_single_task_with_new_affinity).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, SingleTaskWithNewAffinityActivity.class);
                    startActivity(intent);
                }
        );

        findViewById(R.id.button_single_instance).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, SingleInstanceActivity.class);
                    startActivity(intent);
                }
        );

        setTitle(getClass().getSimpleName() + " " + getTaskId() + " " + hashCode());
        Log.i(getClass().getSimpleName(), getClass().getSimpleName() + " " + getTaskId() + " " + hashCode());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(getClass().getSimpleName(), "onNewIntent()");
    }
}
