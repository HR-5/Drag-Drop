package com.example.draganddrop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;


public class MyCanvasView extends View {
    int scnwid, scnht;
    Bitmap bit;
    Canvas mcan;
    Paint paint;
    int red, black;
    Rect rect;
    float X1, Y1, X2, Y2, centerX, centerY, lenght,breath;


    public MyCanvasView(Context context) {
        this(context, null);
    }

    public MyCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        rect = new Rect();
        red = ResourcesCompat.getColor(getResources(), R.color.toolbar, null);
        black = ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null);
        paint = new Paint();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        scnwid = displayMetrics.widthPixels;
        scnht = displayMetrics.heightPixels;
        centerX = scnwid / 2;
        centerY = scnht / 2;
        lenght = 700;
        breath = 700;
        X1 = centerX - lenght / 2;
        Y1 = centerY - breath / 2;
        X2 = centerX + lenght / 2;
        Y2 = centerY + breath / 2;
        setPaint(paint, red, 10, 50);
    }

    private void setPaint(Paint paint, int color, double stroke, int size) {
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setTextSize(size);
        paint.setStrokeWidth((float) stroke);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bit = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mcan = new Canvas(bit);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bit != null)
            canvas.drawBitmap(bit, 0, 0, null);
        mcan.drawRect(X1, Y1, X2, Y2, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float px = event.getX();
        float py = event.getY();
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            if(Math.abs(px - X1) < 80){
                    mcan.drawColor(black);
                    X1 = px;
                    lenght = X2 - X1;
                breath = Y2 - Y1;
                    invalidate();
            }
            else if(Math.abs(px - X2) < 80){
                mcan.drawColor(black);
                X2 = px;
                lenght = X2 - X1;
                breath = Y2 - Y1;
                invalidate();
            }
            else if(Math.abs(py - Y1) < 80){
                mcan.drawColor(black);
                Y1 = py;
                lenght = X2 - X1;
                breath = Y2 - Y1;
                invalidate();
            }
            else if(Math.abs(py - Y2) < 80){
                mcan.drawColor(black);
                Y2 = py;
                lenght = X2 - X1;
                breath = Y2 - Y1;
                invalidate();
            }
            else if(Math.abs(centerX - px) < 100 && Math.abs(centerY - py) < 100){
                mcan.drawColor(black);
                centerY = py;
                centerX = px;
                X1 = centerX - lenght / 2;
                Y1 = centerY - breath / 2;
                X2 = centerX + lenght / 2;
                Y2 = centerY + breath / 2;
                invalidate();
            }
        }
        return true;
    }
}

