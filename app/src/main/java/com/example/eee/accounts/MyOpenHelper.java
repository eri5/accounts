package com.example.eee.accounts;

/**
 * Created by eee on 2018/04/04.
 */

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;


public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {

        super(context, "AccountDB", null, 1);

    }



    @Override

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table account(" + "item text not null, " + "price text"

                + ");");

    }



    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}



