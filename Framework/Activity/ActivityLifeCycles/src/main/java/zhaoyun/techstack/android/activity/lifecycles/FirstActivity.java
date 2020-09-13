package zhaoyun.techstack.android.activity.lifecycles;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class FirstActivity extends BaseActivity {

    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);

        initView();
    }

    private void initView() {
        findViewById(R.id.button_second_activity).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, SecondActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }
        );
        findViewById(R.id.button_dialog).setOnClickListener(
                view -> {
                    SimpleDialogFragment dialogFragment = new SimpleDialogFragment();
                    dialogFragment.show(getSupportFragmentManager(), "SimpleDialogFragment");
                }
        );
        findViewById(R.id.button_transparent_activity).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, TransparentActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }
        );
        findViewById(R.id.button_dialog_activity).setOnClickListener(
                view -> {
                    Intent intent = new Intent(this, DialogActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }
        );
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public static class SimpleDialogFragment extends DialogFragment {

        private static final String TAG = SimpleDialogFragment.class.getSimpleName();

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Log.i(TAG, "onCreateDialog()");

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("message : nothing fancy");
            builder.setPositiveButton(
                    "okay",
                    (dialog, which) -> dialog.dismiss()
            );
            builder.setNegativeButton(
                    "cancel",
                    (dialog, which) -> dialog.cancel()
            );

            return builder.create();
        }
    }
}
