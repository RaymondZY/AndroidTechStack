package zhaoyun.techstack.android.contentprovider.observer;

import androidx.annotation.NonNull;

/**
 * @author zhaoyun
 * @version 2018/10/25
 */
public class Anime {

    private long mId;
    private String mName;
    private int mLikeCount;
    private boolean mWatched;

    public Anime(long id, String name, int likeCount, boolean watched) {
        mId = id;
        mName = name;
        mLikeCount = likeCount;
        mWatched = watched;
    }

    public String getName() {
        return mName;
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
        return "Anime {" + mName + ", " + mLikeCount + ", " + mWatched + "}";
    }
}
