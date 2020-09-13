package zhaoyun.techstack.android.broadcastreceiver.sender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author zhaoyun
 * @version 2018/10/29
 */
public class LocalBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = LocalBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive()");
    }
}
