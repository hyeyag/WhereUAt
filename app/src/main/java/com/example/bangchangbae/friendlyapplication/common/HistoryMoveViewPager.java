package com.example.bangchangbae.friendlyapplication.common;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by bangchangbae on 16. 8. 31..
 */
public class HistoryMoveViewPager extends ViewPager {
    private Stack<Integer> mHistory = new Stack();
    private int mPreviousItem = -1;
    private List<OnPageChangeListener> mOnPageChangeListenersSaved;
    private OnPageChangeListener mOnPageChangeListenerSaved;

    public HistoryMoveViewPager(Context context) {
        super(context);
    }

    public HistoryMoveViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setCurrentItem(int item) {
        mPreviousItem = item;
        super.setCurrentItem(item);
        Log.d("viewpager steps", "setCurrentItem item  : " + item + " previousItem : " + mPreviousItem);

    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        mPreviousItem = item;
        super.setCurrentItem(item, smoothScroll);
        Log.d("viewpager steps", "setCurrentItem item  : " + item + " previousItem : " + mPreviousItem);

    }

    protected void move(int item, boolean isSaveHistory){
        if(isSaveHistory) {
            int prevItem = (mPreviousItem > -1) ? mPreviousItem : 0;
            Log.d("viewpager steps", "move item  : " + item + " prevItem : " + prevItem);
            mHistory.push(prevItem);
        }
        Log.d("viewpager steps", "move item  : " + item + " isSaveHistory : " + isSaveHistory);
        setCurrentItem(item);
    }
    public void Go(int item){
        Log.d("viewpager steps", "Go item  : " + item);
        move(item, true);
    }
    public boolean Back(){
        Log.d("viewpager steps", "Back history size  : " + mHistory.size());
        if(mHistory.size() < 1)
            return false;
        int preIndex = mHistory.pop();
        String history;
        history = "";
        Log.d("viewpager steps", "preIndex  : " + preIndex);
        for(int i=0; i<mHistory.size();i++) {
            history += mHistory.get(i) + " ";
        }
        Log.d("viewpager steps", "Back history   : " + history);
        Log.d("viewpager steps", "Back history size  : " + mHistory.size());

        preventOnPageChangeEvent();
        move(preIndex, false);
        restoreOnPageChangeListener();
        Log.d("viewpager steps", "Back history size  : " + mHistory.size());
        return true;
    }

    @Deprecated
    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mOnPageChangeListenerSaved = listener;
        super.setOnPageChangeListener(listener);
    }

    @Override
    public void addOnPageChangeListener(OnPageChangeListener listener) {
        if (mOnPageChangeListenersSaved == null) {
            mOnPageChangeListenersSaved = new ArrayList<>();
        }
        mOnPageChangeListenersSaved.add(listener);
        super.addOnPageChangeListener(listener);
    }

    @Override
    public void removeOnPageChangeListener(OnPageChangeListener listener) {
        if (mOnPageChangeListenersSaved != null) {
            mOnPageChangeListenersSaved.remove(listener);
        }
        super.removeOnPageChangeListener(listener);
    }

    @Override
    public void clearOnPageChangeListeners() {
        if (mOnPageChangeListenersSaved != null) {
            mOnPageChangeListenersSaved.clear();
        }
        super.clearOnPageChangeListeners();
    }


    private void preventOnPageChangeEvent() {
        super.clearOnPageChangeListeners();
        super.setOnPageChangeListener(null);
    }
    private void restoreOnPageChangeListener(){
        if (mOnPageChangeListenersSaved != null) {
            for (int i = 0, z = mOnPageChangeListenersSaved.size(); i < z; i++) {
                OnPageChangeListener listener = mOnPageChangeListenersSaved.get(i);
                if (listener != null) {
                    super.addOnPageChangeListener(listener);
                }
            }
        }
        super.setOnPageChangeListener(mOnPageChangeListenerSaved);
    }
}
