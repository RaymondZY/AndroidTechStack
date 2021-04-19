package zhaoyun.techstack.android.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AddService extends Service {

    private AddInterface.Stub mAddInterface = new AddInterface.Stub() {
        @Override
        public int add(int a, int b) throws RemoteException {
            Log.d("DEBUG", "AddService.add()");
            return a + b;
        }
    };

    public AddService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("DEBUG", "AddService.onBind()");
        return mAddInterface.asBinder();
    }
}
