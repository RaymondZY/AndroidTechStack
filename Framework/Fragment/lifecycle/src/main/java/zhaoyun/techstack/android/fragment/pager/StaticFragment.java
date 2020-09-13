package zhaoyun.techstack.android.fragment.pager;

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
 * @version 2020/5/13
 */
public class StaticFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("DEBUG", "StaticFragment.onCreate()");
        super.onCreate(savedInstanceState);
        Log.d("DEBUG", "StaticFragment.onCreate() returned");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        Log.d("DEBUG", "StaticFragment.onAttach()");
        super.onAttach(context);
        Log.d("DEBUG", "StaticFragment.onAttach() returned");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("DEBUG", "StaticFragment.onCreateView()");
        Log.d("DEBUG", "StaticFragment.onCreateView() savedInstanceState = " + savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_lifecycle, container, false);
        Log.d("DEBUG", "StaticFragment.onCreateView() returned");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("DEBUG", "StaticFragment.onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
        Log.d("DEBUG", "StaticFragment.onActivityCreated() returned");
    }

    @Override
    public void onStart() {
        Log.d("DEBUG", "StaticFragment.onStart()");
        super.onStart();
        Log.d("DEBUG", "StaticFragment.onStart() returned");
    }

    @Override
    public void onResume() {
        Log.d("DEBUG", "StaticFragment.onResume()");
        super.onResume();
        Log.d("DEBUG", "StaticFragment.onResume() returned");
    }

    @Override
    public void onPause() {
        Log.d("DEBUG", "StaticFragment.onPause()");
        super.onPause();
        Log.d("DEBUG", "StaticFragment.onPause() returned");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("DEBUG", "StaticFragment.onSaveInstanceState() outState = [" + outState + "]");
        super.onSaveInstanceState(outState);
        outState.putString("hello", "world");
        Log.d("DEBUG", "StaticFragment.onSaveInstanceState() returned");
    }

    @Override
    public void onStop() {
        Log.d("DEBUG", "StaticFragment.onStop()");
        super.onStop();
        Log.d("DEBUG", "StaticFragment.onStop() returned");
    }

    @Override
    public void onDestroyView() {
        Log.d("DEBUG", "StaticFragment.onDestroyView()");
        super.onDestroyView();
        Log.d("DEBUG", "StaticFragment.onDestroyView() returned");
    }

    @Override
    public void onDestroy() {
        Log.d("DEBUG", "StaticFragment.onDestroy()");
        super.onDestroy();
        Log.d("DEBUG", "StaticFragment.onDestroy() returned");
    }

    @Override
    public void onDetach() {
        Log.d("DEBUG", "StaticFragment.onDetach()");
        super.onDetach();
        Log.d("DEBUG", "StaticFragment.onDetach() returned");
    }
}
