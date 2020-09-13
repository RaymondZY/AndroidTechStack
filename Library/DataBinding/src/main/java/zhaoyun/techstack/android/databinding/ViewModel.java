package zhaoyun.techstack.android.databinding;

import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * @author zhaoyun
 * @version 2019/7/11
 */
public class ViewModel extends BaseObservable {

    private static final String TAG = ViewModel.class.getSimpleName();

    private Model mModel;
    private int mCurrentIndex;

    public ViewModel() {
        mModel = new Model();
        mCurrentIndex = 0;
    }

    @Bindable
    public User getCurrentUser() {
        return mModel.getUserList().get(mCurrentIndex);
    }

    @Bindable
    public int getCurrentColor() {
        return mModel.getColorList().get(mCurrentIndex);
    }

    public void onButtonClicked(View view) {
        Log.i(TAG, "onButtonClicked() " + view);
        mCurrentIndex = (mCurrentIndex + 1) % mModel.getSize();
    }
}
