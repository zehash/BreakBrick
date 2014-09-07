package com.unimelb.breakbrick;

import java.util.ListIterator;

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
		dx = 6;
		dy = 6;
		setX(screenWidth/2);
		setY(2 * screenHeight/3);
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
		double ballX = x + ballRadius;
		double ballY = y - ballRadius;
		if (ballY > WorldView.blocksList.get(WorldView.blocksList.size() - 1).getBounds().bottom) {
			return;
		}
		
		for (int i = 0; i < WorldView.blocksList.size(); i++) {
			Block curr = WorldView.blocksList.get(i);
			Rect temp = curr.getBounds();
			if (ballX >= temp.left && ballX <= temp.right && ballY <= temp.bottom) {
		    	curr.setPaintColor();
		    	curr.drawBlock(canvas);
		    	WorldView.blocksList.remove(curr);
		    	dy = 6;
		    	break;
		    } else {
		    	continue;
		    }
		}
		/*ListIterator<Block> list = WorldView.blocksList.listIterator(WorldView.blocksList.size() - 1);
		System.out.println("list.size() is"+ list.)
		while (list.hasPrevious()) {
		    Block prev = list.previous();
		    Rect temp = prev.getBounds();
		    if (ballX >= temp.left && ballX <= temp.right && ballY <= temp.bottom) {
		    	prev.setPaintColor();
		    	prev.drawBlock(canvas);
		    	WorldView.blocksList.remove(prev);
		    	dy = 6;
		    	break;
		    } else {
		    	continue;
		    }
		}*/
	
	}
	
	private void detectPaddleCollision() {
		double ballX = x + ballRadius;
		double ballY = y + ballRadius;
		double paddleXl = Paddle.getX() - (Paddle.width /2);
		double paddleYl = Paddle.getY() - (Paddle.height / 2);
		double paddleXr = Paddle.getX() + (Paddle.width / 2);
		double paddleYr = Paddle.getY() + (Paddle.height / 2);
		
		if (ballX > paddleXl && ballX < paddleXr && ballY > paddleYl && ballY < paddleYr ) {
			dy = -6;
		}
	}
	private void updatePhysics(Canvas canvas) {
		detectBlockCollision(canvas);
		detectPaddleCollision();
		if (x + ballRadius >= screenWidth) {
			dx = -6;
		} else if (x - ballRadius <= 0) {
			dx = 6;
		}
		
		if (y + ballRadius >= screenHeight) {
			dy = -6;
		} else if (y - ballRadius <= 0) {
			dy = 6;
		}
	}

}
