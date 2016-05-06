package com.scu.offlinechart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.telephony.SmsManager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class ChartView extends View {
    //    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;
    private byte []data;
    private Paint paint;

    public ChartView(Context context) {
        super(context);
        init(null, 0);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
//        final TypedArray a = getContext().obtainStyledAttributes(
//                attrs, R.styleable.ChartView, defStyle, 0);

        mExampleColor = Color.CYAN;

        //a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        paint = new Paint();
        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
//        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        //mTextHeight = fontMetrics.top;
        mTextHeight = fontMetrics.bottom;
    }

    public void setData(byte []data) {
        this.data = data;
        invalidate();
//        System.out.println(qrsCheck.setData(data));
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

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint); //Fill the entire canvas wiht white color


        drawCrissCrossLines(canvas, paint, getWidth(), getHeight());
        drawEcgGraph(canvas, paint, contentHeight);

        Log.d("DRAW", "drawing");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ee) {
            // TODO Auto-generated catch block
            ee.printStackTrace();
        }
    }


    private void drawEcgGraph(Canvas canvas, Paint paint, int contentHeight) {
        //if(heartRateQRS>100 || (heartRateQRS<60 && heartRateQRS>35))
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("ECG Graph", 150, 50, paint);
        paint.setStrokeWidth(3);

        if (this.data != null && this.data.length > 0) {
            float x1,y1,x2,y2;
            //float j = (float) 1.5;
            //x1 = contentWidth / 5;
            x1 = 0;
            y1 = contentHeight/8-100;
            for(int i = 0; i < this.data.length - 2; i++){
                //canvas.drawLine(i, this.data[i] + contentHeight/2, (i+1), this.data[i+1] + contentHeight/2, paint);
                //x2 = contentWidth / 5 + i;
                x2 = i+1;
                y2 =  contentHeight/16 - (float)(this.data[i]*2.8);
                //System.out.println("y2="+y2+"\ndata="+this.data[i]+"\n");
                if(y2>390)
                    y2 = 390; //cliff off


//                x2 = contentWidth / 5 + i + 1;
//                y2 =  contentHeight*3/5 - this.data[i+1];
                canvas.drawLine(x1, y1, x2, y2, paint);

                //Log.d("ECG_VAL: "+i, " x1=" + x1 + " y1=" + y1 + ", x2=" + x2 + " y2=" + y2 + " Width=" + getWidth() + " Height=" + getHeight() + " Data=" + this.data[i]);
                x1=x2;
                y1=y2;
            }
        }
    }

   public void drawCrissCrossLines(Canvas canvas, Paint paint,int width, int height)
    {
        //paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        paint.setColor(Color.argb(255,255,155,155));

        for(int i=0;i<=width;i+=50){
            for(int j=0;j<=height;j+=50){
                canvas.drawLine(0,j,width,j,paint);
                canvas.drawLine(i,0,i,height,paint);
            }
        }
    }



}