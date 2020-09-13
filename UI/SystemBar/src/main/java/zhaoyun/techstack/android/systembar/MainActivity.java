package zhaoyun.techstack.android.systembar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpinner;
    private CheckBox mStatusBarCheckBox;
    private CheckBox mNavigationBarCheckBox;
    private SeekBar mAlphaSeekBar;
    private ArrayAdapter<SystemBarActivity.Implementation> mArrayAdapter;
    private CheckBox mFitWindowCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SystemBarActivity.Implementation.values());
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner = findViewById(R.id.spinner);
        mSpinner.setAdapter(mArrayAdapter);

        mStatusBarCheckBox = findViewById(R.id.checkBox_statusBar);
        mNavigationBarCheckBox = findViewById(R.id.checkBox_navigationBar);
        mFitWindowCheckBox = findViewById(R.id.checkBox_fitWindow);
        mAlphaSeekBar = findViewById(R.id.seekBar_alpha);

        Button startButton = findViewById(R.id.button_start);
        startButton.setOnClickListener(view -> start());
    }

    private void start() {
        SystemBarActivity.Implementation implementation = mArrayAdapter.getItem(mSpinner.getSelectedItemPosition());
        boolean setStatusBar = mStatusBarCheckBox.isChecked();
        boolean setNavigationBar = mNavigationBarCheckBox.isChecked();
        boolean fitWindow = mFitWindowCheckBox.isChecked();
        int alpha = mAlphaSeekBar.getProgress();

        if (implementation != null) {
            SystemBarActivity.start(this, implementation, setStatusBar, setNavigationBar, fitWindow, alpha);
        }
    }
}
