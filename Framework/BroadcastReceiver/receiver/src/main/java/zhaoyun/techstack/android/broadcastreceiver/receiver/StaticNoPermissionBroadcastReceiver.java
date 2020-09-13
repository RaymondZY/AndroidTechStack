package zhaoyun.techstack.android.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author zhaoyun
 * @version 2018/10/27
 */
public class StaticNoPermissionBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = StaticNoPermissionBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive()");
    }
}
