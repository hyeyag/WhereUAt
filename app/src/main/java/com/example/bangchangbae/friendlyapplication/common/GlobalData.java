package com.example.bangchangbae.friendlyapplication.common;

import android.app.Application;

import com.example.bangchangbae.friendlyapplication.R;
import com.example.bangchangbae.friendlyapplication.data.MyDetail;
import com.example.bangchangbae.friendlyapplication.data.MyFeed;
import com.example.bangchangbae.friendlyapplication.data.MyProfile;
import com.example.bangchangbae.friendlyapplication.data.MySearchResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by bangchangbae on 16. 8. 29..
 */
public class GlobalData extends Application {

    public enum GRID_TYPE {
        NONE,
        FAVORITE,
        UPLOADED
    }
    private ArrayList<LowData> lowData;
    private MyProfile me = null;
    int[] default_thumbnails = {R.drawable.thumbnail_001, R.drawable.thumbnail_002, R.drawable.thumbnail_003, R.drawable.thumbnail_004};

    public GlobalData() {

        me = new MyProfile(R.drawable.mon, "웨얼유앳(Where U At)", "VR기기를 통해 화면 속 상황을 더욱 생생하게! 여행계획 시 필요한 꿀팁과 일정까지 GET!!!");
        MyProfile yourProfile = new MyProfile(R.drawable.mac, "other", "others profile todo fill in");



        lowData = new ArrayList<>();
        LowData data_001 = new LowData(R.drawable.img_feed_center_1,
                "[대학로] 이화벽화마을", "2016.05.23", "05:00 pm",
                27, 123,
                "제가 촬영하며 여행한 이곳은 대학로의 ‘이화벽화마을’입니다.",
                default_thumbnails,
                new String[]{"대학로", "여의도", "영등포"},
                new String[]{"대학로", "이화벽화마을", "포토존", "봄사진", "인생사진", "꽃벽화", "낙산공원"},
                me, false);
        lowData.add(data_001);
        LowData data_002 = new LowData(R.drawable.junju,
                "[전주] 전주 한옥마을", "2016.05.23", "05:00 pm",
                34, 123,
                "제가 촬영하며 여행한 이곳은 대학로의 ‘이화벽화마을’입니다.",
                default_thumbnails,
                new String[]{"전주", "한옥", "마을"},
                new String[]{"대학로", "전주", "한옥"},
                me, true);
        lowData.add(data_002);
        LowData data_003 = new LowData(R.drawable.songdo,
                "[부산] 송도 해수욕장", "2016.05.23", "05:00 pm",
                53, 123,
                "제가 촬영하며 여행한 이곳은 대학로의 ‘이화벽화마을’입니다.",
                default_thumbnails,
                new String[]{"서울", "대구", "부산"},
                new String[]{"대학로", "부산", "송도", "해운대"},
                me, false);
        lowData.add(data_003);
        LowData data_004 = new LowData(R.drawable.dome,
                "[구로] 고척 스카이돔", "2016.05.23", "05:00 pm",
                53, 123,
                "제가 촬영하며 여행한 이곳은 대학로의 ‘이화벽화마을’입니다.",
                default_thumbnails,
                new String[]{"대학로", "여의도", "영등포"},
                new String[]{"대학로", "구로", "고척", "허구돔"},
                me, true);
        lowData.add(data_004);
        LowData data_005 = new LowData(R.drawable.img_feed_center_2,
                "[해외] 서핑장", "2016.05.23", "05:00 pm",
                27, 123,
                "제가 촬영하며 여행한 이곳은 대학로의 ‘이화벽화마을’입니다.",
                default_thumbnails,
                new String[]{"인천", "비행기", "어딘가"},
                new String[]{"인천", "해외", "묻지마", "인생사진"},
                yourProfile, true);
        lowData.add(data_005);

    }

    public List<MyFeed> getFeedList(){
        List<MyFeed> myList = new ArrayList<>();
        for(int i=0; i<lowData.size(); i++){
            myList.add(lowData.get(i).getFeedData());
        }
        return myList;
    }
    public List<Integer> getMyThumbnailImageList(GRID_TYPE type){
        List<Integer> thumbanils = new ArrayList<>();
        if(type == GRID_TYPE.NONE) {
            thumbanils = null;
        }
        else if(type == GRID_TYPE.FAVORITE) {
            for (int i=0; i<lowData.size(); i++){
                if(lowData.get(i).isFarivorite)
                    thumbanils.add(lowData.get(i).getImageId());
            }
        }
        else if(type == GRID_TYPE.UPLOADED) {
            for (int i=0; i<lowData.size(); i++){
                if(lowData.get(i).getUploaderProfile() == getMyProfile())
                    thumbanils.add(lowData.get(i).getImageId());
            }
        }
        else{
            thumbanils = null;
        }
        return thumbanils;
    }
    public List<Integer> getMyThumbnailFavoriteImageList(String query){
        List<Integer> thumbanils = new ArrayList<>();

        for (int i=0; i<lowData.size(); i++){
            if(lowData.get(i).isFarivorite){
                for(int j=0; j<lowData.get(i).tags.length; j++){
                    if(query.equals(lowData.get(i).tags[j])) {
                        thumbanils.add(lowData.get(i).getImageId());
                        break;
                    }
                }
            }
        }
        return thumbanils;
    }
    public MyDetail getDetail(int pictureId){
        MyDetail result = null;
        for (int i=0; i<lowData.size(); i++){
            if(pictureId == lowData.get(i).getImageId()){
                result = lowData.get(i).getDetailData();
                break;
            }
        }
        return result;
    }

    private MySearchResult isContain(List<MySearchResult> list, String tag){
        MySearchResult result = null;
        for(int i=0; i<list.size();i++){
            if(list.get(i).mResultTitle.equals(tag)){
                result = list.get(i);
                break;
            }
        }
        return result;
    }
    public List<MySearchResult> getSearchResult(final String querry){
        List<MySearchResult> mResultList = new ArrayList<>();
        List<String> relativeTags = getRelativeTag(querry);

        for(int i=0; i<relativeTags.size();i++){
            MySearchResult result = getMySearchResult(relativeTags.get(i));
            mResultList.add(result);
        }

        Collections.sort(mResultList, new Comparator<MySearchResult>(){
            public int compare(MySearchResult obj1, MySearchResult obj2)
            {
                int result = -1;
                if(obj1.mResultTitle.equals(querry))
                    result = -1;
                else if(obj2.mResultTitle.equals(querry))
                    result = 1;
                else {
                    if(obj1.getCount() > obj2.getCount())
                        result = -1;
                    else if(obj1.getCount() < obj2.getCount())
                        result = 1;
                    else
                        result = obj1.mResultTitle.compareToIgnoreCase(obj2.mResultTitle);
                }
                return result;
            }
        });
        return mResultList;
    }
    private List<String> addTags(List<String> relativeTags, String[] tags){
        List<String> result = new ArrayList<>();
        for (String tag : tags) {
            if (relativeTags.indexOf(tag) == -1)
                result.add(tag);
        }
        result.addAll(relativeTags);
        return result;
    }
    private List<String> getRelativeTag(String querry){
        List<String> relativeTags = new ArrayList<>();

        for (int i=0; i<lowData.size(); i++){
            String[] tags = lowData.get(i).tags;
            for (String tag : tags) {
                if (querry.equals(tag)) {
                    relativeTags = addTags(relativeTags, tags);
                    break;
                }
            }
        }
        return relativeTags;
    }
    public MySearchResult getMySearchResult(String querry){
        MySearchResult result = new MySearchResult(MySearchResult.SearchType.HASH_TAG, querry);

        for (int i=0; i<lowData.size(); i++){
            String[] tags = lowData.get(i).tags;
            for (String tag : tags) {
                if (querry.equals(tag)) {
                    result.addPicture(lowData.get(i).feed_img_src);
                    break;
                }
            }
        }
        return result;
    }
    public MyProfile getProfile(int pictureId){
        MyProfile myProfile = null;
        for(int i=0; i<lowData.size(); i++){
            if(lowData.get(i).getImageId() == pictureId) {
                myProfile = lowData.get(i).getUploaderProfile();
                break;
            }
        }
        return myProfile;
    }
    public MyProfile getMyProfile(){
        return me;
    }
    private class LowData{
        public int feed_img_src = 0;


        public String title_name = null;
        public String visitedDay = null;
        public String visitiedTime = null;
        public int views_number = 0;
        public int likes_number = 0;
        public String description = null;

        public int[] thumbnail_image_src_array = null;
        public String [] schedule = null;
        public String [] tags = null;

        public boolean isFarivorite = false;

        MyProfile uploader = null;
        public LowData(int feed_img_src, String title_name, String visitedDay, String visitiedTime,
                       int views_number, int likes_number, String description,
                       int[] thumbnail_image_src_array, String [] schedule, String [] tags,
                       MyProfile uploader, boolean isFarivorite){
            this.feed_img_src = feed_img_src;
            this.title_name = title_name;
            this.visitedDay = visitedDay;
            this.visitiedTime = visitiedTime;
            this.views_number = views_number;
            this.likes_number = likes_number;
            this.description = description;
            this.thumbnail_image_src_array = thumbnail_image_src_array;
            this.schedule = schedule;
            this.tags = tags;
            this.uploader = uploader;
            this.isFarivorite = isFarivorite;

        }
        public MyProfile getUploaderProfile(){
            return uploader;
        }
        public Integer getImageId(){
            return feed_img_src;
        }
        public MyFeed getFeedData() {
            MyFeed result = new MyFeed(feed_img_src, uploader.profile_img_src, title_name, views_number, likes_number);
            return result;
        }
        public MyDetail getDetailData(){
            MyDetail result = new MyDetail(feed_img_src, thumbnail_image_src_array, schedule,
                    title_name, visitedDay, visitiedTime, views_number, likes_number,
                    description, tags);
            return result;
        }
    }


}
