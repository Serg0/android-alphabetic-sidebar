/*
 * 文件描述:
 * author：zbtu
 * date：2012-10-31
 */
package com.travlesky.mycontact.core.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

/**
 ************************************************* 
 * SideView.java 功能描述:
 * 
 * Copyright: Copyright (c) 2012 Company: 中国民航信息网络股份有限公司
 * 
 * @author 涂宗彬
 * @version 1.0
 * @see 2012-10-31 创建文件
 ************************************************* 
 */
public class SideBarView extends View {
	private static final char[] mLetter = new char[] { '#', 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	/**
	 * @param context
	 */
	public SideBarView(Context context) {
		super(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SideBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public SideBarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		// 画布为灰色
		canvas.drawColor(Color.GRAY);
		Paint paint = new Paint();
		//  写入文字 2012103-12123 begin
		final int textHeight = getHeight() / mLetter.length;
		final int width = getWidth();
		for (int i = 0; i < mLetter.length; i++) {
			//画笔
			paint.setColor(Color.WHITE);
			paint.setTypeface(Typeface.DEFAULT_BOLD);
			paint.setAntiAlias(true);
			paint.setTextSize(24);
			// 计算坐标
			final float xPos = width / 2
					- paint.measureText(String.valueOf(mLetter[i])) / 2;

			final float yPos = textHeight * i + textHeight;
			
			canvas.drawText(String.valueOf(mLetter[i]), xPos, yPos, paint);
			paint.reset();
		}
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		final float y = (int) event.getY();
		// 判断点击位置
		int idx = (int) (y / getHeight() * mLetter.length);
		if (idx >= mLetter.length) {
			idx = mLetter.length - 1;
		} else if (idx < 0) {
			idx = 0;
		}
		if (event.getAction() == MotionEvent.ACTION_DOWN
				|| event.getAction() == MotionEvent.ACTION_MOVE) {
			mDialogText.setVisibility(View.VISIBLE);
			mDialogText.setText(String.valueOf( mLetter[idx]));
			if (sectionIndexter == null) {
				sectionIndexter = (SectionIndexer) list.getAdapter();
			}
			int position = sectionIndexter.getPositionForSection(mLetter[idx]);
			if (position == -1) {
				return true;
			}
			list.setSelection(position);
		} else {
			mDialogText.setVisibility(View.INVISIBLE);
		}
		return true;
	}

	private ListView list;
	private SectionIndexer sectionIndexter;
	private TextView mDialogText;

	public void setListView(ListView _list) {
		list = _list;
		sectionIndexter = (SectionIndexer) _list.getAdapter();
	}

	public void setTextView(TextView mDialogText) {
		this.mDialogText = mDialogText;
	}

}
