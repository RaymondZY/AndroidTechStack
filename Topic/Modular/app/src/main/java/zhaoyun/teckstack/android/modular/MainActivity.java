package zhaoyun.teckstack.android.modular;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import zhaoyun.teckstack.android.modular.router.annotation.Router;
import zhaoyun.teckstack.android.modular.router.api.RouterCenter;

@Router(path = "/app/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startOrderActivity(View view) {
        RouterCenter.getInstance().navigate(this, "/order/OrderActivity");
    }

    public void startPersonalActivity(View view) {
        RouterCenter.getInstance().navigate(this, "/personal/PersonalActivity");
    }
}
