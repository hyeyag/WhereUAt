package com.example.bangchangbae.friendlyapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.example.bangchangbae.friendlyapplication.common.Util;
import com.example.bangchangbae.friendlyapplication.data.MyFeed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bangchangbae on 16. 6. 29..
 */
public class MyFeedListAdapter extends RecyclerView.Adapter<MyFeedListAdapter.ViewHolder> {
    private List<MyFeed> myList;
    private Context mContext;

    public MyFeedListAdapter(Context context) {
        this.myList = new ArrayList<>();
        this.mContext = context;
        this.update();
    }
    public void update(){
        this.myList.clear();
        MyFeed one = new MyFeed(R.drawable.songdo, R.drawable.mac, "[부산] 송도 해수욕장", 27, 8);
        MyFeed two = new MyFeed(R.drawable.junju, R.drawable.mon, "[전주] 전주 한옥마을", 34, 10);
        MyFeed three = new MyFeed(R.drawable.dome, R.drawable.hyo, "[구로] 고척 스카이돔", 52, 21);
        myList.add(one);
        myList.add(two);
        myList.add(three);
        myList.add(one);
        myList.add(two);
        myList.add(three);
        myList.add(one);
        myList.add(two);
        myList.add(three);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("view index", "recycler index : " + position);
        MyFeed my = myList.get(position);

        //new DownloadImageTask(holder.mFeedCenterImageView).execute(my.feed_img_src);
        int mFeedCenterImageWidth = holder.mFeedCenterImageView.getLayoutParams().width;
        int mFeedCenterImageHeight = holder.mFeedCenterImageView.getLayoutParams().height;
        Bitmap mFeedCenterImageBitmap = resizeResource(my.feed_img_src, mFeedCenterImageWidth, mFeedCenterImageHeight);
        holder.mFeedCenterImageView.setImageBitmap(mFeedCenterImageBitmap);

        //new DownloadImageTask(holder.mProfileImageView).execute(my.profile_img_src);
        int mProfileImageViewWidth = holder.mProfileImageView.getLayoutParams().width;
        int mProfileImageViewHeight = holder.mProfileImageView.getLayoutParams().height;
        Bitmap mProfileImageBitmap = resizeResource(my.profile_img_src, mProfileImageViewHeight, mProfileImageViewWidth);
        holder.mProfileImageView.setImageBitmap(mProfileImageBitmap);

        holder.mFeedTitleTextView.setText(my.title_name);
        holder.mLikesNumberTextSwitcher.setCurrentText(holder.mView.getResources().getQuantityString(R.plurals.likes_count, my.likes_number, my.likes_number));
        holder.mViewsNumberTextSwitcher.setCurrentText(holder.mView.getResources().getQuantityString(R.plurals.views_count, my.views_number, my.views_number));

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mFeedCenterImageView;
        public final ImageView mProfileImageView;
        public final TextView mFeedTitleTextView;
        public final TextSwitcher mLikesNumberTextSwitcher;
        public final TextSwitcher mViewsNumberTextSwitcher;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mFeedCenterImageView = (ImageView) view.findViewById(R.id.ivFeedCenter);
            mProfileImageView = (ImageView) view.findViewById(R.id.ivUserProfile);
            mFeedTitleTextView = (TextView) view.findViewById(R.id.ivFeedTitle);
            mLikesNumberTextSwitcher = (TextSwitcher) view.findViewById(R.id.tsLikesCounter);
            mViewsNumberTextSwitcher = (TextSwitcher) view.findViewById(R.id.tsViewCounter);
        }

    }

    private Bitmap resizeResource(int imageResourceId, int width, int height){
        return Util.decodeSampledBitmapFromResource(mContext.getResources(), imageResourceId, width, height);
    }
}
