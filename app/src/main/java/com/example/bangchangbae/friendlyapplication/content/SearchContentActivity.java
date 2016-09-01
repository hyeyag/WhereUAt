package com.example.bangchangbae.friendlyapplication.content;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.bangchangbae.friendlyapplication.ImageAdapter;
import com.example.bangchangbae.friendlyapplication.R;

/**
 * Created by bangchangbae on 2016. 9. 1..
 */
public class SearchContentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_with_grid);

        GridView infoGrid = (GridView)findViewById(R.id.gridview);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        infoGrid.setAdapter(imageAdapter);

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
