package com.example.bangchangbae.friendlyapplication.data;

/**
 * Created by bangchangbae on 2016. 10. 11..
 */

public class MyProfile {
    public int profile_img_src = 0;
    public String user_name = null;
    public String user_description = null;

    public MyProfile(int profile_img_src, String user_name, String user_description){
        this.profile_img_src = profile_img_src;
        this.user_name = user_name;
        this.user_description = user_description;
    }
    /*@+id/ivUserProfile : @drawable/ic_profile"
    @+id/userid : "웨얼유앳(Where U At)"
    @+id/userDescription : "VR기기를 통해 화면 속 상황을 더욱 생생하게! 여행계획 시 필요한 꿀팁과 일정까지 GET!!!"*/
}
