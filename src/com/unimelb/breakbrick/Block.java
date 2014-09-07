package com.unimelb.breakbrick;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

public class Block extends ShapeDrawable {

	private Paint paint;
	private int blockColor;
	private Canvas canvas;
	private int blockHeight;
	private int spacing;
	private int topOffset;
	private int blockWidth;
	
	
	public Block(Canvas canvas, int i, int j) {
		super(new RectShape());
		paint = new Paint();
		blockColor = Color.CYAN;
		paint.setColor(blockColor);
		this.canvas  = canvas;
		blockHeight = canvas.getWidth() / 10;
		spacing = canvas.getWidth() / 88;
		topOffset = canvas.getHeight() / 10;
		blockWidth = (canvas.getWidth() / 5) - spacing;
		initBlock(canvas, i, j);
	}

	private void initBlock(Canvas canvas, int i, int j) {
		int y_coordinate = (i * (blockHeight + spacing)) + topOffset;
		int x_coordinate = j * (blockWidth + spacing);

		Rect r = new Rect();
		r.set(x_coordinate, y_coordinate, x_coordinate + blockWidth,
				y_coordinate + blockHeight);
		this.setBounds(r);
		WorldView.blocksList.add(this);
	}

	public void drawBlock(Canvas canvas) {
		canvas.drawRect(this.getBounds(), paint);
	}

	public void setPaintColor() {
		blockColor = Color.WHITE;
		paint.setColor(blockColor);
	}
	public int getColor() {
		return paint.getColor();
	}

	public int[] toIntArray() {
		int[] arr = { this.getBounds().left, this.getBounds().top,
				this.getBounds().right, this.getBounds().bottom, blockColor };
		return arr;
	}
}
