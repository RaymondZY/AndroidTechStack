package zhaoyun.techstack.android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author zhaoyun
 * @version 2018/10/29
 */
public class LogFragment extends Fragment {
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(this.toString(), "onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(this.toString(), "onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(this.toString(), "onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(this.toString(), "onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(this.toString(), "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(this.toString(), "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(this.toString(), "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(this.toString(), "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(this.toString(), "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(this.toString(), "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(this.toString(), "onDetach()");
    }
}
