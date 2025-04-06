package com.main.v12t1;

public abstract class Monster {
    private String name;
    private int life;
    private int maxLife;

    public Monster(int maxLife, String name) {
        this.maxLife = maxLife;
        this.life = maxLife;
        this.name = name;
    }

    public void takeDamage(int damage) {
        life = Math.max(0, life - damage);
    }

    public int getLife() {
        return life;
    }

    public String getName() {
        return name;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setLife(int life) {
        this.life = life;
    }
}