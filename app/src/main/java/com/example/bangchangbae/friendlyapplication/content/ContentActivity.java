package com.example.bangchangbae.friendlyapplication.content;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bangchangbae.friendlyapplication.R;
import com.example.bangchangbae.friendlyapplication.common.GlobalData;
import com.example.bangchangbae.friendlyapplication.common.Util;
import com.example.bangchangbae.friendlyapplication.data.MyDetail;
import com.example.bangchangbae.friendlyapplication.data.MyProfile;

/**
 * Created by bangchangbae on 16. 9. 1..
 */
public class ContentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        Intent intent = getIntent();
        int picture_id = intent.getIntExtra(Util.PICTURE_ID, -1);

        if(picture_id == -1)
            return;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;

        MyDetail detailData = ((GlobalData)getApplicationContext()).getDetail(picture_id);

        ImageView datailMainImageView = (ImageView) findViewById(R.id.detailMain);

        int datailMainImageViewWidth = datailMainImageView.getLayoutParams().width > 0 ? datailMainImageView.getLayoutParams().width : width;
        int datailMainImageViewHeight = datailMainImageView.getLayoutParams().height;
        Bitmap datailMainImageBitmap = Util.decodeSampledBitmapFromResource(getResources(), detailData.feed_img_src, datailMainImageViewWidth, datailMainImageViewHeight);
        datailMainImageView.setImageBitmap(datailMainImageBitmap);


        int [] thumbnailViewIds = {R.id.thumbnail01, R.id.thumbnail02, R.id.thumbnail03, R.id.thumbnail04};
        int [] thumbnailSrcId = detailData.thumbnail_image_src_array;
        for(int i=0; i<thumbnailSrcId.length; i++){
            ImageView thumbnailView = (ImageView) findViewById(thumbnailViewIds[i]);
            int thumbnailViewWidth = thumbnailView.getLayoutParams().width > 0 ? thumbnailView.getLayoutParams().width : width/4;
            int thumbnailViewHeight = thumbnailView.getLayoutParams().height;
            Bitmap thumbnailViewBitmap = Util.decodeSampledBitmapFromResource(getResources(), thumbnailSrcId[i], thumbnailViewWidth, thumbnailViewHeight);
            thumbnailView.setImageBitmap(thumbnailViewBitmap);
        }

        TextView scheduleTextView = (TextView) findViewById(R.id.schecule_text);
        scheduleTextView.setText(detailData.getScheduleText());
        TextView titleTextView = (TextView) findViewById(R.id.title_text);
        titleTextView.setText(detailData.title_name);
        TextView visitedDayView = (TextView) findViewById(R.id.visitedDay);
        visitedDayView.setText(detailData.visitedDay);
        TextView visitedTimeView = (TextView) findViewById(R.id.visitedTime);
        visitedTimeView.setText(detailData.visitiedTime);
        TextView viewsCounterView = (TextView) findViewById(R.id.viewsCounter);
        viewsCounterView.setText(String.valueOf(detailData.views_number));
        TextView likesCounterView = (TextView) findViewById(R.id.likesCounter);
        likesCounterView.setText(String.valueOf(detailData.likes_number));
        TextView descriptionView = (TextView) findViewById(R.id.description);
        descriptionView.setText(detailData.description);
        TextView tagView = (TextView) findViewById(R.id.tag);
        tagView.setText(detailData.getTagText());


        MyProfile profileData = ((GlobalData)getApplicationContext()).getProfile(picture_id);
        ImageView ivUserProfileImageView = (ImageView) findViewById(R.id.ivUserProfile);

        int ivUserProfileImageViewWidth = ivUserProfileImageView.getLayoutParams().width;
        int ivUserProfileImageViewHeight = ivUserProfileImageView.getLayoutParams().height;
        Bitmap ivUserProfileImageBitmap = Util.decodeSampledBitmapFromResource(getResources(), profileData.profile_img_src, ivUserProfileImageViewWidth, ivUserProfileImageViewHeight);
        ivUserProfileImageView.setImageBitmap(ivUserProfileImageBitmap);
        TextView useridView = (TextView) findViewById(R.id.userid);
        useridView.setText(profileData.user_name);
        TextView userDescriptionView = (TextView) findViewById(R.id.userDescription);
        userDescriptionView.setText(profileData.user_description);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
