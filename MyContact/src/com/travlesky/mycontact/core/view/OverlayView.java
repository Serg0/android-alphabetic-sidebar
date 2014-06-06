/*
 * 文件描述:
 * author：zbtu
 * date：2012-11-1
 */
package com.travlesky.mycontact.core.view;

import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.TextView;

import com.travlesky.mycontact.R;

/**
 ************************************************* 
 * OverlayView.java 功能描述:
 * 
 * Copyright: Copyright (c) 2012 Company: 中国民航信息网络股份有限公司
 * 
 * @author 涂宗彬
 * @version 1.0
 * @see 2012-11-1 创建文件
 ************************************************* 
 */
public class OverlayView {
	/**
	 * 初始化汉语拼音首字母弹出提示框
	 * 
	 * @param layoutInflater
	 * @param windowManager
	 * @return
	 */
	public static View initOverlay(LayoutInflater layoutInflater,
			WindowManager windowManager) {
		TextView overlayTextView = (TextView) layoutInflater.inflate(
				R.layout.selection_overlay_textview, null);
		overlayTextView.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.TRANSLUCENT);
		windowManager.addView(overlayTextView, lp);
		return overlayTextView;
	}
}
