package zhaoyun.techstack.android.contentprovider.observer;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author zhaoyun
 * @version 2020/5/12
 */
public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private Cursor mCursor;

    public AnimeAdapter(Context context, Cursor cursor) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        swapCursor(cursor);
    }

    public void swapCursor(Cursor cursor) {
        if (cursor != null && mCursor != cursor) {
            mCursor = cursor;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_view_anime, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (mCursor.moveToPosition(position)) {
            int id = mCursor.getInt(mCursor.getColumnIndex(AnimeTable.COLUMN_ID));
            String name = mCursor.getString(mCursor.getColumnIndex(AnimeTable.COLUMN_NAME));
            int likeCount = mCursor.getInt(mCursor.getColumnIndex(AnimeTable.COLUMN_LIKE_COUNT));
            boolean watched = mCursor.getInt(mCursor.getColumnIndex(AnimeTable.COLUMN_WATCHED)) == 1;

            holder.mTextView.setText(String.format(Locale.getDefault(), "%d %s %d %s", id, name, likeCount, watched));
            holder.mLikeButton.setOnClickListener(view -> this.like(id, likeCount));
            holder.mWatchButton.setOnClickListener(view -> this.toggleWatched(id, watched));
        }
    }

    private void like(int id, int likeCount) {
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(BuildConfig.AUTHORITIES)
                .path(AnimeTable.TABLE_NAME)
                .build();
        uri = ContentUris.withAppendedId(uri, id);
        ContentValues contentValues = new ContentValues();
        contentValues.put(AnimeTable.COLUMN_LIKE_COUNT, likeCount + 1);
        mContext.getContentResolver().update(uri, contentValues, null, null);
    }

    private void toggleWatched(int id, boolean watched) {
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(BuildConfig.AUTHORITIES)
                .path(AnimeTable.TABLE_NAME)
                .build();
        uri = ContentUris.withAppendedId(uri, id);
        ContentValues contentValues = new ContentValues();
        contentValues.put(AnimeTable.COLUMN_WATCHED, !watched);
        mContext.getContentResolver().update(uri, contentValues, null, null);
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private Button mLikeButton;
        private Button mWatchButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.textView);
            mLikeButton = itemView.findViewById(R.id.button_like);
            mWatchButton = itemView.findViewById(R.id.button_watch);
        }
    }
}
