package com.example.EnumExampleProject;

public class EnumExample {

    public enum Season {
        WINTER, SPRING, SUMMER, FALL
    }

    public static void main(String[] args) {
        Season season = Season.SUMMER;

        switch (season) {
            case WINTER -> System.out.println("It's cold outside!");
            case SPRING -> System.out.println("Flowers are blooming.");
            case SUMMER -> System.out.println("Time for the beach!");
            case FALL -> System.out.println("Leaves are falling.");
        }
    }
}
