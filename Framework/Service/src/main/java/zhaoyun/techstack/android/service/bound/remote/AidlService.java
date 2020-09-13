package zhaoyun.techstack.android.service.bound.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import zhaoyun.techstack.android.service.AidlServiceInterface;
import zhaoyun.techstack.android.service.AidlServiceCallback;

/**
 * @author zhaoyun
 * @version 2018/10/25
 */
public class AidlService extends Service {

    private static final String TAG = "AidlService";

    private AidlServiceInterface.Stub mBinder = new AidlServiceInterface.Stub() {

        @Override
        public void asyncHeavyWork(WrappedObject wrappedObject, AidlServiceCallback callback) throws RemoteException {
            Log.d(TAG, "doSomeHeavyWork() wrappedObject = [" + wrappedObject + "], callback = [" + callback + "]");
            mRemoteCallbackList.register(callback);
            int percent, time;
            for (percent = 0, time = 1; percent < 100; percent += 20, time++) {
                broadcast(percent, wrappedObject);
                SystemClock.sleep(2000);
                wrappedObject.changeSomething(time);
            }
            broadcast(percent, wrappedObject);
            mRemoteCallbackList.unregister(callback);
        }

        @Override
        public void syncLightWork(WrappedObject wrappedObject) {
            Log.d(TAG, "syncLightWork() wrappedObject = [" + wrappedObject + "]");
            wrappedObject.changeSomething(1);
        }

        private void broadcast(int percent, WrappedObject wrappedObject) throws RemoteException {
            int size = mRemoteCallbackList.beginBroadcast();
            for (int i = 0; i < size; i++) {
                AidlServiceCallback broadcastItem = mRemoteCallbackList.getBroadcastItem(i);
                broadcastItem.onProgressChanged(percent, wrappedObject);
            }
            mRemoteCallbackList.finishBroadcast();
        }
    };

    private RemoteCallbackList<AidlServiceCallback> mRemoteCallbackList;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        super.onCreate();

        mRemoteCallbackList = new RemoteCallbackList<>();
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
}
