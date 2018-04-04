package com.example.eee.accounts;

/**
 * Created by eee on 2018/03/18.
 */
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

//Tab1
public class SubActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //layout ファイルを指定
        setContentView(R.layout.activity_sub);
        // 現在のintentを取得する
        Intent intent = getIntent();
        // intentから指定キーの文字列を取得する
        //String key = intent.getStringExtra( "KEY" );
        int array[] = new int[3];
        array = intent.getIntArrayExtra("KEY");
        //選択された日付を表示
        TextView textView = new TextView(this);
        textView = (TextView) findViewById(R.id.selectedDate);
        textView.setText(String.valueOf(array[0])+ "年"+  String.valueOf(array[1])+"月"+ String.valueOf(array[2])+"日");


        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        //商品名テキスト
        final EditText itemText = (EditText) findViewById(R.id.editItem);
        //値段テキスト
        final EditText priceText = (EditText) findViewById(R.id.editPrice);


        //DB を見るボタン
        Button detaBaseButton = (Button) findViewById(R.id.dataBase);
        detaBaseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent dbIntent = new Intent(SubActivity.this,ShowDataBase.class);
                startActivity(dbIntent);
            }
        });


        //登録ボタン
        Button entryButton = (Button) findViewById(R.id.insert);
        entryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String item = itemText.getText().toString();
                String price = priceText.getText().toString();

                ContentValues insertValues = new ContentValues();
                insertValues.put("item", item);
                insertValues.put("price", price);
                long id = db.insert("account", item, insertValues);
            }

        });

        //更新ボタン
        Button updateButton = (Button) findViewById(R.id.update);
        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String item = itemText.getText().toString();
                String price = priceText.getText().toString();

                if (item.equals("")) {
                    Toast.makeText(SubActivity.this, "商品名を入力してください。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues updateValues = new ContentValues();
                    updateValues.put("price", price);
                    db.update("account", updateValues, "item=?", new String[] { item });
                }
            }
        });


        //削除ボタン
        Button deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String item = itemText.getText().toString();
                String price = priceText.getText().toString();

                if (item.equals("")) {
                    Toast.makeText(SubActivity.this, "商品名を入力してください。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    db.delete("account", "item=?", new String[] { item });
                }
            }
        });


        //すべて削除ボタン
        Button deleteAllButton = (Button) findViewById(R.id.deleteAll);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String item = itemText.getText().toString();
                String price = priceText.getText().toString();
                db.delete("account", null, null);
            }

        });


        //RETURNボタン
        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
