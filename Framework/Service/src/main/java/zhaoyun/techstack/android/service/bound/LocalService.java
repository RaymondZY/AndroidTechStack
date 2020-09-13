package zhaoyun.techstack.android.service.bound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author zhaoyun
 * @version 2018/10/24
 */
public class LocalService extends Service {

    private static final String TAG = LocalService.class.getSimpleName();

    public class LocalBinder extends Binder {

        public LocalService getService() {
            return LocalService.this;
        }
    }

    public interface GetTimeCallback {

        void onResult(long time);
    }

    private final IBinder mBinder = new LocalBinder();
    private Handler mWorkHandler;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        super.onCreate();

        HandlerThread handlerThread = new HandlerThread("worker_thread");
        handlerThread.start();
        mWorkHandler = new Handler(handlerThread.getLooper());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() intent = [" + intent + "]");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind() intent = [" + intent + "]");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }

    public void asyncGetTimeMayBlock(GetTimeCallback callback) {
        mWorkHandler.post(() -> {
            Log.d(TAG, "asyncGetTimeMayBlock() simulating background calculation for 2s");
            SystemClock.sleep(2000);
            Log.d(TAG, "asyncGetTimeMayBlock() done");
            long currentTime = System.currentTimeMillis();
            callback.onResult(currentTime);
        });
    }
}
