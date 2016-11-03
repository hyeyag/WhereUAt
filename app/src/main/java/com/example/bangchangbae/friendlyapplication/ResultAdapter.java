package com.example.bangchangbae.friendlyapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bangchangbae.friendlyapplication.common.Util;
import com.example.bangchangbae.friendlyapplication.content.SearchContentActivity;
import com.example.bangchangbae.friendlyapplication.data.MySearchResult;

import java.util.List;

/**
 * Created by bangchangbae on 2016. 9. 1..
 */
public class ResultAdapter extends BaseAdapter {
    private Context mContext;
    private List<MySearchResult> mResultList = null;
    public ResultAdapter(Context context) {
        this.mContext = context;
    }

    public void updateResults(List<MySearchResult> results) {
        mResultList = results;
        notifyDataSetChanged();
        Log.v("search list", "update : " + mResultList.size());
    }
    @Override
    public int getCount() {
        return mResultList == null ? 0 : mResultList.size();
    }

    @Override
    public Object getItem(int position) {
        return mResultList == null ? null : mResultList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result, parent, false);
        }
        TextView titleTextView = (TextView)convertView.findViewById(R.id.search_title);
        String type = mResultList.get(pos).mType == MySearchResult.SearchType.HASH_TAG ? "#" :
                (mResultList.get(pos).mType == MySearchResult.SearchType.LOCATION ? "V" :
                (mResultList.get(pos).mType == MySearchResult.SearchType.PERSION_ID ? "P" : ""));
        titleTextView.setText(type + " " + mResultList.get(pos).mResultTitle);

        TextView subscriptTextView = (TextView)convertView.findViewById(R.id.search_subscript);
        subscriptTextView.setText(mResultList.get(pos).getSubScribe());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = mContext;
                Intent intent = new Intent(context, SearchContentActivity.class);
                intent.putExtra(Util.SEARCH_KEYWORD, mResultList.get(pos).mResultTitle);
                intent.putExtra(Util.PICKTURE_ID_LIST, mResultList.get(pos).getPictureIdList());
                context.startActivity(intent);
            }
        });
        Log.v("search list", "title[" + pos + "] : " + mResultList.get(pos).mResultTitle);
        Log.v("search list", "subscribe[" + pos + "] : " + mResultList.get(pos).getSubScribe());


        return convertView;
    }
}
