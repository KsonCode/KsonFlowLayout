package com.kson.flowlayout;import android.content.Context;import android.util.AttributeSet;import android.util.Log;import android.view.ViewGroup;public class FlowLayout extends ViewGroup {//    public int mLeftMargin = 20;//    public int mTopMargin = 20;    public FlowLayout(Context context) {        super(context);    }    public FlowLayout(Context context, AttributeSet attrs) {        super(context, attrs);    }    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {        super(context, attrs, defStyleAttr);    }    @Override    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {        super.onMeasure(widthMeasureSpec, heightMeasureSpec);        Log.e("tag" , "onMeasure");        measureChildren(widthMeasureSpec ,heightMeasureSpec);        int leftMargin = 0;        int topMargin = 0;        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);        int modelWidth = MeasureSpec.getMode(widthMeasureSpec);        int sizeHeigth = MeasureSpec.getSize(heightMeasureSpec);        int modelHeigth = MeasureSpec.getMode(heightMeasureSpec);        switch (modelHeigth){            case MeasureSpec.AT_MOST:                int measuredHeight = 0 ;                for (int j = 0 ; j < getChildCount() ; j ++){                    int measuredWidth = getChildAt(j).getMeasuredWidth();                    measuredHeight = getChildAt(j).getMeasuredHeight();                    if (leftMargin + measuredWidth  > getMeasuredWidth()){                        leftMargin = 0;                        topMargin += measuredHeight ;                    }                    leftMargin += measuredWidth ;                }                topMargin += measuredHeight ;                break;        }        setMeasuredDimension(sizeWidth , topMargin);    }    @Override    protected void onLayout(boolean changed, int l, int t, int r, int b) {        Log.e("tag" , "onLayout");        int leftMargin = 0;        int topmargin = 0;        for (int j = 0 ; j < getChildCount() ; j ++){            int measuredHeight = getChildAt(j).getMeasuredHeight();            int measuredWidth = getChildAt(j).getMeasuredWidth();            if (leftMargin + measuredWidth  > getWidth()){                leftMargin = 0;                topmargin += measuredHeight ;                getChildAt(j).layout(leftMargin , topmargin , measuredWidth + leftMargin , measuredHeight + topmargin  );            }else {                getChildAt(j).layout(leftMargin , topmargin , measuredWidth + leftMargin ,measuredHeight + topmargin  );            }            leftMargin += measuredWidth ;        }    }}