package com.example.bangchangbae.friendlyapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by bangchangbae on 16. 8. 29..
 */
public class Uitil {
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        reqWidth = reqWidth > 0 ? reqWidth : 1;
        reqHeight = reqHeight > 0 ? reqHeight : 1;
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            inSampleSize = 2;

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    || (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        Log.d("check sampleSize", "inSampleSize : " + inSampleSize);
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        int requestWidth = reqWidth > 0 ? reqWidth : 0;
        int requestHeight = reqHeight > 0 ? reqHeight : 0;
        int originWidth = options.outWidth;
        int originHeight = options.outHeight;
        float rate = 0.0f;

        if (requestWidth == 0 && requestHeight == 0) {
            Log.e("decode", "request too small size");
        } else if (requestHeight == 0) {
            rate = (float) requestWidth / (float) originWidth;
            requestHeight = (int) (originHeight * rate);
        } else if (requestWidth == 0) {
            rate = (float) requestHeight / (float) originHeight;
            requestWidth = (int) (originWidth * rate);
        } else {
            rate = (float) requestWidth / (float) originWidth;
            if (reqHeight < requestHeight * rate) {
                requestHeight = (int) (originHeight * rate);
            } else {
                rate = (float) requestHeight / (float) originHeight;
                requestWidth = (int) (originWidth * rate);
            }
        }

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, requestWidth, requestHeight);
        Log.d("decode", "request width : " + reqWidth + " height : " + reqHeight);
        Log.d("decode", "cal request width : " + requestWidth + " height : " + requestHeight);
        Log.d("decode", "origin width : " + options.outWidth + " height : " + options.outHeight);
        Log.d("decode", "inSampleSize : " + options.inSampleSize);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
