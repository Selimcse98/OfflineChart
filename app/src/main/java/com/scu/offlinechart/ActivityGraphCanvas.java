package com.scu.offlinechart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class ActivityGraphCanvas extends View {
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...

    private TextPaint mTextPaint;
    private Paint paint;

    public ActivityGraphCanvas(Context context) {
        super(context);
        init(null, 0);
    }

    public ActivityGraphCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ActivityGraphCanvas(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
       // final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ActivityGraphCanvas, defStyle, 0);
       // mExampleColor = a.getColor(Color.CYAN,mExampleColor);
       // mExampleDimension = a.getDimension(0,mExampleDimension);
       // a.recycle();
        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        paint = new Paint();
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
    }

    public void setData(byte []data) {
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        //System.out.println("Content Height at ActivityGraphCanvas : " + getHeight());
        paint.setColor(Color.argb(255, 215, 212, 212));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint); //Fill the entire canvas wiht color

//        paint.setColor(Color.BLUE);
//        paint.setTextSize(50);
//        canvas.drawText("Activity Graph", 350, 45, paint);

        drawCrissCrossLines(canvas, paint, getWidth(), getHeight());


//        Log.d("DRAW", "drawing");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ee) {
//            // TODO Auto-generated catch block
//            ee.printStackTrace();
//        }
    }

    public void drawCrissCrossLines(Canvas canvas, Paint paint,int width, int height)
    {
        //paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.argb(255, 255, 155,155));

        for(int i=0;i<=width;i+=50){
            for(int j=0;j<=height;j+=50){
                canvas.drawLine(0,j,width,j,paint);
                canvas.drawLine(i,0,i,height,paint);
            }
        }
    }
}