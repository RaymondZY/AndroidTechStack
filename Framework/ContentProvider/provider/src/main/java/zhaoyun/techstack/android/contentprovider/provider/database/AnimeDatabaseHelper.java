package zhaoyun.techstack.android.contentprovider.provider.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author zhaoyun
 * @version 2018/10/25
 */
public class AnimeDatabaseHelper extends SQLiteOpenHelper {

    private static final String NAME = "anime.db";
    private static final int VERSION = 1;

    private AnimeTable mAnimeTable;

    public AnimeDatabaseHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);

        mAnimeTable = new AnimeTable();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        mAnimeTable.onCreate(db);
        insertDefaultAnime(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void insertDefaultAnime(SQLiteDatabase database) {
        mAnimeTable.insertAnimeList(database, AnimeFactory.createDefaultAnime());
    }
}
