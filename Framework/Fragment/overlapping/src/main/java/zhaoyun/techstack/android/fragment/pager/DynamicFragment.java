package zhaoyun.techstack.android.fragment.pager;

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
 * @version 2020/5/13
 */
public class DynamicFragment extends Fragment {

    private static final String ARGUMENT_KEY_NAME = "name";

    static DynamicFragment createInstance(String name) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_KEY_NAME, name);
        DynamicFragment fragment = new DynamicFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    private String mName;

    @Override
    public void onAttach(@NonNull Context context) {
        mName = getArguments() != null ? getArguments().getString(ARGUMENT_KEY_NAME, "undefined") : "undefined";

        Log.d("DEBUG", mName + ".onAttach()");
        super.onAttach(context);
        Log.d("DEBUG", mName + ".onAttach() returned");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("DEBUG", mName + ".onCreate()");
        super.onCreate(savedInstanceState);
        Log.d("DEBUG", mName + ".onCreate() returned");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("DEBUG", mName + ".onCreateView()");
        Log.d("DEBUG", mName + ".onCreateView() savedInstanceState = " + savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_lifecycle, container, false);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(mName);
        Log.d("DEBUG", mName + ".onCreateView() returned");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("DEBUG", mName + ".onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        Log.d("DEBUG", mName + ".onActivityCreated() returned");
    }

    @Override
    public void onStart() {
        Log.d("DEBUG", mName + ".onStart()");
        super.onStart();
        Log.d("DEBUG", mName + ".onStart() returned");
    }

    @Override
    public void onResume() {
        Log.d("DEBUG", mName + ".onResume()");
        super.onResume();
        Log.d("DEBUG", mName + ".onResume() returned");
    }

    @Override
    public void onPause() {
        Log.d("DEBUG", mName + ".onPause()");
        super.onPause();
        Log.d("DEBUG", mName + ".onPause() returned");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("DEBUG", mName + ".onSaveInstanceState() outState = [" + outState + "]");
        super.onSaveInstanceState(outState);
        outState.putString("hello", "world");
        Log.d("DEBUG", mName + ".onSaveInstanceState() returned");
    }

    @Override
    public void onStop() {
        Log.d("DEBUG", mName + ".onStop()");
        super.onStop();
        Log.d("DEBUG", mName + ".onStop() returned");
    }

    @Override
    public void onDestroyView() {
        Log.d("DEBUG", mName + ".onDestroyView()");
        super.onDestroyView();
        Log.d("DEBUG", mName + ".onDestroyView() returned");
    }

    @Override
    public void onDestroy() {
        Log.d("DEBUG", mName + ".onDestroy()");
        super.onDestroy();
        Log.d("DEBUG", mName + ".onDestroy() returned");
    }

    @Override
    public void onDetach() {
        Log.d("DEBUG", mName + ".onDetach()");
        super.onDetach();
        Log.d("DEBUG", mName + ".onDetach() returned");
    }
}
