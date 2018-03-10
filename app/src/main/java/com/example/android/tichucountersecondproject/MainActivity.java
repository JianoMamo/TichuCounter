package com.example.android.tichucountersecondproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int TICHU = 100;
    private final int GRANDE = 200;
    private final int NOTICHU = -100;
    private final int NOGRANDE = -200;
    private int scoreA = 0;
    private int scoreB = 0;
    private Button submitButton;
    private Button tichu;
    private Button tichu1;
    private Button noTichu;
    private Button noTichu1;
    private Button grande;
    private Button grande1;
    private Button noGrande;
    private Button noGrande1;
    private Button reset;
    private TextView textPointA;
    private TextView textPointB;
    private TextView teamnameA;
    private TextView teamnameB;
    private TextView showMaxPoints;
    private EditText editPointsA;
    private EditText editPointsB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        Intent intent = getIntent();
        final String maxPoints = intent.getStringExtra("points");
        String nameA = intent.getStringExtra("nameA");
        String nameB = intent.getStringExtra("nameB");

        teamnameA.setText(nameA);
        teamnameB.setText(nameB);
        showMaxPoints.setText(maxPoints);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editPointsA.getText().toString().equals("") || editPointsB.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter for both score", Toast.LENGTH_LONG).show();
                } else {

                    int pointsA = Integer.parseInt(editPointsA.getText().toString());
                    int pointsB = Integer.parseInt(editPointsB.getText().toString());

                    if (pointsA <= 400 && pointsB <= 400 && (pointsA + pointsB) % 100 == 0 && pointsA % 5 == 0 && pointsB % 5 == 0) {
                        scoreA += pointsA;
                        scoreB += pointsB;
                        textPointA.setText(String.valueOf(scoreA));
                        textPointB.setText(String.valueOf(scoreB));

                        editPointsA.getText().clear();
                        editPointsB.getText().clear();

                        if (scoreA >= Integer.parseInt(maxPoints)) {

                            AlertDialog.Builder builder;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                            } else {
                                builder = new AlertDialog.Builder(MainActivity.this);
                            }
                            builder.setTitle(R.string.messageA)
                                    .setMessage(R.string.startover)
                                    .setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                            Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
                                            startActivity(startIntent);
                                            finish();
                                        }
                                    })
                                    .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // do nothing
                                            Intent intentExit = new Intent(Intent.ACTION_MAIN);
                                            intentExit.addCategory(Intent.CATEGORY_HOME);
                                            intentExit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intentExit);
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                        if (scoreB >= Integer.parseInt(maxPoints)) {
                            Toast.makeText(MainActivity.this, "Team B won!!\n Press Reset for new game", Toast.LENGTH_LONG).show();
                            AlertDialog.Builder builder;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                            } else {
                                builder = new AlertDialog.Builder(MainActivity.this);
                            }
                            builder.setTitle(R.string.messageB)
                                    .setMessage(R.string.startover)
                                    .setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                            Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
                                            startActivity(startIntent);
                                            finish();
                                        }
                                    })
                                    .setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // do nothing
                                            Intent intentExit = new Intent(Intent.ACTION_MAIN);
                                            intentExit.addCategory(Intent.CATEGORY_HOME);
                                            intentExit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intentExit);
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "Both numbers must be multiple of 5 and 100\nYou can put till 400 points", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        grande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pointsA = editPointsA.getText().toString();
                if (pointsA.equals("")) {
                    pointsA += String.valueOf(GRANDE);
                    editPointsA.setText(pointsA);
                } else {
                    int pointsAA = Integer.parseInt(pointsA) + GRANDE;
                    editPointsA.setText(String.valueOf(pointsAA));
                }
            }
        });

        grande1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pointsB = editPointsB.getText().toString();
                if (pointsB.equals("")) {
                    pointsB += String.valueOf(GRANDE);
                    editPointsB.setText(pointsB);
                } else {
                    int pointsBB = Integer.parseInt(pointsB) + GRANDE;
                    editPointsB.setText(String.valueOf(pointsBB));
                }
            }
        });

        noGrande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pointsA = editPointsA.getText().toString();
                if (pointsA.equals("")) {
                    pointsA += String.valueOf(NOGRANDE);
                    editPointsA.setText(pointsA);
                } else {
                    int pointsAA = Integer.parseInt(pointsA) - GRANDE;
                    editPointsA.setText(String.valueOf(pointsAA));
                }
            }
        });

        noGrande1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pointsB = editPointsB.getText().toString();
                if (pointsB.equals("")) {
                    pointsB += String.valueOf(NOGRANDE);
                    editPointsB.setText(pointsB);
                } else {
                    int pointsBB = Integer.parseInt(pointsB) - GRANDE;
                    editPointsB.setText(String.valueOf(pointsBB));
                }
            }
        });

        tichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pointsA = editPointsA.getText().toString();
                if (pointsA.equals("")) {
                    pointsA += String.valueOf(TICHU);
                    editPointsA.setText(pointsA);
                } else {
                    int pointsAA = Integer.parseInt(pointsA) + TICHU;
                    editPointsA.setText(String.valueOf(pointsAA));
                }
            }
        });

        tichu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pointsB = editPointsB.getText().toString();
                if (pointsB.equals("")) {
                    pointsB += String.valueOf(TICHU);
                    editPointsB.setText(pointsB);
                } else {
                    int pointsBB = Integer.parseInt(pointsB) + TICHU;
                    editPointsB.setText(String.valueOf(pointsBB));
                }

            }
        });

        noTichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pointsA = editPointsA.getText().toString();
                if (pointsA.equals("")) {
                    pointsA += String.valueOf(NOTICHU);
                    editPointsA.setText(pointsA);
                } else {
                    int pointsAA = Integer.parseInt(pointsA) - TICHU;
                    editPointsA.setText(String.valueOf(pointsAA));
                }
            }
        });

        noTichu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pointsB = editPointsB.getText().toString();
                if (pointsB.equals("")) {
                    pointsB += String.valueOf(NOTICHU);
                    editPointsB.setText(pointsB);
                } else {
                    int pointsBB = Integer.parseInt(pointsB) - TICHU;
                    editPointsB.setText(String.valueOf(pointsBB));
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPointsA.getText().clear();
                editPointsB.getText().clear();
                textPointA.setText(String.valueOf(0));
                textPointB.setText(String.valueOf(0));
            }
        });

    }

    private void setup() {
        submitButton = findViewById(R.id.submit);
        tichu = findViewById(R.id.tichu);
        tichu1 = findViewById(R.id.tichu1);
        noTichu = findViewById(R.id.noTichu);
        noTichu1 = findViewById(R.id.noTichu1);
        grande = findViewById(R.id.Grande);
        grande1 = findViewById(R.id.Grande1);
        noGrande = findViewById(R.id.noGrande);
        noGrande1 = findViewById(R.id.noGrande1);
        reset = findViewById(R.id.reset);
        textPointA = findViewById(R.id.pointsA);
        textPointB = findViewById(R.id.pointsB);
        teamnameA = findViewById(R.id.teamAname);
        teamnameB = findViewById(R.id.teamBName);
        editPointsA = findViewById(R.id.editPointsA);
        editPointsB = findViewById(R.id.editPointsB);
        showMaxPoints = findViewById(R.id.showMaxPoints);
    }


}
