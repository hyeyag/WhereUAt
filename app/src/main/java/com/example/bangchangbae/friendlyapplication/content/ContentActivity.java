package com.example.bangchangbae.friendlyapplication.content;

import android.app.Activity;
import android.os.Bundle;

import com.example.bangchangbae.friendlyapplication.R;

/**
 * Created by bangchangbae on 16. 9. 1..
 */
public class ContentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
