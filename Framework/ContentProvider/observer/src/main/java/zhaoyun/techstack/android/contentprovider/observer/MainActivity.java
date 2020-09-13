package zhaoyun.techstack.android.contentprovider.observer;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "MainActivity";

    private AnimeAdapter mAnimeAdapter;
    private Handler mWorkHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HandlerThread handlerThread = new HandlerThread("worker");
        handlerThread.start();
        mWorkHandler = new Handler(handlerThread.getLooper());

        mAnimeAdapter = new AnimeAdapter(this, null);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_anime);
        recyclerView.setAdapter(mAnimeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.button_query).setOnClickListener(view -> this.query());
        findViewById(R.id.button_update).setOnClickListener(view -> this.update());

        LoaderManager.getInstance(this).initLoader(0, null, this);
    }

    private void query() {
        mWorkHandler.post(() -> {
            Uri uri = new Uri.Builder()
                    .scheme(ContentResolver.SCHEME_CONTENT)
                    .authority(BuildConfig.AUTHORITIES)
                    .path(AnimeTable.TABLE_NAME)
                    .build();
            Cursor cursor = getContentResolver().query(uri, null, null, null, null, null);
            runOnUiThread(() -> mAnimeAdapter.swapCursor(cursor));
        });
    }

    private void update() {
        mWorkHandler.post(() -> {
        });
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Uri uri = new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(BuildConfig.AUTHORITIES)
                .path(AnimeTable.TABLE_NAME)
                .build();
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished()");

        mAnimeAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset()");

        mAnimeAdapter.swapCursor(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
