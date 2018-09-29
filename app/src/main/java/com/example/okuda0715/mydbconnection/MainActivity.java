package com.example.okuda0715.mydbconnection;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowId;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHelper myDBHelper = new MyDBHelper(this);

        SQLiteDatabase db = myDBHelper.getWritableDatabase();

        Cursor cursor = null;

        // 単純な全件selectするサンプル
        cursor = db.query(
                false, // trueを指定すると検索結果から重複する行を削除します。
                UserContract.User.TABLE_NAME, // テーブル名
                null, // 取得する列名（String型の配列で指定する。）
                null, // where句の実際の値に「?」を設定した値（String型で指定する。）
                null, // where句のプレースフォルダに設定する値（String型の配列で指定する。）
                null, // group byする基準項目
                null,
                null,
                null
        );

        // group by してmaxを取得するサンプル
//        cursor = db.query(
//                false, // trueを指定すると検索結果から重複する行を削除します。
//                UserContract.User.TABLE_NAME, // テーブル名
//                new String[]{
//                        UserContract.User._ID,
//                        UserContract.User.COL_NAME,
//                        "max(" + UserContract.User.COL_SCORE + ") as score"},//asで別名をつけておかないと
//                // 後でcursorで取得したのデータを操作する時に
//                // [cursor.getColumnIndex(別名)]で取得できない。
//                null, // where句の実際の値以外の部分
//                null, // where句の実際の値
//                UserContract.User.COL_NAME, // group byする基準項目
//                null,
//                null,
//                null
//        );

        // where句を指定するサンプル
//        cursor = db.query(
//                false, // trueを指定すると検索結果から重複する行を削除します。
//                UserContract.User.TABLE_NAME, // テーブル名
//                new String[]{UserContract.User.COL_NAME + ", max(" + UserContract.User.COL_SCORE + ")"},
//                UserContract.User.COL_SCORE + " > ?",// where句の実際の値以外の部分
//                new String[]{"50"}, // SCORE > 50 // where句の実際の値
//                null, // group by
//                null,
//                null,
//                null
//        );

        Log.v("MyDBConnection", "record count : " + cursor.getCount());
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(UserContract.User._ID));
            String name = cursor.getString(cursor.getColumnIndex(UserContract.User.COL_NAME));
            int score = cursor.getInt(cursor.getColumnIndex(UserContract.User.COL_SCORE));
            Log.v("MyDBConnection", "name : " + name + ", score : " + score);
        }

        cursor.close();

        db.close();
    }
}
