package com.example.xiyougame;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//TODO 跟踪浏览器历史记录
		
		//ContentResolver contentResolver = getContentResolver();
		 // Cursor cursor = contentResolver.query(Uri.parse("content://browser/bookmarks"),new String[]{"url"}, null, null, null);
		 // while (cursor != null && cursor.moveToNext()) 
		//  {   
		//  string = cursor.getString(cursor.getColumnIndex("url"));
		   
		  //}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
