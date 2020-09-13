package zhaoyun.techstack.android.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiverActivity extends AppCompatActivity {

    private BroadcastReceiver mNoPermissionBroadcastReceiver;
    private BroadcastReceiver mReceiverPermissionBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        mNoPermissionBroadcastReceiver = new DynamicBroadcastReceiver("NO PERMISSION");
        mReceiverPermissionBroadcastReceiver = new DynamicBroadcastReceiver("RECEIVER PERMISSION");

        IntentFilter intentFilter = new IntentFilter(BuildConfig.BROADCAST_ACTION);
        registerReceiver(
                mNoPermissionBroadcastReceiver,
                intentFilter
        );
        registerReceiver(
                mReceiverPermissionBroadcastReceiver,
                intentFilter,
                BuildConfig.BROADCAST_RECEIVER_PERMISSION,
                null
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mNoPermissionBroadcastReceiver);
        unregisterReceiver(mReceiverPermissionBroadcastReceiver);
    }
}
