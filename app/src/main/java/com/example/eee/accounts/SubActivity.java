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


        /*Button btn = (Button)findViewById(R.id.cal_button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // オブジェクトを取得
                EditText shish = (EditText) findViewById(R.id.shishutu);
                EditText shuny = (EditText) findViewById(R.id.shunyu);
                EditText zanda = (EditText) findViewById(R.id.zandaka);
                //入力された値を取得
                Editable getText = shish.getText();
                int int_shish = Integer.parseInt(getText.toString());
                getText = shuny.getText();
                int int_shuny = Integer.parseInt(getText.toString());
                getText = zanda.getText();
                int int_zanda = Integer.parseInt(getText.toString());
                // 残高計算
                int_zanda = int_zanda + int_shuny;
                int_zanda = int_zanda - int_shish;
                zanda.setText(Integer.toString(int_zanda));
                shuny.setText(Integer.toString(int_shuny));
                shish.setText(Integer.toString(int_shish));
            }
        });*/

        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        //add 4/4-s
        final EditText nameText = (EditText) findViewById(R.id.editName);
        final EditText ageText = (EditText) findViewById(R.id.editAge);
        //add 4/4-e

        Button detaBaseButton = (Button) findViewById(R.id.dataBase);

        detaBaseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent dbIntent = new Intent(SubActivity.this,ShowDataBase.class);
                startActivity(dbIntent);
            }
        });


        //add 4/4-s
        Button entryButton = (Button) findViewById(R.id.insert);
        entryButton.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();

                ContentValues insertValues = new ContentValues();
                insertValues.put("name", name);
                insertValues.put("age", age);
                long id = db.insert("person", name, insertValues);
            }

        });

        Button updateButton = (Button) findViewById(R.id.update);
        updateButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(SubActivity.this, "名前を入力してください。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues updateValues = new ContentValues();
                    updateValues.put("age", age);
                    db.update("person", updateValues, "name=?", new String[] { name });
                }
            }
        });


        Button deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(SubActivity.this, "名前を入力してください。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    db.delete("person", "name=?", new String[] { name });
                }
            }
        });


        Button deleteAllButton = (Button) findViewById(R.id.deleteAll);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                String name = nameText.getText().toString();
                String age = ageText.getText().toString();
                db.delete("person", null, null);
            }

        });





        //add 4/4-e

        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
