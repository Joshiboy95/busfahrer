package de.joshuarosenberger.suffkischte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

public class GameIsOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game_is_over);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void onNewGameBtnClicked(View view) {
        final Intent mainpage = new Intent(this, CreateGame.class);
        startActivity(mainpage);
    }
}




