package com.unimelb.breakbrick;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Paddle {

	private static double centerX;
    private static double centerY;
    private Rect rect;
    private Paint paint;
    public static int width;
    public static int height;
    
    public Paddle(int posX, int posY, int width, int height) {
    	Paddle.width = width;
    	Paddle.height = height;
        rect = new Rect(posX, posY, posX + width, posY + height);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        centerX = getRect().centerX();
        centerY = getRect().centerY();
    }
    
    public static double getX() {
        return centerX;
    }

    public static double getY() {
        return centerY;
    }

    public Rect getRect() {
        return rect;
    }

    public Paint getPaint() {
        return paint;
    }
    
    public void move(double position) {
        double dx = position - getX();
        move(dx, 0);
    }
    
    public void move(double dx, double dy) {
        centerX += dx;
        centerY += dy;
        // Correctly round the position of the entity
        int moveX = (int) (centerX - getRect().centerX());
        int moveY = (int) (centerY - getRect().centerY());
        getRect().offset(moveX, moveY);
    }
}