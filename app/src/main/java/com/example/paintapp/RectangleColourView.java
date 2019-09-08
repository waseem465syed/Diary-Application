package com.example.paintapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class RectangleColourView extends View {




    private onColourClickListener listener=null;
  private Paint colourPaint;

  private int[] colours= {1783434,25500, 2551400, Color.YELLOW, 3413934,135206250,00255,
                            190190190,000,16032240, 238130238};


    public RectangleColourView(Context context) {
        super(context);
        init();



    }

    public RectangleColourView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public RectangleColourView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public RectangleColourView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        colourPaint = new Paint();
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        int width = getWidth()/colours.length;
        for (int i = 0; i<colours.length;i++){
            colourPaint.setColor(colours[i]);
            canvas.drawRect(i*width,0, (i+1)*width, getHeight(),colourPaint);
        }
    }

    public void setOnColourClickListener() {

    }

    public interface onColourClickListener{
        public void changeColour(int    colour);
    }

    public void setOnColourClickListener(onColourClickListener listener){
        this.listener= listener;
    }
    public boolean onTouchEvent(MotionEvent event){
        if (listener!=null && event.getAction()==MotionEvent.ACTION_DOWN){
            float x=event.getX();

            int width = getWidth()/colours.length;

            int selectedColourPositionInArray = (int) x/width;
            listener.changeColour(colours[selectedColourPositionInArray]);
        }

        return true;
    }



}
