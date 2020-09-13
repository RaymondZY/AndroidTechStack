package zhaoyun.techstack.android.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.util.LruCache;

/**
 * @author zhaoyun
 * @version 2020/6/23
 */
public class ImageLoader {

    private LruCache<String, Bitmap> mLruCache;

    public ImageLoader() {
        mLruCache = new LruCache<String, Bitmap>(4 * 1024 * 1024) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    return bitmap.getAllocationByteCount();
                } else {
                    return bitmap.getByteCount();
                }
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                Log.d("DEBUG", "ImageLoader.entryRemoved() " + key);
            }
        };
    }

    public Bitmap loadImage(Context context, String fileName) {
        Log.d("DEBUG", "ImageLoader.loadImage() fileName = [" + fileName + "]");

        Bitmap bitmap = mLruCache.get(fileName);
        if (bitmap == null) {
            Log.d("DEBUG", "ImageLoader.loadImage() miss LRUCache");
            Log.d("DEBUG", "ImageLoader.loadImage() load from resources");

            int id = context.getResources().getIdentifier(fileName, "mipmap", context.getPackageName());
            bitmap = BitmapFactory.decodeResource(context.getResources(), id);
            int height = bitmap.getHeight();
            int width = bitmap.getWidth();
            int size = bitmap.getByteCount();

            Log.d("DEBUG", "ImageLoader.loadImage() height = " + height);
            Log.d("DEBUG", "ImageLoader.loadImage() width = " + width);
            Log.d("DEBUG", "ImageLoader.loadImage() size = " + size);

            mLruCache.put(fileName, bitmap);
        } else {
            Log.d("DEBUG", "ImageLoader.loadImage() hit LRUCache");
        }
        return bitmap;
    }
}
