package zhaoyun.techstack.android.activity.backstack.another;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_flag_activity_new_task).setOnClickListener(
                view -> startActivity(createIntent(Intent.FLAG_ACTIVITY_NEW_TASK))
        );
        findViewById(R.id.button_flag_activity_single_top).setOnClickListener(
                view -> {
                    startActivity(createIntent(Intent.FLAG_ACTIVITY_SINGLE_TOP));
                    new Thread(
                            () -> {
                                SystemClock.sleep(5000);
                                startActivity(createIntent(Intent.FLAG_ACTIVITY_SINGLE_TOP));
                            }
                    ).start();
                }
        );
        findViewById(R.id.button_flag_activity_clear_top).setOnClickListener(
                view -> {
                    startActivity(createIntent(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    new Thread(
                            () -> {
                                SystemClock.sleep(5000);
                                startActivity(createIntent(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            }
                    ).start();
                }
        );
    }

    private Intent createIntent(int flag) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName(
                "zhaoyun.techstack.android.activity.backstack",
                "zhaoyun.techstack.android.activity.backstack.EntranceActivity"
        );
        intent.setFlags(flag);
        return intent;
    }
}
