package zhaoyun.teckstack.android.memory;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class LeakActivity extends AppCompatActivity {

    private LeakHandler mLeakHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

        HandlerThread handlerThread = new HandlerThread("Leak");
        handlerThread.start();
        mLeakHandler = new LeakHandler(handlerThread.getLooper());
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    private class LeakHandler extends Handler {

        private LeakHandler(Looper looper) {
            super(looper);
        }
    }
}