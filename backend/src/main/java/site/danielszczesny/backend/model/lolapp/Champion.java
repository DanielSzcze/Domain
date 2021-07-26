package site.danielszczesny.backend.model.lolapp;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Champion {

    private String name;
    private int number;
    private String iconDirectory;
    private static List<Champion> championList;

    public Champion(String name, int number) {
        this.name = name;
        this.number = number;
        this.iconDirectory = "/lol_icons/" + name + ".png";
    }

    private static void fillList() {
        championList = new ArrayList<>();
        Arrays.stream(Champions.values()).forEach(champion ->championList.add(champion.getChampion()));
    }

    public static String allChampions() {
        fillList();
        return championList.toString();
    }

    public int getNumber() {
        return number;
    }

    public String getIconDirectory() {
        return iconDirectory;
    }

    @Override
    public String toString() {
        return "Champion{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", iconDirectory='" + iconDirectory + '\'' +
                '}';
    }
}
