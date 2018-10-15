package de.joshuarosenberger.suffkischte;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;

public class CreateGame extends AppCompatActivity {
    private ArrayList<EditText> playerFields;
    private ArrayList<String> playerNames;
    public static final String PLAYER_NAMES = "de.joshuarosenberger.suff_kischte.PLAYER_NAMES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_game);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        playerFields = new ArrayList<EditText>();
        playerNames = new ArrayList<String>();

        final Intent i = new Intent(this, Game.class);
        final Context context = getApplicationContext();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_start_game);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPlayerNameList();

                if (playerNames.size() >= 3) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(PLAYER_NAMES, playerNames);
                    i.putExtra("nameBundle", bundle);
                    startActivity(i);
                } else {
                    displayToast("Nicht genügend Spieler! (min 3)");
                }
            }
        });

        addPlayerAddField();
        addPlayerAddField();
        addPlayerAddField();
        addPlayerAddField();
    }

    private void addPlayerAddField() {
        final LinearLayout playerListLayout = (LinearLayout) findViewById(R.id.player_menue);
        final LinearLayout playerField = new LinearLayout(this);
        playerField.setOrientation(LinearLayout.HORIZONTAL);
        playerField.setPadding(100,0,0,0);

        final EditText nameField = new EditText(this);
        nameField.setHint("Name");
        nameField.setMinimumWidth(600);
        nameField.setTextColor(getResources().getColor(R.color.colorText));
        nameField.setHintTextColor(getResources().getColor(R.color.colorTextHint));
        nameField.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
        playerFields.add(nameField);
        playerField.addView(nameField);

        ImageButton removeBtn = new ImageButton(this);
        Drawable delete = getResources().getDrawable(android.R.drawable.ic_menu_delete);
        delete.setBounds(0, 0, 90, 90);
        removeBtn.setImageDrawable(delete);
        removeBtn.setBackgroundResource(0);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerFields.remove(nameField);
                playerListLayout.removeView(playerField);
            }
        });
        playerField.addView(removeBtn);

        playerListLayout.addView(playerField);
    }

    public void onAddPlayerClicked(View view) {
        addPlayerAddField();
    }

    private void createPlayerNameList() {
        playerNames = new ArrayList<String>();
        for (EditText t:
                playerFields) {
            if (t.getText().length() > 0) {
                System.out.println(t.getText().toString());
                playerNames.add(t.getText().toString());
            }
        }
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
