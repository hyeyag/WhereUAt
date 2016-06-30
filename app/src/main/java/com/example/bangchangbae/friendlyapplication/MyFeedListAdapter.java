package com.example.bangchangbae.friendlyapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bangchangbae on 16. 6. 29..
 */
public class MyFeedListAdapter extends RecyclerView.Adapter<MyFeedListAdapter.ViewHolder> {
    private List<MyFeed> myList;
    private Context context;

    public MyFeedListAdapter(Context context) {
        this.myList = new ArrayList<>();
        this.context = context;
        this.update();
    }
    public void update(){
        this.myList.clear();
        MyFeed one = new MyFeed("", "", "[TEST] one", 111, 11111);
        MyFeed two = new MyFeed("", "", "[TEST] two", 211, 21111);
        MyFeed three = new MyFeed("", "", "[TEST] three", 311, 31111);
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
        MyFeed my = myList.get(position);

        //new DownloadImageTask(holder.mFeedCenterImageView).execute(my.feed_img_src);
        //new DownloadImageTask(holder.mProfileImageView).execute(my.profile_img_src);
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
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
    public class MyFeed {
        public String feed_img_src = null;
        public String profile_img_src = null;
        public String title_name = null;
        public int views_number = 0;
        public int likes_number = 0;
        public MyFeed(String feed_img_src, String profile_img_src, String title_name, int views_number, int likes_number){
            this.feed_img_src = feed_img_src;
            this.profile_img_src = profile_img_src;
            this.title_name = title_name;
            this.views_number = views_number;
            this.likes_number = likes_number;
        }
    }
}
