package com.main.v12t1;

import java.util.Random;

public class GameManager {
    private static GameManager instance;
    private Player player;
    private Monster currentMonster;

    private GameManager() {
        player = new Player();
        generateMonster();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public Monster generateMonster() {
        currentMonster = (new Random().nextBoolean()) ? new Skeleton() : new Vampire();
        if (currentMonster == null) {
            currentMonster = new Skeleton();
        }
        return currentMonster;
    }

    public Monster getLatestMonster() {
        if (currentMonster == null) {
            return generateMonster();
        }
        return currentMonster;
    }
}