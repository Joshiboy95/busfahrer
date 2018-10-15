package de.joshuarosenberger.suffkischte;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent main = new Intent( splashscreen.this, CreateGame.class);
                startActivity(main);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
