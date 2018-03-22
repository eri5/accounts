package com.example.eee.accounts;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.CalendarView.OnDateChangeListener;


public class MainActivity extends AppCompatActivity implements OnDateChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(this);

        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
        Intent intent = new Intent(MainActivity.this, SubActivity.class);

        int foo[] = new int[3];
        foo[0] = i;
        foo[1] = i1;
        foo[2] = i2;

        intent.putExtra("KEY", foo);
        startActivity(intent);}

}
