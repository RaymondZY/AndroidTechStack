package zhaoyun.techstack.android.service.unbound.background;

import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * @author zhaoyun
 * @version 2020/5/7
 */
public class BasicJobIntentService extends JobIntentService {

    private static final String TAG = "BasicJobIntentService";

    public static final String BROADCAST_ACTION = "zhaoyun.techstack.android.service.unbound.background.BasicJobIntentService.BROADCAST";
    public static final String BROADCAST_EXTRA_KEY_PROGRESS = "zhaoyun.techstack.android.service.unbound.background.BasicJobIntentService.EXTRA_PROGRESS";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        super.onCreate();
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "onHandleWork() intent = [" + intent + "]");

        Log.d(TAG, "Simulating long-running operation for 5 seconds..");
        for (int progress = 0; progress < 100; progress += 20) {
            broadcastProgress(progress);
            SystemClock.sleep(1000);
        }
        broadcastProgress(100);
        Log.d(TAG, "Job done");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }

    private void broadcastProgress(int progress) {
        Intent intent = new Intent();
        intent.setAction(BROADCAST_ACTION);
        intent.putExtra(BROADCAST_EXTRA_KEY_PROGRESS, progress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
