package site.danielszczesny.backend;

import site.danielszczesny.backend.model.lolapp.Champion;
import site.danielszczesny.backend.model.lolapp.Champions;
import site.danielszczesny.backend.model.lolapp.ChestArray;

import java.util.Arrays;


public class Runner {
    public static void main(String[] args) {
        System.out.println(Champion.allChampions());
        ChestArray chestArray = new ChestArray();
        System.out.println(chestArray);
        String array = chestArray.toString();
        array = array.substring(1, array.length() - 1);
        String[] split = array.split(", ");
        System.out.println(Arrays.toString(split));
        System.out.println(split[5]);
    }
}
