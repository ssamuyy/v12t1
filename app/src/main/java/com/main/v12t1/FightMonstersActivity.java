package com.main.v12t1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.main.v12t1.fragments.ShowMonsterFragment;
import com.main.v12t1.fragments.BossFightFragment;

public class FightMonstersActivity extends AppCompatActivity {

    private static Button bossFightButton;

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Fragment fragment = null;
            if (view.getId() == R.id.ShowMonsterFragmentButton) {
                Monster monster = GameManager.getInstance().generateMonster();
                Bundle data = new Bundle();
                data.putString("monsterName", monster.getName());
                data.putInt("monsterLife", monster.getLife());
                data.putInt("monsterMaxLife", monster.getMaxLife());
                fragment = new ShowMonsterFragment();
                fragment.setArguments(data);
            } else if (view.getId() == R.id.BossFightFragmentButton) {
                fragment = new BossFightFragment();
            } else if (view.getId() == R.id.ReturnFromFightButton) {
                finish();
                return;
            }

            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FightMonstersFrame, fragment)
                        .commit();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_monsters);

        Button showMonsterButton = findViewById(R.id.ShowMonsterFragmentButton);
        bossFightButton = findViewById(R.id.BossFightFragmentButton);
        Button returnButton = findViewById(R.id.ReturnFromFightButton);

        bossFightButton.setEnabled(false);

        showMonsterButton.setOnClickListener(listener);
        bossFightButton.setOnClickListener(listener);
        returnButton.setOnClickListener(listener);

        updateBossFightButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBossFightButton();
    }

    public static void updateBossFightButton() {
        int score = GameManager.getInstance().getPlayer().getScore();
        if (score >= 100) {
            bossFightButton.setEnabled(true);
        } else {
            bossFightButton.setEnabled(false);
        }
    }
}