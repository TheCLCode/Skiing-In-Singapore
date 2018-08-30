package com.clcode;

public class Main {

    public static void main(String[] args) {
        int[][] mapGrid = new int[][]{{4,8,7,3},{2,5,9,3},{6,3,2,5},{4,4,1,6}};
//        SkiMap map = new SkiMap(4,4, mapGrid);
        SkiMap map = new SkiMap();
        map.findLongestPath();
    }
}
