package com.example.paintapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.Nullable;

public class CanvasView extends View {

    private Paint penPaint;
    private Path penPath;
    private Bitmap mainBitmap;
    private Canvas mainCanvas;
    private Paint mainBitmapPaint;




    public CanvasView(Context context) {
        super(context);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        penPaint = new Paint();
        penPath = new Path();
        penPaint.setStyle(Paint.Style.STROKE);
        penPaint.setStrokeWidth(8.0f);

        penPaint.setAntiAlias(true);
        mainBitmapPaint = new Paint();
    }

    protected void onSizechanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w,h,oldw,oldh);

        mainBitmap= Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        mainCanvas = new Canvas(mainBitmap);
    }

    private float LastX;
    private float LastY;


    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawPath(penPath,penPaint);

        canvas.drawBitmap(mainBitmap,0,0,mainBitmapPaint);
    }

    public boolean onTouchEven(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                penPath.moveTo(x,y);
                LastX = x;
                LastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                 penPath.quadTo(LastX,LastY,(LastX+x)/2, (LastY+y)/2);
                 LastY = y;
                 LastX = x;
                 break;
            case MotionEvent.ACTION_UP:
                penPath.lineTo(x,y);
                mainCanvas.drawPath(penPath,penPaint);

                penPath.reset();
                break;

        }

        invalidate();
        return true;
    }

    public void setBackgroundColour(int colour){
        mainCanvas.drawColor(colour);
        postInvalidate();
    }

    public void setCanvasTextColour(int colour){
        penPaint.setColor(colour);
        postInvalidate();
    }

    public void clearCanvas(){
        mainCanvas.drawColor(0,PorterDuff.Mode.CLEAR);
        postInvalidate();
    }

    public Bitmap getMainBitmap(){
        return mainBitmap;
    }




}
