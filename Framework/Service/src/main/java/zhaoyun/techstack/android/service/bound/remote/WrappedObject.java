package zhaoyun.techstack.android.service.bound.remote;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * @author zhaoyun
 * @version 2020/5/8
 */
public class WrappedObject implements Parcelable {

    private static final String TAG = "WrappedObject";

    private int mInt;
    private String mString;
    private int[] mIntArray;
    private List<Integer> mIntegerList;
    private List<String> mStringList;
    private Map<String, Integer> mStringIntegerMap;

    public WrappedObject(int anInt, String string, int[] intArray, List<Integer> integerList, List<String> stringList, Map<String, Integer> stringIntegerMap) {
        mInt = anInt;
        mString = string;
        mIntArray = intArray;
        mIntegerList = integerList;
        mStringList = stringList;
        mStringIntegerMap = stringIntegerMap;
    }

    private WrappedObject(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel parcel) {
        mInt = parcel.readInt();
        mString = parcel.readString();
        mIntArray = parcel.createIntArray();
        mIntegerList = new ArrayList<>();
        parcel.readList(mIntegerList, getClass().getClassLoader());
        mStringList = parcel.createStringArrayList();
        mStringIntegerMap = new HashMap<>();
        parcel.readMap(mStringIntegerMap, getClass().getClassLoader());
    }

    void changeSomething(int time) {
        Log.d(TAG, "changeSomething() time = [" + time + "]");
        mInt = -1 * time;
        mString = "changed string " + time;
        mIntArray = new int[] {-1 * time, -1 * time, -1 * time};
        mIntegerList = new ArrayList<Integer>() {{
            add(-1 * time);
            add(-1 * time);
            add(-1 * time);
        }};
        mStringList = new ArrayList<String>() {{
            add("changed string " + time);
            add("changed string " + time);
            add("changed string " + time);
        }};
        mStringIntegerMap = new HashMap<String, Integer>() {{
            put("x", -1 * time);
            put("y", -1 * time);
            put("z", -1 * time);
        }};
    }

    public static final Creator<WrappedObject> CREATOR = new Creator<WrappedObject>() {
        @Override
        public WrappedObject createFromParcel(Parcel in) {
            return new WrappedObject(in);
        }

        @Override
        public WrappedObject[] newArray(int size) {
            return new WrappedObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mInt);
        dest.writeString(mString);
        dest.writeIntArray(mIntArray);
        dest.writeList(mIntegerList);
        dest.writeStringList(mStringList);
        dest.writeMap(mStringIntegerMap);
    }

    @NonNull
    @Override
    public String toString() {
        return "WrappedObject{" +
                "mInt=" + mInt +
                ", mString='" + mString + '\'' +
                ", mIntArray=" + Arrays.toString(mIntArray) +
                ", mIntegerList=" + mIntegerList +
                ", mStringList=" + mStringList +
                ", mStringIntegerMap=" + mStringIntegerMap +
                '}';
    }
}
