package com.example.eee.accounts;

/**
 * Created by eee on 2018/04/04.
 */


        import android.app.Activity;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;


public class ShowDataBase extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_database);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        setContentView(layout);

        Intent intent = getIntent();
        String key2 = intent.getStringExtra( "KEY2" );

        MyOpenHelper helper = new MyOpenHelper(this);

        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM account2 WHERE date = ?;";
        Cursor c = db.rawQuery(sql, new String [] {key2});



        // queryメソッドの実行例
       // String[] columns = new String []{"item", "price", "word"};
        //Cursor c = db.query("account", null, "date = ?",
          //      new String []{"2018314"}, null, null, null);



      boolean mov = c.moveToFirst();
      int calc = 0;

        while (mov) {


            TextView textView = new TextView(this);

            textView.setText(String.format("%s : [商品名] %s : %d円", c.getString(2), c.getString(0),
                   c.getInt(1)));

            calc = calc + c.getInt(1);

            mov = c.moveToNext();
            layout.addView(textView);

        }

        Toast.makeText(ShowDataBase.this,String.valueOf(calc),
                Toast.LENGTH_SHORT).show();


        c.close();
        db.close();

    }
}

