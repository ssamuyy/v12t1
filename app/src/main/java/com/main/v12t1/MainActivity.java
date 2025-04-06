package com.main.v12t1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fightMonsterButton = findViewById(R.id.FightMonsterActivityButton);
        scoreText = findViewById(R.id.PlayerScoreText);

        fightMonsterButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FightMonstersActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateScore();
    }

    private void updateScore() {
        int score = GameManager.getInstance().getPlayer().getScore();
        scoreText.setText("Pisteet: " + score);
    }
}