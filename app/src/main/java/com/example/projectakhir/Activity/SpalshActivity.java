package com.example.projectakhir.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectakhir.R;

public class SpalshActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView persentase;
    private Thread thread;
    private Handler handler;

    private int Value = 0;
    private int times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        double getTimes = Double.parseDouble(sharedPreferences.getString("interval_time","0.1"))*1000;
        times = (int) getTimes;

        progressBar = findViewById(R.id.progress);
        persentase = findViewById(R.id.persentase);

        progressBar.setProgress(0);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // Menampung semua data yang ingin diproses oleh thread
                persentase.setText(String.valueOf(Value)+"%");
                if(Value == progressBar.getMax()){
                    //intent to Main Activity
                    startActivity(new Intent(SpalshActivity.this, MainActivity.class));
                    finish();
                }
                Value++;
            }
        };

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int w=0; w<=progressBar.getMax(); w++){
                        progressBar.setProgress(w); // Memasukan Value pada ProgressBar

                        // Mengirim pesan dari handler, untuk diproses didalam thread
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(times); // Waktu Pending
                    }
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void onBackPressed() {
        thread.interrupt();
        finish();
    }
}
