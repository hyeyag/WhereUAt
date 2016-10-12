package com.example.bangchangbae.friendlyapplication.content;

import android.app.Activity;
import android.os.Bundle;

import com.example.bangchangbae.friendlyapplication.R;

/**
 * Created by bangchangbae on 16. 9. 1..
 */
public class WriteContentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_editor);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
