package zhaoyun.techstack.android.broadcastreceiver.sender;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class SenderActivity extends AppCompatActivity {

    private BroadcastReceiver mBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);

        initBroadcastReceiver();
        initView();
    }

    private void initBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(BuildConfig.BROADCAST_ACTION);
        mBroadcastReceiver = new LocalBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, intentFilter);
    }

    private void initView() {
        findViewById(R.id.button_send).setOnClickListener(
                view -> {
                    Intent intent = new Intent(BuildConfig.BROADCAST_ACTION);
                    sendBroadcast(intent);
                }
        );
        findViewById(R.id.button_send_with_package).setOnClickListener(
                view -> {
                    Intent intent = new Intent(BuildConfig.BROADCAST_ACTION);
                    intent.setPackage("zhaoyun.techstack.android.broadcastreceiver.receiver");
                    sendBroadcast(intent);
                }
        );
        findViewById(R.id.button_send_with_sender_permission).setOnClickListener(
                view -> {
                    Intent intent = new Intent(BuildConfig.BROADCAST_ACTION);
                    sendBroadcast(intent, BuildConfig.BROADCAST_SENDER_PERMISSION);
                }
        );
        findViewById(R.id.button_send_local).setOnClickListener(
                view -> {
                    Intent intent = new Intent(BuildConfig.BROADCAST_ACTION);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }
}
