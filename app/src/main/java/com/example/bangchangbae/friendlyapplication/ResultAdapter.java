package com.example.bangchangbae.friendlyapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bangchangbae.friendlyapplication.content.SearchContentActivity;
import com.example.bangchangbae.friendlyapplication.content.WriteContentActivity;
import com.example.bangchangbae.friendlyapplication.data.MySearchResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bangchangbae on 2016. 9. 1..
 */
public class ResultAdapter extends BaseAdapter {
    private Context mContext;
    private List<MySearchResult> mResultList;
    public ResultAdapter(Context context) {
        this.mContext = context;
        this.mResultList = new ArrayList<>();
        MySearchResult one = new MySearchResult(MySearchResult.SearchType.HASH_TAG, "해운대", "관련글 199,627 개");
        MySearchResult two = new MySearchResult(MySearchResult.SearchType.HASH_TAG, "해운대맛", "관련글 111,637 개");
        MySearchResult three = new MySearchResult(MySearchResult.SearchType.HASH_TAG, "해운대", "관련글 9,627 개");
        this.mResultList.add(one);
        this.mResultList.add(two);
        this.mResultList.add(three);
    }

    @Override
    public int getCount() {
        return mResultList.size();
    }

    @Override
    public Object getItem(int position) {
        return mResultList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result, parent, false);

            TextView titleTextView = (TextView)convertView.findViewById(R.id.search_title);
            String type = mResultList.get(pos).mType == MySearchResult.SearchType.HASH_TAG ? "#" :
                    (mResultList.get(pos).mType == MySearchResult.SearchType.LOCATION ? "V" :
                    (mResultList.get(pos).mType == MySearchResult.SearchType.PERSION_ID ? "P" : ""));
            titleTextView.setText(type + " " + mResultList.get(pos).mResultTitle);

            TextView subscriptTextView = (TextView)convertView.findViewById(R.id.search_subscript);
            subscriptTextView.setText(mResultList.get(pos).mSubscribe);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = mContext;
                    Intent intent = new Intent(context, SearchContentActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }
}
