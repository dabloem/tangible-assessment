package com.abnamro.assessment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RandomYearGenerator
 */
public class RandomYearGenerator {

    private static final int _1920 = 1920;

    public static List<Integer> getDates() {
        Random rand = new Random();
        List<Integer> result = new ArrayList<>();
        result.add(_1920 + rand.nextInt(100));
        result.add(_1920 + rand.nextInt(100));
        return result;
    }
}