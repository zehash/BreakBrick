package com.unimelb.breakbrick;

import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Ball {

	private static float ballRadius = 40;
	private WorldView worldView;
	private Bitmap bmp;
	
	public double x;
	public double y;
	public double dx;
	public double dy;
	
	private int screenWidth;
	private int screenHeight;
	
	public Ball(WorldView worldView, Bitmap bmp, int screenWidth, int screenHeight) {
		this.worldView = worldView;
		this.bmp = bmp;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		switch(WorldView.mode) {
			case 0: {
				dx = 6;
				dy = 6;
				break;
			}
			case 1: {
				dx = 8;
				dy = 8;
				break;
			}
			case 2: {
				dx = 10;
				dy = 10;
			}
		}
		
		setX(Paddle.getX());
		setY(Paddle.getY() - (Paddle.height / 2) - ballRadius);
	}

	private void setX(double i) {
		this.x = i;		
	}

	private void setY(double i) {
		this.y = i;
	}
	
	public void setdx(double i) {
		this.dx = i;		
	}

	public void setdy(double i) {
		this.dy = i;
	}
	
	public void onMake(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		updatePhysics(canvas);
		
		if(worldView.onScreen) {
			moveBall();
			canvas.drawCircle((float)x, (float)y, ballRadius, paint);
		}
	}

	private void moveBall() {
		x = x + dx;
		y = y + dy;
	}

	private void detectBlockCollision(Canvas canvas) {
		boolean flag = false;
		double ballX1 = x + ballRadius;
		double ballX2 = x - ballRadius;
		double ballY1 = y - ballRadius;
		double ballY2 = y + ballRadius;
		if (worldView.blocksList.size() > 0)
			if (ballY1 > worldView.blocksList.get(worldView.blocksList.size() - 1).getBounds().bottom) {
				return;
			}
		
		for (int i = 0; i < worldView.blocksList.size(); i++) {
			Block curr = worldView.blocksList.get(i);
			Rect temp = curr.getBounds();
			flag = false;
			if ((ballX1 >= temp.left && ballX2 < temp.left) || (ballX2 <= temp.right && ballX1 > temp.right)) { // Entering from left/right
				if(ballY1 > temp.top && ballY2 < temp.bottom) {
					//hit on left/right side
					dx = dx * -1;
					flag = true;
				}
				else if(ballY2 >= temp.top && ballY1 < temp.top) {
					//hit near top edge
					dx = dx * -1;
					flag = true;
				} 
				else if(ballY1 <= temp.bottom && ballY2 > temp.bottom) {
					// hit near bottom edge
					dx = dx * -1;
					flag = true;
				}
			}
			
			if (((ballY1 <= temp.bottom && ballY2 > temp.bottom) || (ballY2 >= temp.top && ballY2 < temp.top))) { //entering from bottom
				if(ballX1 < temp.right && ballX2 > temp.left) {
					//hit on bottom/top side
					dy = dy * -1;
					flag = true;
				}
				else if (ballX1 >= temp.left && ballX2 < temp.left) {
					//hit near left edge
					dy = dy * -1;
					flag = true;
				}
				else if (ballX2 <= temp.right && ballX1 > temp.right) {
					//hit near right edge
					dy = dy * -1;
					flag = true;
				}
			}
			
			if (flag) {
				redrawBlock(canvas, curr);
			}
		}
	}
	
	private void redrawBlock(Canvas canvas, Block curr) {
		if(curr.isSpecial) {
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(Color.BLUE);
			paint.setFakeBoldText(true);
			paint.setTextSize(50);
			WorldView.lifeRemaining += 1;
			canvas.drawText("+1", screenWidth / 3, 3 * screenHeight / 5, paint);
		}
		WorldView.score += WorldView.lifeRemaining * 10;
		curr.setPaintColor();
    	curr.drawBlock(canvas);
    	worldView.removeBlocks(curr);
	}
	
	private void detectPaddleCollision() {
		double ballX = x + ballRadius;
		double ballY = y + ballRadius;
		double paddleXl = Paddle.getX() - (Paddle.width /2);
		double paddleYl = Paddle.getY() - (Paddle.height / 2);
		double paddleXr = Paddle.getX() + (Paddle.width / 2);
		double paddleYr = Paddle.getY() + (Paddle.height / 2);
		
		if (ballX > paddleXl && ballX < paddleXr && ballY > paddleYl && ballY < paddleYr ) {
			dy = dy * -1;
		}
	}
	private void updatePhysics(Canvas canvas) {
		detectBlockCollision(canvas);
		detectPaddleCollision();
		if (x + ballRadius >= screenWidth) {
			dx = dx * -1;
		} else if (x - ballRadius <= 0) {
			dx = dx * -1;
		}
		
		if (y - ballRadius <= 0) {
			dy = dy * -1;
		} else if (y - ballRadius >= screenHeight) {
			if(WorldView.lifeRemaining > 0) {
				 Date start = new Date();
				    Date end = new Date();
				    while(end.getTime() - start.getTime() <  2000){
				        end = new Date();
				    }
				WorldView.lifeRemaining--;
				setX(Paddle.getX());
				setY(Paddle.getY() - (Paddle.height / 2) - ballRadius);
			} else {
				Paint paint = new Paint();
				paint.setAntiAlias(true);
				paint.setColor(Color.BLACK);
				paint.setFakeBoldText(true);
				paint.setTextSize(80);
				canvas.drawText("Game Over", screenWidth / 3, 3 * screenHeight / 5, paint);
				canvas.drawText("Score : "+ WorldView.score, screenWidth / 3, 4 * screenHeight / 5, paint);
			}
		} 
	}

}
