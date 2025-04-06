package com.main.v12t1;

import java.util.Random;

public class Vampire extends Monster {
    private static String[] names = {"Drakula", "Vlad", "Nosferatu"};

    public Vampire() {
        super(50 + new Random().nextInt(30), names[new Random().nextInt(names.length)]);
    }
}
