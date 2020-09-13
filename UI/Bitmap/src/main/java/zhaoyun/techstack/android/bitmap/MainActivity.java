package zhaoyun.techstack.android.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageLoader mImageLoader;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageLoader = new ImageLoader();
        mImageView = findViewById(R.id.imageView);
        mImageView.setOnClickListener(view -> loadRandomImage());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        Log.d("DEBUG", "MainActivity.onCreate() displayMetrics.density = " + displayMetrics.density);
        Log.d("DEBUG", "MainActivity.onCreate() displayMetrics.densityDpi = " + displayMetrics.densityDpi);

        loadLargeBitmap();
        loadRandomImage();
    }

    private void loadLargeBitmap() {
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.screen);

        Log.d("DEBUG", "MainActivity.loadLargeBitmap() originalBitmap.getWidth() = " + originalBitmap.getWidth());
        Log.d("DEBUG", "MainActivity.loadLargeBitmap() originalBitmap.getHeight() = " + originalBitmap.getHeight());
        Log.d("DEBUG", "MainActivity.loadLargeBitmap() originalBitmap.getConfig() = " + originalBitmap.getConfig());
        Log.d("DEBUG", "MainActivity.loadLargeBitmap() originalBitmap.getByteCount() = " + originalBitmap.getByteCount());

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap optimizedBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.screen, options);

        Log.d("DEBUG", "MainActivity.loadLargeBitmap() optimizedBitmap.getWidth() = " + optimizedBitmap.getWidth());
        Log.d("DEBUG", "MainActivity.loadLargeBitmap() optimizedBitmap.getHeight() = " + optimizedBitmap.getHeight());
        Log.d("DEBUG", "MainActivity.loadLargeBitmap() optimizedBitmap.getConfig() = " + optimizedBitmap.getConfig());
        Log.d("DEBUG", "MainActivity.loadLargeBitmap() optimizedBitmap.getByteCount() = " + optimizedBitmap.getByteCount());
    }

    private void loadRandomImage() {
        Random random = new Random();
        int imageIndex = random.nextInt(8) + 1;
        String fileName = "image" + imageIndex;
        Bitmap bitmap = mImageLoader.loadImage(this, fileName);
        mImageView.setImageBitmap(bitmap);
    }
}
