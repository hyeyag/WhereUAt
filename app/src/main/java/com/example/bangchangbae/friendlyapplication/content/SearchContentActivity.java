package com.example.bangchangbae.friendlyapplication.content;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.bangchangbae.friendlyapplication.ImageAdapter;
import com.example.bangchangbae.friendlyapplication.R;
import com.example.bangchangbae.friendlyapplication.common.GlobalData;
import com.example.bangchangbae.friendlyapplication.common.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bangchangbae on 2016. 9. 1..
 */
public class SearchContentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_with_grid);


        Intent intent = getIntent();
        String search_keyword = intent.getStringExtra(Util.SEARCH_KEYWORD);
        TextView keywordTextView = (TextView)findViewById(R.id.search_keyword_text);
        keywordTextView.setText(search_keyword);

        int [] imageList =  intent.getIntArrayExtra(Util.PICKTURE_ID_LIST);
        List<Integer> ImageList = new ArrayList<>();
        for (int anImageList : imageList) ImageList.add(anImageList);

        GridView infoGrid = (GridView)findViewById(R.id.gridview);
        final ImageAdapter imageAdapter = new ImageAdapter(GlobalData.GRID_TYPE.NONE, this);
        imageAdapter.updateResults(ImageList);
        infoGrid.setAdapter(imageAdapter);
        infoGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ContentActivity.class);
                int pictureId = imageAdapter.getPictureId(position);
                intent.putExtra(Util.PICTURE_ID, pictureId);
                context.startActivity(intent);
            }
        });

        Button backButton = (Button)findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchContentActivity.super.onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
