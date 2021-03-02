package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.widget.SeekBar;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

/*
 * @author Rachel Madison
 * draws Face
 * */
public class Face extends SurfaceView  {
    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public int hairStyle;

    public boolean eyeChecked;
    public boolean hairChecked;
    public boolean skinChecked;

    public int hairBlue;
    public int hairGreen;
    public int hairRed;

    public int skinBlue;
    public int skinGreen;
    public int skinRed;

    public int eyeBlue;
    public int eyeGreen;
    public int eyeRed;

    float width;
    float height;
    float cxHead;
    float cyHead;
    float leftHead;
    float rightHead;
    float topHead;
    float bottomHead;
    float headWidth;


    //Paints
    Paint skin = new Paint();
    Paint white = new Paint();
    Paint iris = new Paint();
    Paint hair = new Paint();
    Paint black = new Paint();



    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        setBackgroundColor(Color.WHITE);
        randomize();
        white.setColor(Color.WHITE);
        black.setColor(Color.BLACK);
    }

    public void eyeSetRGB(int color) {
        eyeBlue = Color.blue(color);
        eyeRed = Color.red(color);
        eyeGreen = Color.green(color);
    }

    public void skinSetRGB(int color) {
        skinBlue = Color.blue(color);
        skinRed = Color.red(color);
        skinGreen = Color.green(color);
    }

    public void hairSetRGB(int color) {
        hairBlue = Color.blue(color);
        hairRed = Color.red(color);
        hairGreen = Color.green(color);
    }


    public void drawHair(Canvas canvas, Paint paint) {
        switch(hairStyle) {
            case 0:
                canvas.drawArc(leftHead,topHead,rightHead,bottomHead, 210, 120, false, hair);
                canvas.drawArc(leftHead,topHead,rightHead,bottomHead, 180, 60, false, hair);
                canvas.drawArc(leftHead,topHead,rightHead,bottomHead, -60, 60, false, hair);
                break;

            case 1:
                canvas.drawArc(leftHead,topHead,rightHead,bottomHead, 210, 120, false, hair);
                canvas.drawArc(leftHead,topHead,rightHead,bottomHead, 150, 90, false, hair);
                canvas.drawArc(leftHead,topHead,rightHead,bottomHead, -60, 90, false, hair);
                break;

            case 2:
                canvas.drawArc(leftHead,topHead,rightHead,bottomHead, 210, 120, false, hair);
                canvas.drawArc(leftHead,topHead,rightHead,bottomHead, 135, 90, false, hair);
                canvas.drawArc(leftHead,topHead,rightHead,bottomHead, -45, 90, false, hair);
                break;
        }
    }

    public void drawEye(Canvas canvas, float cx, float cy, Paint irisColor) {
        canvas.drawCircle(cx,cy,55, white); //white of the eye
        canvas.drawCircle(cx,cy,30, iris);  //iris
        canvas.drawCircle(cx,cy,20,black); //pupil
    }

    //randomizes the colors and hairstyle
    public void randomize(){
        hairStyle = (int)(Math.random()*3);
        skinColor = Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
        eyeColor = Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
        hairColor = Color.rgb((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
    }

    public void onDraw(Canvas canvas) {
        //initializing variables for drawing face
        width = getWidth();
        height = getHeight();
        cxHead = width/2;
        cyHead = height/2;
        leftHead = cxHead - 200;
        rightHead = cxHead + 200;
        topHead = cyHead - 300;
        bottomHead = cyHead + 300;
        headWidth = rightHead - leftHead;

        hair.setColor(hairColor);
        iris.setColor(eyeColor);
        skin.setColor(skinColor);

        //draw head
        canvas.drawOval(leftHead, topHead, rightHead, bottomHead, skin);

        //draw both eyes
        drawEye(canvas, leftHead + (headWidth/3), cyHead - 60, iris);
        drawEye(canvas, leftHead + 2*(headWidth/3), cyHead - 60, iris);

        //draws the hairstyle selected
        drawHair(canvas, hair);
    }



}
