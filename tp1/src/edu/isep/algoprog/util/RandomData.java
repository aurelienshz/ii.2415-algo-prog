package edu.isep.algoprog.util;

import java.util.Random;

public class RandomData {
    public static int[] generate1d(int nbVals, int min, int max) {
        int[] res = new int[nbVals];
        Random generator = new Random();
        for (int i = 0; i != nbVals; ++i) {
            // Why not min + nextInt(0, max - min) ?
            long rand = generator.nextLong();
            int value = (int) ((rand % ((long) max - min)) + min);
            res[i] = Math.abs(value);
        }
        return res;
    }
}
