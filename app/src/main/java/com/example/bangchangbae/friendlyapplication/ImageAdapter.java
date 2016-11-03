package com.example.bangchangbae.friendlyapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.bangchangbae.friendlyapplication.common.GlobalData;
import com.example.bangchangbae.friendlyapplication.common.Util;

import java.util.List;

/**
 * Created by bangchangbae on 16. 8. 26..
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private LruCache<String, Bitmap> mMemoryCache;
    private List<Integer> mImageIds;

    public ImageAdapter(GlobalData.GRID_TYPE type, Context context) {
        mContext = context;

        // Get memory class of this device, exceeding this amount will throw an
        // OutOfMemory exception.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory());

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;
        Log.d("grid perf", "cacheSize : " + cacheSize);

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in bytes rather than number
                // of items.
                Log.d("grid perf", "sizeOf : " + bitmap.getByteCount());
                return bitmap.getByteCount();
            }

        };
        GlobalData myGlobalData= (GlobalData)context.getApplicationContext();
        mImageIds = myGlobalData.getMyThumbnailImageList(type);
    }

    public void updateResults(List<Integer> results) {
        mImageIds = results;
        notifyDataSetChanged();
    }

    public int getPictureId(int index){
        return mImageIds.get(index);
    }

    @Override
    public int getCount() {
        return mImageIds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, (
                    int)(100*mContext.getResources().getDisplayMetrics().density)));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else{
            imageView = (ImageView) convertView;
        }
        Log.d("view index", "grid index : " + position);

        final String imageKey = String.valueOf(mImageIds.get(position));
        Log.d("grid perf", "imageKey : " + imageKey);
        Bitmap thumbnailBitmap = getBitmapFromMemCache(imageKey);
        if(thumbnailBitmap == null){
            int imageViewHeight = imageView.getLayoutParams().height;
            int imageViewWidth = imageView.getLayoutParams().width;
            thumbnailBitmap = resizeResource(mImageIds.get(position), imageViewWidth, imageViewHeight);
            addBitmapToMemoryCache(imageKey, thumbnailBitmap);
            Log.d("grid perf", "imageKey : " + imageKey + " use new image");
        }else{
            Log.d("grid perf", "imageKey : " + imageKey + " use cached image");
        }
        imageView.setImageBitmap(thumbnailBitmap);
        return imageView;
    }
    private Bitmap resizeResource(int imageResourceId, int width, int height){
        int requestWidth = width;
        int requestHeight = height;
        return Util.decodeSampledBitmapFromResource(mContext.getResources(), imageResourceId, requestWidth, requestHeight);
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}
