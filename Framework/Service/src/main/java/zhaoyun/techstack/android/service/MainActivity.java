package zhaoyun.techstack.android.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.JobIntentService;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import zhaoyun.techstack.android.service.bound.LocalService;
import zhaoyun.techstack.android.service.bound.remote.AidlService;
import zhaoyun.techstack.android.service.bound.remote.MessengerService;
import zhaoyun.techstack.android.service.bound.remote.WrappedObject;
import zhaoyun.techstack.android.service.unbound.BasicService;
import zhaoyun.techstack.android.service.unbound.background.BasicIntentService;
import zhaoyun.techstack.android.service.unbound.background.BasicJobIntentService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private BroadcastReceiver mProgressBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive() context = [" + context + "], intent = [" + intent + "]");

            int progress;
            if (BasicIntentService.BROADCAST_ACTION.equals(intent.getAction())) {
                progress = intent.getIntExtra(BasicIntentService.BROADCAST_EXTRA_KEY_PROGRESS, -1);
            } else {
                progress = intent.getIntExtra(BasicJobIntentService.BROADCAST_EXTRA_KEY_PROGRESS, -1);
            }
            Log.d(TAG, "onReceive() progress = " + progress);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mProgressBroadcastReceiver);
    }

    private void initView() {
        initService();
        initIntentService();
        initJobIntentService();
        initLocalService();
        initMessengerRemoteService();
        initAidlRemoteService();
    }

    /* ---------- Service ---------- */

    private void initService() {
        findViewById(R.id.button_start_service).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, BasicService.class);
                    startService(intent);
                }
        );
        findViewById(R.id.button_stop_service).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, BasicService.class);
                    stopService(intent);
                }
        );
    }

    /* ---------- Intent Service ---------- */

    private void initIntentService() {
        findViewById(R.id.button_start_intent_service).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, BasicIntentService.class);
                    startService(intent);
                }
        );
        findViewById(R.id.button_stop_intent_service).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, BasicIntentService.class);
                    stopService(intent);
                }
        );
        LocalBroadcastManager.getInstance(this).registerReceiver(mProgressBroadcastReceiver, new IntentFilter(BasicIntentService.BROADCAST_ACTION));
    }

    /* ---------- Job Intent Service ---------- */

    private void initJobIntentService() {
        findViewById(R.id.button_start_job_intent_service).setOnClickListener(
                view -> JobIntentService.enqueueWork(this, BasicJobIntentService.class, 0, new Intent())
        );
        findViewById(R.id.button_stop_job_intent_service).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, BasicJobIntentService.class);
                    stopService(intent);
                }
        );
        LocalBroadcastManager.getInstance(this).registerReceiver(mProgressBroadcastReceiver, new IntentFilter(BasicJobIntentService.BROADCAST_ACTION));
    }

    /* ---------- Local Service ---------- */

    private LocalService mLocalService;
    private boolean mLocalServiceConnected;
    private ServiceConnection mLocalServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected() name = [" + name + "], service = [" + service + "]");
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mLocalService = binder.getService();
            mLocalServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected() name = [" + name + "]");
            mLocalService = null;
            mLocalServiceConnected = false;
        }
    };

    private void initLocalService() {
        findViewById(R.id.button_bind_local_service).setOnClickListener(view -> bindLocalService());
        findViewById(R.id.button_unbind_local_service).setOnClickListener(view -> unbindLocalService());
        findViewById(R.id.button_local_service_operation).setOnClickListener(view -> callLocalServiceOperation());
    }

    private void bindLocalService() {
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, mLocalServiceConnection, BIND_AUTO_CREATE);
    }

    private void unbindLocalService() {
        if (mLocalServiceConnected) {
            unbindService(mLocalServiceConnection);
            mLocalService = null;
            mLocalServiceConnected = false;
        }
    }

    private void callLocalServiceOperation() {
        if (mLocalServiceConnected) {
            mLocalService.asyncGetTimeMayBlock(time -> Log.d(TAG, "Current Time = " + time));
        } else {
            Toast.makeText(this, "Service is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    /* ---------- Messenger Remote Service ---------- */

    public static final int MESSAGE_TIME = 1;
    public static final String MESSAGE_DATA_KEY_TIME = "time";

    private Messenger mMessenger;
    private boolean mMessengerRemoteServiceConnected;
    private ServiceConnection mMessengerRemoteServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected() name = [" + name + "], service = [" + service + "]");
            mMessenger = new Messenger(service);
            mMessengerRemoteServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected() name = [" + name + "]");
            mMessenger = null;
            mMessengerRemoteServiceConnected = false;
        }
    };

    private static class RemoteReplyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_TIME) {
                long currentTime = msg.getData().getLong(MESSAGE_DATA_KEY_TIME);
                Log.d(TAG, "handleMessage() currentTime = " + currentTime);
            }
            super.handleMessage(msg);
        }
    }

    private void initMessengerRemoteService() {
        findViewById(R.id.button_bind_messenger_remote_service).setOnClickListener(view -> bindMessengerRemoteService());
        findViewById(R.id.button_unbind_messenger_remote_service).setOnClickListener(view -> unbindMessengerRemoteService());
        findViewById(R.id.button_messenger_remote_service_operation).setOnClickListener(view -> callMessengerRemoteServiceOperation());
    }

    private void bindMessengerRemoteService() {
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, mMessengerRemoteServiceConnection, BIND_AUTO_CREATE);
    }

    private void unbindMessengerRemoteService() {
        if (mMessengerRemoteServiceConnected) {
            unbindService(mMessengerRemoteServiceConnection);
            mMessenger = null;
            mMessengerRemoteServiceConnected = false;
        }
    }

    private void callMessengerRemoteServiceOperation() {
        if (mMessengerRemoteServiceConnected) {
            Message message = Message.obtain(null, MessengerService.MESSAGE_TIME);
            message.replyTo = new Messenger(new RemoteReplyHandler());
            try {
                mMessenger.send(message);
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        } else {
            Toast.makeText(this, "Service is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    /* ---------- Aidl Remote Service ---------- */

    private WrappedObject mWrappedObject;
    private AidlServiceInterface mAidlRemoteServiceInterface;
    private boolean mAidlRemoteServiceConnected;
    private ServiceConnection mAidlRemoteServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected() name = [" + name + "], service = [" + service + "]");
            mAidlRemoteServiceInterface = AidlServiceInterface.Stub.asInterface(service);
            mAidlRemoteServiceConnected = true;
            try {
                service.linkToDeath(mDeathRecipient, 0);
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected() name = [" + name + "]");
            mAidlRemoteServiceInterface = null;
            mAidlRemoteServiceConnected = false;
        }
    };
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d(TAG, "binderDied()");
            mAidlRemoteServiceInterface = null;
            mAidlRemoteServiceConnected = false;
        }
    };
    private AidlServiceCallback mAidlRemoteServiceCallback = new AidlServiceCallback.Stub() {
        @Override
        public void onProgressChanged(int percent, WrappedObject changedObject) {
            Log.d(TAG, "onProgressChanged() percent = [" + percent + "]");
            Log.d(TAG, "onProgressChanged() changedObject = " + changedObject);
            Log.d(TAG, "onProgressChanged() mWrappedObject = " + mWrappedObject);
            SystemClock.sleep(500);
        }
    };

    private void initAidlRemoteService() {
        findViewById(R.id.button_bind_aidl_remote_service).setOnClickListener(view -> bindAidlRemoteService());
        findViewById(R.id.button_unbind_aidl_remote_service).setOnClickListener(view -> unbindAidlRemoteService());
        findViewById(R.id.button_aidl_remote_service_async_operation).setOnClickListener(view -> callAidlRemoteServiceAsyncOperation());
        findViewById(R.id.button_aidl_remote_service_sync_operation).setOnClickListener(view -> callAidlRemoteServiceSyncOperation());
    }

    private void bindAidlRemoteService() {
        Intent intent = new Intent(this, AidlService.class);
        bindService(intent, mAidlRemoteServiceConnection, BIND_AUTO_CREATE);
    }

    private void unbindAidlRemoteService() {
        if (mAidlRemoteServiceConnected) {
            mAidlRemoteServiceInterface.asBinder().unlinkToDeath(mDeathRecipient, 0);
            unbindService(mAidlRemoteServiceConnection);
            mAidlRemoteServiceInterface = null;
            mAidlRemoteServiceConnected = false;
        }
    }

    private void callAidlRemoteServiceAsyncOperation() {
        if (mAidlRemoteServiceConnected) {
            try {
                initWrappedObject();
                mAidlRemoteServiceInterface.asyncHeavyWork(mWrappedObject, mAidlRemoteServiceCallback);
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        } else {
            Toast.makeText(this, "Service is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    private void callAidlRemoteServiceSyncOperation() {
        if (mAidlRemoteServiceConnected) {
            try {
                initWrappedObject();
                mAidlRemoteServiceInterface.syncLightWork(mWrappedObject);
                Log.d(TAG, "initAidlRemoteService() mWrappedObject = " + mWrappedObject);
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        } else {
            Toast.makeText(this, "Service is not connected", Toast.LENGTH_SHORT).show();
        }
    }

    private void initWrappedObject() {
        mWrappedObject = new WrappedObject(
                0,
                "string",
                new int[] {1, 2, 3},
                new ArrayList<Integer>() {{
                    add(1);
                    add(2);
                    add(3);
                }},
                new ArrayList<String>() {{
                    add("a");
                    add("b");
                    add("c");
                }},
                new HashMap<String, Integer>() {{
                    put("a", 1);
                    put("b", 2);
                    put("c", 3);
                }}
        );
    }

    /* -------------------- */
}
