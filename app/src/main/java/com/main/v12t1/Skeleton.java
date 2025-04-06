package com.main.v12t1;

import java.util.Random;

public class Skeleton extends Monster {
    private static String[] names = {"Luukas", "Kallo", "Luuranko"};

    public Skeleton() {
        super(30 + new Random().nextInt(20), names[new Random().nextInt(names.length)]);
    }
}