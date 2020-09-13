package zhaoyun.techstack.android.handler;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private static class BackgroundRunnable implements Runnable {

        private WeakReference<Context> mContextWeakReference;

        private BackgroundRunnable(WeakReference<Context> contextWeakReference) {
            mContextWeakReference = contextWeakReference;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Context context = mContextWeakReference.get();
            if (context != null) {
                Toast.makeText(context, "Job done.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "context is not null");
            } else {
                Log.i(TAG, "context is null");
            }
        }
    }

    private void initView() {
        HandlerThread handlerThread = new HandlerThread("background");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        HandlerThread leakHandlerThread = new HandlerThread("leak");
        leakHandlerThread.start();
        Handler leakHandler = new LeakHandler(leakHandlerThread.getLooper());

        findViewById(R.id.button_background_task).setOnClickListener(
                view -> handler.post(
                        new BackgroundRunnable(new WeakReference<>(this))
                )
        );

        findViewById(R.id.button_background_task_leak_runnable).setOnClickListener(
                view -> handler.post(
                        () -> {
                            try {
                                Thread.sleep(50000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(this, "Job done.", Toast.LENGTH_SHORT).show();
                        }
                )
        );

        findViewById(R.id.button_background_task_leak_handler).setOnClickListener(
                view -> leakHandler.post(
                        new BackgroundRunnable(new WeakReference<>(this))
                )
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy()");
    }

    private class LeakHandler extends Handler {
        private LeakHandler(Looper looper) {
            super(looper);
        }
    }
}
