package zhaoyun.teckstack.android.modular.order;

import androidx.appcompat.app.AppCompatActivity;
import zhaoyun.teckstack.android.modular.router.annotation.Router;
import zhaoyun.teckstack.android.modular.router.api.RouterCenter;

import android.os.Bundle;
import android.view.View;

@Router(path = "/order/OrderActivity")
public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
    }

    public void startMainActivity(View view) {
        RouterCenter.getInstance().navigate(this, "/app/MainActivity");
    }

    public void startPersonalActivity(View view) {
        RouterCenter.getInstance().navigate(this, "/personal/PersonalActivity");
    }
}
