package com.example.administrator.recordplayground;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecordView recordView;
    private Button btn;
    private TimerTask timerTask;
    private Timer timer;
    private TextView txtTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recordView= (RecordView) findViewById(R.id.test);
        btn= (Button) findViewById(R.id.btn);
        txtTimer= (TextView) findViewById(R.id.time);
        btn.setOnClickListener(this);
        for(int i=0;i<10;++i){
            recordView.setSecend(i);
        }
        timer=new Timer(true);

    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            recordView.setSecend(msg.what);
            Log.e("计时",msg.what+" ");
            if (msg.what%40==0) {
                txtTimer.setText(msg.what/40 + "秒");
            }
        }
    };

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn:
//                for (int i = 5; i < 1000; ++i) {
//                    final int finalI = i;
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////                            mHandler.sendEmptyMessage(finalI);
////                        }
////                    }).start();
//
//                }
                timer.schedule(task,0, 25);
                break;
            default:
                break;
        }
    }

    TimerTask task = new TimerTask() {
        int secend=0;
        public void run() {
            Message message = new Message();
            message.what = secend++;
            mHandler.sendMessage(message);
        }
    };
}
