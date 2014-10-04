package com.unimelb.breakbrick;

import java.util.ArrayList;
import java.util.Random;

import org.lightcouch.CouchDbClientAndroid;
import org.lightcouch.CouchDbProperties;

import com.google.gson.JsonObject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class WorldView extends SurfaceView implements SurfaceHolder.Callback,
		Runnable, OnTouchListener {
	private SurfaceHolder surfaceHolder;
	private boolean running = false;
	public Ball ball;
	private Paddle paddle;
	private int width;
	private int height;
	public boolean onScreen = true;
	public static ArrayList<Block> blocksList;
	// Touch event position for the paddle
	private double lastKnownPaddlePosition;
	private boolean flag = true;
	int col;
	public static int lifeRemaining;
	public static int score;
		
	
	public WorldView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
		setFocusable(true);
		setOnTouchListener(this);
		
		CouchDbProperties properties = new CouchDbProperties()
		  .setDbName("brickbreak")
		  .setCreateDbIfNotExist(true)
		  .setProtocol("http")
		  .setHost("115.146.92.221")
		  .setPort(5984)
		  .setMaxConnections(100)
		  .setConnectionTimeout(0);

		CouchDbClientAndroid dbClient3 = new CouchDbClientAndroid(properties);
		JsonObject json = dbClient3.find(JsonObject.class, "gamelevel");
		col = json.getAsJsonObject().get("coloumn").getAsInt();
		
	}
	
	@Override
	public void run() {
		while (running) {
			Canvas canvas = null;
			try {
				canvas = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					paddle.move(lastKnownPaddlePosition);

					if (canvas != null) {
						doDraw(canvas);
					}
					ball.onMake(canvas);
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder surfaceHolder) {
		this.surfaceHolder = surfaceHolder;
		this.running = true;

		width = getWidth();
		height = getHeight();
		double paddleWidth = getWidth() / 2;
		double paddleHeight = getHeight() / 25;
		double paddleX = (width - paddleWidth) / 2;
		double paddleY = 7 * height / 8;
		paddle = new Paddle((int) paddleX, (int) paddleY, (int) paddleWidth,
				(int) paddleHeight);
		lifeRemaining = 3;
		score = 0;
		// TODO: Thread should not meddle with properties of the view.
		// Refactor...
		lastKnownPaddlePosition = paddle.getX();

		ball = new Ball(this, null, width, height);
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			lastKnownPaddlePosition = event.getX();
		}
		return true;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	protected void doDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		canvas.drawRect(paddle.getRect(), paddle.getPaint());
		drawBlocks(canvas);
	}

	private void initBlocks(Canvas canvas) {
		Random randomNumber = new Random();
		boolean special = false;
		int temp = randomNumber.nextInt(25);
		blocksList = new ArrayList<Block>();
		int k = 0, s = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				k++;
				if(k == col){
					if(s == temp) {
						special = true;
					} else {
						special = false;
					}
					Block block = new Block(canvas, i, j, special);
					k = 0;
				}
				s++;
			}
		}
		flag = false;
	}

	private void drawBlocks(Canvas canvas) {
		if (flag)
			initBlocks(canvas);
		for (int i = 0; i < blocksList.size(); i++) {
			blocksList.get(i).drawBlock(canvas);

		}
	}

}
