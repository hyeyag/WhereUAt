package com.example.bangchangbae.friendlyapplication.common;

import android.app.Application;

import com.example.bangchangbae.friendlyapplication.data.MyDetail;
import com.example.bangchangbae.friendlyapplication.data.MyFeed;

import java.util.List;

/**
 * Created by bangchangbae on 16. 8. 29..
 */
public class GlobalData extends Application {
    public List<MyFeed> getFeedList(){
        return null;
    }
    public Integer[] getMyThumbnailImageList(){
        return null;
    }
    public MyDetail getDetail(int index){
        return null;
    }
}
