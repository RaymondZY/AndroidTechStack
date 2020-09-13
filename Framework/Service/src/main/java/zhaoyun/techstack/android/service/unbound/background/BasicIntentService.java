package zhaoyun.techstack.android.service.unbound.background;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.SystemClock;
import android.util.Log;

/**
 * @author zhaoyun
 * @version 2018/10/24
 */
public class BasicIntentService extends IntentService {

    private static final String TAG = BasicIntentService.class.getSimpleName();

    public static final String BROADCAST_ACTION = "zhaoyun.techstack.android.service.unbound.background.BasicIntentService.BROADCAST";
    public static final String BROADCAST_EXTRA_KEY_PROGRESS = "zhaoyun.techstack.android.service.unbound.background.BasicIntentService.EXTRA_PROGRESS";

    public BasicIntentService() {
        super(BasicIntentService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent() intent = [" + intent + "]");

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
        Log.i(TAG, "onDestroy()");
        super.onDestroy();
    }

    private void broadcastProgress(int progress) {
        Intent intent = new Intent();
        intent.setAction(BROADCAST_ACTION);
        intent.putExtra(BROADCAST_EXTRA_KEY_PROGRESS, progress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
