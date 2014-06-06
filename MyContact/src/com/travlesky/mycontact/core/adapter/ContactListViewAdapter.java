/*
 * 文件描述:
 * author：zbtu
 * date：2012-11-1
 */
package com.travlesky.mycontact.core.adapter;

import java.util.Arrays;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.travlesky.mycontact.R;
import com.travlesky.mycontact.core.comparator.SpellComparator;
import com.travlesky.mycontact.core.spell.SpellUtil;

/**
 ************************************************* 
 * ContactListViewAdapter.java 功能描述:
 * 
 * Copyright: Copyright (c) 2012 Company: 中国民航信息网络股份有限公司
 * 
 * @author 涂宗彬
 * @version 1.0
 * @see 2012-11-1 创建文件
 ************************************************* 
 */
public class ContactListViewAdapter extends BaseAdapter implements
		SectionIndexer {
	private LayoutInflater mLayoutInflater;
	private String[] mNames;

	/**
	 * 
	 */
	public ContactListViewAdapter(LayoutInflater layoutInflater, String[] names) {
		super();
		mLayoutInflater = layoutInflater;
		mNames = names;
		// sort the names
		new SortThread().run();
	}
	/**
	 * 排序
	 *************************************************
	 * ContactListViewAdapter.java
	 * 功能描述: 
	 *                                 
	 * Copyright: Copyright (c) 2012  
	 * Company: 中国民航信息网络股份有限公司
	 * @author  涂宗彬
	 * @version 1.0
	 * @see                                              
	 * 2012-11-2 创建文件                             
	 *************************************************
	 */
	class SortThread implements Runnable {

		
		public void run() {
			Arrays.sort(mNames, new SpellComparator());

		}

	}

	
	public int getCount() {
		return mNames.length;
	}

	
	public Object getItem(int position) {
		return mNames[position];
	}

	
	public long getItemId(int position) {
		return position;
	}

	
	public View getView(int position, View convertView, ViewGroup parent) {
		//获得所有姓名
		final String name = mNames[position];
		ViewHolder viewHolder = null;
		
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(
					R.layout.contact_listview_item, null);
			viewHolder = new ViewHolder();
			viewHolder.catalogTextView = (TextView) convertView
					.findViewById(R.id.contactitem_catalog);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(R.id.contactitem_avatar_iv);
			viewHolder.nameTextView = (TextView) convertView
					.findViewById(R.id.contactitem_nick);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		//获取首字母
		mSectionName = name;
		new ConverterToFirstSpellThread().run();
		// String catalog = SpellUtil.converterToFirstSpell(name);
		final String catalog = mFirstSpell;
		
		if (position == 0) {
			viewHolder.catalogTextView.setVisibility(View.VISIBLE);
			viewHolder.catalogTextView.setText(catalog);
		} else {
			
			mSectionName = mNames[position - 1];
			new ConverterToFirstSpellThread().run();
			/*
			 * String lastCatalog = SpellUtil
			 * .converterToFirstSpell(mNames[position - 1]);
			 */
			
			if (catalog.equals(mFirstSpell)) {
				viewHolder.catalogTextView.setVisibility(View.GONE);
			} else {
				viewHolder.catalogTextView.setVisibility(View.VISIBLE);
				viewHolder.catalogTextView.setText(catalog);
			}
		}

		viewHolder.imageView.setImageResource(R.drawable.contact_list_icon);
		viewHolder.nameTextView.setText(name);
		return convertView;
	}

	static class ViewHolder {
		TextView catalogTextView;// 目录
		ImageView imageView;// 头像
		TextView nameTextView;// name
	}

	String mSectionName;

	String mFirstSpell;

	
	public int getPositionForSection(int section) {
		for (int i = 0; i < mNames.length; i++) {
			mSectionName = mNames[i];
			new ConverterToFirstSpellThread().run();
			// 取拼音的首字母并大写
			final char firstChar = mFirstSpell.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return -1;
	}

	class ConverterToFirstSpellThread implements Runnable {

		
		public void run() {
			mFirstSpell = SpellUtil.converterToFirstSpell(mSectionName);
		}

	}

	
	public int getSectionForPosition(int position) {
		return 0;
	}

	
	public Object[] getSections() {
		return null;
	}

}
