package com.example.bangchangbae.friendlyapplication.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
    private List<Integer> pictureList = new ArrayList<>();

    public MySearchResult(SearchType type, String title){
        mType = type;
        mResultTitle = title;
    }
    public String getSubScribe(){
        return "관련글 " + getCountStringComma()+" 개";
    }
    public void addPicture(Integer pictureID){
        if(pictureList.contains(pictureID))
            return;
        pictureList.add(pictureID);
    }
    public void removePicture(Integer pictureID){
        int index = pictureList.lastIndexOf(pictureID);
        if(index == -1)
            return;
        pictureList.remove(index);
    }
    public int getCount(){
        return pictureList.size();
    }
    private String getCountStringComma() {
        long value = pictureList.size();
        DecimalFormat format = new DecimalFormat("###,###");
        return format.format(value);
    }
    public int[] getPictureIdList(){
        int[] result = new int[pictureList.size()];
        for (int i=0; i<pictureList.size(); i++)
            result[i] = pictureList.get(i);
        return result;
    }
}
