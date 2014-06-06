package com.travlesky.mycontact;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.travlesky.mycontact.core.adapter.ContactListViewAdapter;
import com.travlesky.mycontact.core.view.OverlayView;
import com.travlesky.mycontact.core.view.SideBarView;

public class MainActivity extends Activity {

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		ListView contactListView = (ListView) findViewById(R.id.contactlistView);
		SideBarView letterSideBarView = (SideBarView) findViewById(R.id.letterSideBarView);
		
		final LayoutInflater layoutInflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		
		contactListView.setAdapter(new ContactListViewAdapter(layoutInflater,
				NameConstants.names));
		// 初始化
		TextView overlayTextView = (TextView) OverlayView.initOverlay(
				layoutInflater,
				(WindowManager) this.getSystemService(Context.WINDOW_SERVICE));
		overlayTextView.setVisibility(View.INVISIBLE);
		
		letterSideBarView.setListView(contactListView);
		letterSideBarView.setTextView(overlayTextView);
	}

}