package zhaoyun.teckstack.android.modular.personal;

import androidx.appcompat.app.AppCompatActivity;
import zhaoyun.teckstack.android.modular.router.annotation.Router;
import zhaoyun.teckstack.android.modular.router.api.RouterCenter;

import android.os.Bundle;
import android.view.View;

@Router(path = "/personal/PersonalActivity")
public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity);
    }

    public void startMainActivity(View view) {
        RouterCenter.getInstance().navigate(this, "/app/MainActivity");
    }

    public void startOrderActivity(View view) {
        RouterCenter.getInstance().navigate(this, "/order/OrderActivity");
    }
}
