package com.example.bangchangbae.friendlyapplication.data;

/**
 * Created by bangchangbae on 2016. 9. 1..
 */
public class MySearchResult {
    public enum SearchType{
        NONE,
        HASH_TAG,
        LOCATION,
        PERSION_ID
    }
    public SearchType mType = SearchType.NONE;
    public String mResultTitle = "";
    public String mSubscribe = "";

    public MySearchResult(SearchType type, String title, String subscribe){
        mType = type;
        mResultTitle = title;
        mSubscribe = subscribe;
    }
}
