package zhaoyun.techstack.android.ipc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAddInterface = AddInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private AddInterface mAddInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_bind).setOnClickListener(view -> {
            bindService(new Intent(this, AddService.class), mServiceConnection, BIND_AUTO_CREATE);
        });
        findViewById(R.id.button_add).setOnClickListener(view -> {
            try {
                int sum = mAddInterface.add(1, 2);
                Log.d("DEBUG", "MainActivity.onCreate() sum = " + sum);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}
