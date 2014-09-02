package com.unimelb.breakbrick;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {

	private static float ballRadius = 40;
	private WorldView worldView;
	private Bitmap bmp;
	
	public double x;
	public double y;
	public double dx;
	public double dy;
	
	private float previousTime;
	
	private double xSpeed;
	private double ySpeed;
	
	private int screenWidth;
	private int screenHeight;
	
	public Ball(WorldView worldView, Bitmap bmp, int screenWidth, int screenHeight) {
		this.worldView = worldView;
		this.bmp = bmp;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		dx = 3;
		dy = 3;
		setX(screenWidth/2);
		setY(2 * screenHeight/3);
		setXSpeed(400.0 * Math.sin(Math.PI * Math.random() * 0.25));
		setYSpeed(400.0 * Math.cos(Math.PI * Math.random() * 0.25));
		
		updatePosition(x, y);	
	}

	private void updatePosition(double x2, double y2) {
			this.x = x2 + dx;
			this.y = y2 + dy;
	}

	private void setYSpeed(double i) {
		this.ySpeed = i;
	}

	private void setXSpeed(double i) {
		this.xSpeed = i;	
	}

	private void setX(int i) {
		this.x = i;		
	}

	private void setY(int i) {
		this.y = i;
	}
	
	public void onMake(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		updatePhysics();
		
		if(worldView.onScreen) {
			moveBall();
			updatePosition(x, y);
			canvas.drawCircle((float)x, (float)y, ballRadius, paint);
		}
	}

	private void moveBall() {
		x = x + dx;
		y = y + dy;
	}

	private void updatePhysics() {
		if (x + ballRadius >= screenWidth) {
			dx = -3;
		} else if (x - ballRadius <= 0) {
			dx = 3;
		}
		
		if (y + ballRadius >= screenHeight) {
			dy = -3;
		} else if (y - ballRadius <= 0) {
			dy = 3;
		}
	}

}
