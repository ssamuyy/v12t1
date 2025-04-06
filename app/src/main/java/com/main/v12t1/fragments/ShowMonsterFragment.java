package com.main.v12t1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.main.v12t1.FightMonstersActivity;
import com.main.v12t1.GameManager;
import com.main.v12t1.R;
import com.main.v12t1.Monster;

public class ShowMonsterFragment extends Fragment {

    private TextView monsterNameText, monsterLifeText;
    private ImageView monsterImage;
    private static final String TAG = "ShowMonsterFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_monster, container, false);
        monsterNameText = view.findViewById(R.id.MonsterNameText);
        monsterLifeText = view.findViewById(R.id.MonsterLifeText);
        monsterImage = view.findViewById(R.id.MonsterImage);
        ImageButton attackButton = view.findViewById(R.id.AttackMonsterButton);

        Log.d(TAG, "Fragment created and views initialized.");

        try {
            Monster monster = GameManager.getInstance().getLatestMonster();
            if (monster != null) {
                Log.d(TAG, "Loaded monster: " + monster.getName());
                updateMonsterInfo(monster);
                setMonsterImage(monster);
            } else {
                Log.e(TAG, "Monster object is null.");
                monsterNameText.setText("Tuntematon hirviö");
                monsterLifeText.setText("Elämä: 0/0");
                monsterImage.setImageResource(R.drawable.unknown_monster);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error loading monster: " + e.getMessage(), e);
            monsterNameText.setText("Virhe: " + e.getMessage());
            monsterLifeText.setText("Elämä: -/-");
        }

        attackButton.setOnClickListener(v -> attackMonster());

        return view;
    }

    private void setMonsterImage(Monster monster) {
        if (monster.getName().contains("Luukas") || monster.getName().contains("Kallo") || monster.getName().contains("Luuranko")) {
            Log.d(TAG, "Setting image for Skeleton.");
            monsterImage.setImageResource(R.drawable.skeleton_image);
        } else if (monster.getName().contains("Drakula") || monster.getName().contains("Vlad") || monster.getName().contains("Nosferatu")) {
            Log.d(TAG, "Setting image for Vampire.");
            monsterImage.setImageResource(R.drawable.vampire_image);
        } else {
            Log.d(TAG, "Setting image for unknown monster.");
            monsterImage.setImageResource(R.drawable.unknown_monster);
        }
    }

    private void attackMonster() {
        Monster monster = GameManager.getInstance().getLatestMonster();
        if (monster != null) {
            Log.d(TAG, "Attacking monster: " + monster.getName());
            GameManager.getInstance().getPlayer().attack(monster);
            updateMonsterInfo(monster);
            if (monster.getLife() <= 0) {
                FightMonstersActivity.updateBossFightButton();
                Log.d(TAG, "Monster defeated. Generating a new one.");
                monster = GameManager.getInstance().generateMonster();
                updateMonsterInfo(monster);
                setMonsterImage(monster);
            }
        } else {
            Log.e(TAG, "Attempted to attack a null monster.");
        }
    }

    private void updateMonsterInfo(Monster monster) {
        if (monster != null) {
            monsterNameText.setText(monster.getName());
            monsterLifeText.setText("Elämä: " + monster.getLife() + "/" + monster.getMaxLife());
        } else {
            Log.e(TAG, "Monster is null during update.");
            monsterNameText.setText("Tuntematon hirviö");
            monsterLifeText.setText("Elämä: 0/0");
        }
    }
}
