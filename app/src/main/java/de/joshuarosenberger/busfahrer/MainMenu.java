package de.joshuarosenberger.busfahrer;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    private ArrayList<EditText> playerFieds;
    private ArrayList<String> playerNames;
    public static final String PLAYER_NAMES = "de.joshuarosenberger.busfahrer.PLAYER_NAMES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        playerFieds = new ArrayList<EditText>();
        playerNames = new ArrayList<String>();

        final Intent i = new Intent(this, GameActivity.class);
        final Context context = getApplicationContext();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
                    displyToast("Nicht genügend Spieler! (min 3)");
                }





            }
        });


        addPlayerAddField();
        addPlayerAddField();
        addPlayerAddField();
        addPlayerAddField();
        addPlayerAddField();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addPlayerAddField() {
        final LinearLayout playerListLayout = (LinearLayout) findViewById(R.id.playersLinearLayout);
        final LinearLayout playerField = new LinearLayout(this);
        playerField.setOrientation(LinearLayout.HORIZONTAL);
        playerField.setPadding(100,0,0,0);

        final EditText nameField = new EditText(this);
        nameField.setHint("Name");
        nameField.setMinimumWidth(600);
        nameField.setTextColor(getResources().getColor(R.color.colorText));
        nameField.setHintTextColor(getResources().getColor(R.color.colorTextHint));
        nameField.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
        playerFieds.add(nameField);
        playerField.addView(nameField);

        ImageButton removeBtn = new ImageButton(this);
        Drawable delete = getResources().getDrawable(android.R.drawable.ic_menu_delete);
        delete.setBounds(0, 0, 90, 90);
        removeBtn.setImageDrawable(delete);
        removeBtn.setBackgroundResource(0);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerFieds.remove(nameField);
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
             playerFieds) {
            if (t.getText().length() > 0) {
                System.out.println(t.getText().toString());
                playerNames.add(t.getText().toString());
            }
        }
    }

    private void displyToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
