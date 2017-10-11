package com.zxh.rxjava2demo;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView ttt;
    private TimeButton tb;
    int aaa = 1;
    private long curtime = 0l;
    private long lastTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aaa++;
                ttt.setText("" + aaa);
            }
        });
        ttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curtime = System.currentTimeMillis();
                long a = curtime - lastTime;
                if (curtime - lastTime >= 5000) {
                    lastTime = System.currentTimeMillis();
                    aaa++;
                    ttt.setText(a + "    " + aaa);
                }
            }
        });
    }

    private void initView() {
        ttt = (TextView) findViewById(R.id.ttt);
        tb = (TimeButton) findViewById(R.id.tb);
    }
}
