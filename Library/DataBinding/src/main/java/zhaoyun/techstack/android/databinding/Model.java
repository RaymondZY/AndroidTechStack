package zhaoyun.techstack.android.databinding;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyun
 * @version 2019/7/11
 */
public class Model {

    private List<User> mUserList;
    private List<Integer> mColorList;
    private int mSize;

    public Model() {
        mSize = 3;
        mUserList = new ArrayList<>();
        mUserList.add(new User("zhaoyun"));
        mUserList.add(new User("jianghang"));
        mUserList.add(new User("xuxu"));
        mColorList = new ArrayList<>();
        mColorList.add(Color.RED);
        mColorList.add(Color.BLUE);
        mColorList.add(Color.GRAY);
    }

    public List<User> getUserList() {
        return mUserList;
    }

    public List<Integer> getColorList() {
        return mColorList;
    }

    public int getSize() {
        return mSize;
    }
}
