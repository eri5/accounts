package com.example.eee.accounts;

/**
 * Created by eee on 2018/03/18.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub);

        // 現在のintentを取得する
        Intent intent = getIntent();
        // intentから指定キーの文字列を取得する
        //String key = intent.getStringExtra( "KEY" );
        int array[] = new int[3];
        array = intent.getIntArrayExtra("KEY");


        TextView textView = new TextView(this);
        textView = (TextView) findViewById(R.id.textView6);
        textView.setText(String.valueOf(array[0])+ "年"+  String.valueOf(array[1])+"月"+ String.valueOf(array[2])+"日");


        Button btn = (Button)findViewById(R.id.cal_button);
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
        });


        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
