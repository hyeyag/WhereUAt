package com.example.bangchangbae.friendlyapplication.data;

/**
 * Created by bangchangbae on 16. 8. 29..
 */
public class MyDetail {
    public int feed_img_src = 0;
    public int[] thumbnail_image_src_array = null;
    public String [] schedule = null;
    public String title_name = null;
    public String visitedDay = null;
    public String visitiedTime = null;
    public int views_number = 0;
    public int likes_number = 0;
    public String description = null;
    public String [] tags = null;

    public MyDetail(int feed_img_src, int[] thumbnail_image_src_array, String[] schedule, String title_name,
             String visitedDay, String visitiedTime, int views_number, int likes_number,
             String description, String[] tags){
        this.feed_img_src = feed_img_src;
        this.thumbnail_image_src_array = thumbnail_image_src_array;
        this.schedule = schedule;
        this.title_name = title_name;
        this.visitedDay = visitedDay;
        this.visitiedTime = visitiedTime;
        this.views_number = views_number;
        this.likes_number = likes_number;
        this.description = description;
        this.tags = tags;
    }
    public String getScheduleText(){
        return getFormattedText(schedule, "#", " > ");
    }
    public String getTagText(){
        return getFormattedText(tags, "#", " ");
    }
    public String getFormattedText(String[] strings, String preFormat, String postFortmat){
        String result = "";
        for (int i=0; i<strings.length; i++) {
            result += preFormat + strings[i];
            if(i < strings.length-1)
                result += postFortmat;
        }
        return result;
    }
}
