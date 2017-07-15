package com.zeevox.recorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class NoticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (BuildConfig.BUILD_TYPE) {
            case "weekly":
                setContentView(R.layout.notice_weekly);
                Button buttonWeekly = findViewById(R.id.dogscreen_confirm_button_weekly);
                buttonWeekly.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Recorder_NoticeActivity", "Button click");
                        startActivity(new Intent(NoticeActivity.this, MainActivity.class));
                        finish();
                    }
                });
                break;
            case "debug":
                setContentView(R.layout.notice_debug);
                Button buttonDebug = findViewById(R.id.dogscreen_confirm_button_debug);
                buttonDebug.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Recorder_NoticeActivity", "Button click");
                        startActivity(new Intent(NoticeActivity.this, MainActivity.class));
                        finish();
                    }
                });
                break;
            case "beta":
                setContentView(R.layout.notice_beta);
                Button buttonBeta = findViewById(R.id.dogscreen_confirm_button_beta);
                buttonBeta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Recorder_NoticeActivity", "Button click");
                        startActivity(new Intent(NoticeActivity.this, MainActivity.class));
                        finish();
                    }
                });
                break;
            default:
                finish();
        }
    }
}
