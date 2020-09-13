package zhaoyun.techstack.android.fragment.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * @author zhaoyun
 * @version 2018/10/29
 */
public class Anime implements Parcelable {

    private String mName;
    private int mLikeCount;
    private boolean mWatched;

    public Anime(String name, int likeCount, boolean watched) {
        mName = name;
        mLikeCount = likeCount;
        mWatched = watched;
    }

    public int getLikeCount() {
        return mLikeCount;
    }

    public boolean isWatched() {
        return mWatched;
    }

    @NonNull
    @Override
    public String toString() {
        return "Anime { " + mName + ", " + mLikeCount + ", " + mWatched + " }";
    }

    protected Anime(Parcel in) {
        mName = in.readString();
        mLikeCount = in.readInt();
        mWatched = in.readByte() != 0;
    }

    public static final Creator<Anime> CREATOR = new Creator<Anime>() {
        @Override
        public Anime createFromParcel(Parcel in) {
            return new Anime(in);
        }

        @Override
        public Anime[] newArray(int size) {
            return new Anime[size];
        }
    };

    public String getName() {
        return mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mLikeCount);
        dest.writeByte((byte) (mWatched ? 1 : 0));
    }
}
