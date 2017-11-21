package com.example.enter_01.vfin.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.enter_01.vfin.R;


/**
 * Created by Hytexts_Android on 15/5/2560.
 */

// TODO: 18/5/2560 ใส่เปอร์เซ็น 
public class PercenView extends View {

    private int parentWidth;
    private double per = 0;
    int framesPerSecond = 100;

    long startTime;
    Matrix matrix = new Matrix();

    public PercenView(Context context) {
        super(context);
    }

    public PercenView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PercenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        parentWidth = MeasureSpec.getSize(widthMeasureSpec)-15;

        int desiredWidth = 50;
        int desiredHeight = 25;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    public void setC(String per) {
        try {
            this.per = Float.parseFloat(per) * 100;
            this.startTime = System.currentTimeMillis();
            this.postInvalidate();
            invalidate();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }


    int i = 1;
    int pixcle = 10;


    Path path = new Path();       // your path
    Paint paint = new Paint();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundResource(R.drawable.button_background_gray_c);
        int sizePercen = (int) ((parentWidth * per) / 100);
        String color = "#F37D7D";
     /*   if (per <= 25) {
            color = "#e03834";
        } else if (per <= 50) {
            color = "#ebaa00";
        } else if (per <= 75) {
            color = "#289c50";
        }*/

        if (per == 0 ) {
            path.moveTo(13, 13);
            path.lineTo(0, 13);
            canvas.drawPath(path, paint);
        } else if (i <= sizePercen) {
            i = i + pixcle;
            paint.setColor(Color.parseColor(color));
            paint.setStrokeWidth(20);
            paint.setStyle(Paint.Style.STROKE);       // set to STOKE
            paint.setDither(true);                    // set the dither to true
            paint.setStyle(Paint.Style.STROKE);       // set to STOKE
            paint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
            paint.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
            paint.setPathEffect(new CornerPathEffect(150));   // set the path effect when they
            // join.
            paint.setAntiAlias(true);

            path.moveTo(13, 13);
            path.lineTo(i, 13);
            canvas.drawPath(path, paint);

         /*   paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setTextSize(1f);*/
            // canvas.drawTextOnPath("oam", path, i, 0, paint);

         //   Log.d("AdapterNessAnalysis",i+"");
            if (per > 0) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                matrix.postTranslate(pixcle * elapsedTime / 1000, 0); // move 100 pixels to the right
                canvas.concat(matrix);
                this.postInvalidateDelayed(1000 / framesPerSecond);
            }
        } else {
            paint.setColor(Color.parseColor(color));
            paint.setStrokeWidth(20);
            paint.setDither(true);                    // set the dither to true
            paint.setStyle(Paint.Style.STROKE);       // set to STOKE
            paint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
            paint.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
            paint.setPathEffect(new CornerPathEffect(100));   // set the path effect when they join.
            paint.setAntiAlias(true);

            path.moveTo(13, 13);
            path.lineTo(sizePercen, 13);

            canvas.drawPath(path, paint);

       /*     paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            paint.setTextSize(1f);*/
            //  canvas.drawTextOnPath("oam", path, sizePercen, 0, paint);

        }


    }


}
