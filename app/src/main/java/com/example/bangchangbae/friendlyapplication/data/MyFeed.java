package com.example.bangchangbae.friendlyapplication.data;

/**
 * Created by bangchangbae on 16. 8. 29..
 */
public class MyFeed {
    public int feed_img_src = 0;
    public int profile_img_src = 0;
    public String title_name = null;
    public int views_number = 0;
    public int likes_number = 0;
    public MyFeed(int feed_img_src, int profile_img_src, String title_name, int views_number, int likes_number){
        this.feed_img_src = feed_img_src;
        this.profile_img_src = profile_img_src;
        this.title_name = title_name;
        this.views_number = views_number;
        this.likes_number = likes_number;
    }
}