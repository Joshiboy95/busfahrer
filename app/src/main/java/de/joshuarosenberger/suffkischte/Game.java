package de.joshuarosenberger.suffkischte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

import de.joshuarosenberger.suffkischte.framework.GameSession;
import de.joshuarosenberger.suffkischte.framework.Player;
import de.joshuarosenberger.suffkischte.framework.Question;
import de.joshuarosenberger.suffkischte.framework.QuestionGenerator;

public class Game extends AppCompatActivity {
    private GameSession game;
    private ArrayList<Player> players;
    private ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);

        //getting player names carried by the Intent
        ArrayList<String> names = getIntent().getBundleExtra("nameBundle").getStringArrayList(CreateGame.PLAYER_NAMES);

        questions = new QuestionGenerator(names.size(),1).getQuestions();
        game = new GameSession(names, questions);

        //getting first question
        TextView te = (TextView) findViewById(R.id.fullscreen_content);
        te.setText(game.nextQuestion().getText());
    }


    public void onNextBtnClicked(View view) {
        if(game.gameHasFinished()){
            final Intent gameisover = new Intent(this, GameIsOver.class);
            startActivity(gameisover);
            finish();
        }else {
            TextView te = (TextView) findViewById(R.id.fullscreen_content);
            te.setText(game.nextQuestion().getText());
        }
    }

}