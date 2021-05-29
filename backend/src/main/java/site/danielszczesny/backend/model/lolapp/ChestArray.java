package site.danielszczesny.backend.model.lolapp;

import java.util.Arrays;

public class ChestArray {

    private int[] chestArray;

    public ChestArray() {
        this.chestArray = new int[Champions.values().length];
    }

    @Override
    public String toString() {
        return Arrays.toString(chestArray);
    }
}
