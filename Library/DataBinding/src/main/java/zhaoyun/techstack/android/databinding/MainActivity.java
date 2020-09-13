package zhaoyun.techstack.android.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import zhaoyun.techstack.android.databinding.databinding.ActivityMainBinding;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ViewModel viewModel = new ViewModel();
        binding.setViewModel(viewModel);
    }
}
