package zhaoyun.techstack.android.contentprovider.provider.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import zhaoyun.techstack.android.contentprovider.provider.entity.Anime;

/**
 * @author zhaoyun
 * @version 2018/10/25
 */
public class AnimeTable {

    private static final String TAG = "AnimeTable";

    public static final String TABLE_NAME = "anime";
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LIKE_COUNT = "like_count";
    public static final String COLUMN_WATCHED = "watched";

    public void onCreate(@NonNull SQLiteDatabase database) {
        Log.d(TAG, "onCreate()");

        String sql = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL, " +
                        "%s INTEGER NOT NULL" +
                        ");",
                TABLE_NAME,
                COLUMN_ID,
                COLUMN_NAME,
                COLUMN_LIKE_COUNT,
                COLUMN_WATCHED
        );
        database.execSQL(sql);

        Log.d(TAG, "onCreate() returned");
    }

    public void insertAnimeList(SQLiteDatabase database, List<Anime> animeList) {
        database.beginTransaction();
        try {
            for (Anime anime : animeList) {
                database.insert(TABLE_NAME, null, animeToContentValues(anime));
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    private ContentValues animeToContentValues(Anime anime) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, anime.getName());
        contentValues.put(COLUMN_LIKE_COUNT, anime.getLikeCount());
        contentValues.put(COLUMN_WATCHED, anime.isWatched());
        return contentValues;
    }
}
