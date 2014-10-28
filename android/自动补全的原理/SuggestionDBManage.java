package com.example.xiyougame;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SuggestionDBManage {
    private SuggestionDBHelper mDateBaseHelper;
    private SQLiteDatabase mDatabase;

    public SuggestionDBManage(Context context) {
        mDateBaseHelper = new SuggestionDBHelper(context);
        mDatabase = mDateBaseHelper.getWritableDatabase();
    }

    public void insert(SuggestionItem urlitem) {
        mDatabase.beginTransaction(); // 开始事务
        try {
            mDatabase.execSQL("INSERT INTO history VALUES(null, ?, ?)",
                    new Object[] { urlitem.getUrl(), urlitem.getTitle() });
            mDatabase.setTransactionSuccessful(); // 设置事务成功完成
        } finally {
            mDatabase.endTransaction(); // 结束事务
        }
    }

    public List<SuggestionItem> query(String prefix) {
        String like = prefix + "%";
        List<SuggestionItem> values = new ArrayList<SuggestionItem>();
        
        final String selection = "(url LIKE ? OR url LIKE ? OR url LIKE ? OR url LIKE ?)";
        
        String[] selectionArgs = new String[4];
        selectionArgs[1] = "http://" + like;
        selectionArgs[2] = "http://www." + like;
        selectionArgs[3] = "https://" + like;
        selectionArgs[4] = "https://www." + like;
        
        Cursor cursor = mDatabase.query("history", new String[] { "url", "title" }, selection,
                selectionArgs, null, null, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            String url = cursor.getString(0);
            String title = cursor.getString(1);
            values.add(new SuggestionItem(url, title));
            cursor.moveToNext();
        }
        return values;
    }
    
    public void close(){
        mDatabase.close();
    }
}