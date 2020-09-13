package zhaoyun.techstack.android.service.bound.remote;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;
import zhaoyun.techstack.android.service.MainActivity;

/**
 * @author zhaoyun
 * @version 2018/10/24
 */
public class MessengerService extends Service {

    public static final int MESSAGE_TIME = 1;

    private static final String TAG = MessengerService.class.getSimpleName();

    private static class WorkHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage() msg = [" + msg + "]");

            if (msg.what == MESSAGE_TIME) {
                Bundle data = new Bundle();
                data.putLong(MainActivity.MESSAGE_DATA_KEY_TIME, System.currentTimeMillis());
                Message message = Message.obtain(null, MainActivity.MESSAGE_TIME);
                message.setData(data);
                try {
                    msg.replyTo.send(message);
                } catch (RemoteException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            } else {
                super.handleMessage(msg);
            }
        }
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() intent = [" + intent + "]");

        Messenger messenger = new Messenger(new WorkHandler());
        return messenger.getBinder();
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
}
