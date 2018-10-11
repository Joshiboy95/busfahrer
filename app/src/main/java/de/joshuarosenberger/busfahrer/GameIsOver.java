package de.joshuarosenberger.busfahrer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GameIsOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_is_over);
    }

    public void onNewGameBtnClicked(View view) {
        final Intent mainpage = new Intent(this, GameSettings.class);
        startActivity(mainpage);
    }
}




