package com.example.xiyougame;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SuggestionDBHelper extends SQLiteOpenHelper{

    private static final String DBNAME = "url.db";// 数据库名
    
    public SuggestionDBHelper(Context context){
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS history" +  
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, url TEXT, title TEXT)");//创建history表，包含id，url和title字段
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}