package com.example.bangchangbae.friendlyapplication.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bangchangbae on 16. 9. 1..
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    Context mContext;
    GestureDetector mGestureDetector;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onLongItemClick(View view, int position);
    }
    private OnItemClickListener mListener;


    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        mContext = context;
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                if(mListener == null)
                    return;
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(child != null){
                    mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if(mListener == null)
            return false;

        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if(child == null)
            return false;

        if(!mGestureDetector.onTouchEvent(e))
            return false;

        mListener.onItemClick(rv, rv.getChildAdapterPosition(child));
        return true;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
