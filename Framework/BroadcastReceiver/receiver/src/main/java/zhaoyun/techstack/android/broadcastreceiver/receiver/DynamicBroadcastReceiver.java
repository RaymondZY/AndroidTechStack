package zhaoyun.techstack.android.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/**
 * @author zhaoyun
 * @version 2018/10/27
 */
public class DynamicBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = DynamicBroadcastReceiver.class.getSimpleName();

    private String mName;

    public DynamicBroadcastReceiver(String name) {
        mName = name;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive() Name = " + mName);

        PendingResult pendingResult = goAsync();
        new BackgroundTask(pendingResult).execute();
    }

    private static class BackgroundTask extends AsyncTask<Object, Integer, Object> {

        private PendingResult mPendingResult;

        private BackgroundTask(PendingResult pendingResult) {
            mPendingResult = pendingResult;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            mPendingResult.finish();
        }
    }
}
