package com.main.v12t1;

public class Player {
    private int damage = 10;
    private int score = 0;

    public void attack(Monster monster) {
        monster.takeDamage(damage);
        if (monster.getLife() <= 0) {
            score += 10;
        }
    }

    public int getScore() {
        return score;
    }
}