package com.example.android.tichucountersecondproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    String pointsGame;
    String nameA;
    String nameB;
    private EditText editPointGame;
    private EditText editTeamNameA;
    private EditText editTeamNameB;
    private Button buttonStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setup();

        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pointsGame = getTextPointGame();
                nameA = getNameAGame();
                nameB = getNameBGame();

                if (pointsGame.equals("") && nameA.equals("") && nameB.equals("")) {
                    Toast.makeText(StartActivity.this, R.string.fill_text, Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(pointsGame) % 100 != 0 && Integer.parseInt(pointsGame) == 0) {
                    Toast.makeText(StartActivity.this, R.string.wrong_input, Toast.LENGTH_LONG).show();
                } else {
                    Intent intentMain = new Intent(StartActivity.this, MainActivity.class);
                    intentMain.putExtra("points", pointsGame);
                    intentMain.putExtra("nameA", nameA);
                    intentMain.putExtra("nameB", nameB);
                    startActivity(intentMain);
                }
            }
        });
    }

    private void setup() {
        editPointGame = findViewById(R.id.pointGame);
        editTeamNameA = findViewById(R.id.nameA);
        editTeamNameB = findViewById(R.id.nameB);
        buttonStartGame = findViewById(R.id.start_game);
    }

    private String getTextPointGame() {
        return editPointGame.getText().toString();
    }

    private String getNameAGame() {
        return editTeamNameA.getText().toString();
    }

    private String getNameBGame() {
        return editTeamNameB.getText().toString();
    }
}
