package com.example.okuda0715.mydbconnection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mydbconnection.db";
    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE =
            "create table " + UserContract.User.TABLE_NAME + "(" +
                    UserContract.User._ID + " integer primary key autoincrement, " +
                    UserContract.User.COL_NAME + " text, " +
                    UserContract.User.COL_SCORE + " integer)";

    private static final String INIT_TABLE =
            "insert into " + UserContract.User.TABLE_NAME +
                    " (" + UserContract.User.COL_NAME + ", " +
                    UserContract.User.COL_SCORE + ") values " +
                    "('okuda1', 100)," +
                    "('okuda1', 90)," +
                    "('okuda1', 80)," +
                    "('okuda5', 70)," +
                    "('okuda5', 30);";

    private static final String DROP_TABLE =
            "drop table if exists " + UserContract.User.TABLE_NAME;

    MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(INIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
