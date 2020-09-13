package zhaoyun.techstack.android.contentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import zhaoyun.techstack.android.contentprovider.provider.database.AnimeDatabaseHelper;
import zhaoyun.techstack.android.contentprovider.provider.database.AnimeTable;

/**
 * @author zhaoyun
 * @version 2018/10/25
 */
public class AnimeContentProvider extends ContentProvider {

    private static final String TAG = AnimeContentProvider.class.getSimpleName();

    private static final int MATCH_CODE_ANIME = 1;
    private static final int MATCH_CODE_ANIME_ITEM = 2;

    private AnimeDatabaseHelper mDatabaseHelper;
    private UriMatcher mUriMatcher;

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate()");

        mDatabaseHelper = new AnimeDatabaseHelper(getContext());

        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(BuildConfig.AUTHORITIES, AnimeTable.TABLE_NAME, MATCH_CODE_ANIME);
        mUriMatcher.addURI(BuildConfig.AUTHORITIES, AnimeTable.TABLE_NAME + "/#", MATCH_CODE_ANIME_ITEM);

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query()");

        Cursor cursor = null;
        switch (mUriMatcher.match(uri)) {
            case MATCH_CODE_ANIME:
                cursor = mDatabaseHelper.getReadableDatabase().query(
                        AnimeTable.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            case MATCH_CODE_ANIME_ITEM:
                long id = ContentUris.parseId(uri);
                cursor = mDatabaseHelper.getReadableDatabase().query(
                        AnimeTable.TABLE_NAME,
                        projection,
                        BaseColumns._ID + "=?",
                        new String[] {String.valueOf(id)},
                        null,
                        null,
                        sortOrder
                );
                break;

            default:
                break;
        }

        if (cursor != null && getContext() != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        Log.d(TAG, "query() returned: " + cursor);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "insert()");

        if (mUriMatcher.match(uri) == MATCH_CODE_ANIME) {
            long id = mDatabaseHelper.getWritableDatabase().insert(
                    AnimeTable.TABLE_NAME,
                    null,
                    values
            );
            Uri itemUri = ContentUris.withAppendedId(uri, id);
            notifyChange(itemUri);
            return ContentUris.appendId(uri.buildUpon(), id).build();
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete()");

        switch (mUriMatcher.match(uri)) {
            case MATCH_CODE_ANIME:
                notifyChange(uri);
                return mDatabaseHelper.getWritableDatabase().delete(
                        AnimeTable.TABLE_NAME,
                        null,
                        null
                );

            case MATCH_CODE_ANIME_ITEM:
                long id = ContentUris.parseId(uri);
                notifyChange(uri);
                return mDatabaseHelper.getWritableDatabase().delete(
                        AnimeTable.TABLE_NAME,
                        AnimeTable.COLUMN_ID + "=?",
                        new String[] {String.valueOf(id)}
                );

            default:
                return 0;
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update()");

        switch (mUriMatcher.match(uri)) {
            case MATCH_CODE_ANIME:
                notifyChange(uri);
                return mDatabaseHelper.getWritableDatabase().update(
                        AnimeTable.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs
                );

            case MATCH_CODE_ANIME_ITEM:
                long id = ContentUris.parseId(uri);
                notifyChange(uri);
                int affectedCount = mDatabaseHelper.getWritableDatabase().update(
                        AnimeTable.TABLE_NAME,
                        values,
                        AnimeTable.COLUMN_ID + "=?",
                        new String[] {String.valueOf(id)}
                );
                Log.d(TAG, "update() affectedCount = " + affectedCount);
                return affectedCount;

            default:
                return 0;
        }
    }

    private void notifyChange(Uri itemUri) {
        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(itemUri, null);
        }
    }
}
