package zhaoyun.techstack.android.viewpager2.transformer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author zhaoyun
 * @version 2020/5/19
 */
public class NumberFragment extends Fragment {

    private static final String ARGUMENT_KEY_NUMBER = "number";

    static NumberFragment createInstance(int number) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_KEY_NUMBER, number);
        NumberFragment numberFragment = new NumberFragment();
        numberFragment.setArguments(arguments);
        return numberFragment;
    }

    private TextView mTextView;
    private int mNumber;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mNumber = arguments.getInt(ARGUMENT_KEY_NUMBER);
        }
        Log.d("DEBUG", "NumberFragment.onAttach() mNumber = " + mNumber);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("DEBUG", "NumberFragment.onCreate() mNumber = " + mNumber);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("DEBUG", "NumberFragment.onCreateView() mNumber = " + mNumber);
        View view = inflater.inflate(R.layout.fragment_number, container, false);
        mTextView = view.findViewById(R.id.textView);
        return view;
    }

    @Override
    public void onStart() {
        Log.d("DEBUG", "NumberFragment.onStart() mNumber = " + mNumber);
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("DEBUG", "NumberFragment.onResume() mNumber = " + mNumber);
        super.onResume();
        Log.d("DEBUG", "NumberFragment.onResume() lazy load number");
        mTextView.setText(String.valueOf(mNumber));
    }

    @Override
    public void onPause() {
        Log.d("DEBUG", "NumberFragment.onPause() mNumber = " + mNumber);
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("DEBUG", "NumberFragment.onStop() mNumber = " + mNumber);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("DEBUG", "NumberFragment.onDestroyView() mNumber = " + mNumber);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("DEBUG", "NumberFragment.onDestroy() mNumber = " + mNumber);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("DEBUG", "NumberFragment.onDetach() mNumber = " + mNumber);
        super.onDetach();
    }
}
