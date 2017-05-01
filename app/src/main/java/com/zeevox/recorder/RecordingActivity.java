package com.zeevox.recorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;

public class RecordingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recording);

        new MaterialDialog.Builder(this)
                .title("Information")
                .content("It is not possible to record in this application build. Sorry for any inconvenience caused.")
                .positiveText("OK")
                .show();

        final ImageView stop = (ImageView) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecordingActivity.this, RecorderActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stop recording
    }
}
