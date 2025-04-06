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
import com.main.v12t1.GameManager;
import com.main.v12t1.R;
import com.main.v12t1.Monster;

public class BossFightFragment extends Fragment {

    private TextView bossNameText;
    private ImageView bossImage;
    private static final String TAG = "BossFightFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boss_fight, container, false);
        bossNameText = view.findViewById(R.id.BossText);
        bossImage = view.findViewById(R.id.BossImage);
        ImageButton attackBossButton = view.findViewById(R.id.AttackBossButton);

        Log.d(TAG, "BossFightFragment created.");

        Monster boss = new Monster(150, "Robotti") {
            @Override
            public void takeDamage(int damage) {
                int newLife = Math.max(0, getLife() - damage);
                setLife(newLife);
            }
        };
        updateBossInfo(boss);

        attackBossButton.setOnClickListener(v -> attackBoss(boss));

        return view;
    }

    private void updateBossInfo(Monster boss) {
        if (boss != null) {
            bossNameText.setText(boss.getName() + ": " + boss.getLife() + "/" + boss.getMaxLife());
            bossImage.setImageResource(R.drawable.boss_image);
        }
    }

    private void attackBoss(Monster boss) {
        if (boss != null) {
            GameManager.getInstance().getPlayer().attack(boss);
            updateBossInfo(boss);
            if (boss.getLife() <= 0) {
                Log.d(TAG, "Boss defeated!");
                bossNameText.setText("Voitit päävihollisen!");
            }
        }
    }
}