package zhaoyun.techstack.android.contentprovider.observer;

import android.provider.BaseColumns;

/**
 * @author zhaoyun
 * @version 2020/5/12
 */
public class AnimeTable {

    public static final String TABLE_NAME = "anime";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LIKE_COUNT = "like_count";
    public static final String COLUMN_WATCHED = "watched";
}
