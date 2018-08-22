package com.clcode;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SkiMap {

    private int maxLatitude;
    private int maxLongitude;
    private int[][] mapGrid;
    private List<Integer> longestPath;

    public SkiMap(){
        initializeMapGridValues();
    }

    public SkiMap(int maxLatitude, int maxLongitude, int[][] mapGrid){
        setMaxLatitude(maxLatitude);
        setMaxLongitude(maxLongitude);
        setMapGrid(mapGrid);
        initializeMapGridValues();
    }

    public void initializeMapGridValues(){
        if(getMapGrid()==null){
            try {
                URL url = new URL("file:D:\\codingdojo\\SkiingChallenge\\map.txt");
                try (Scanner scanner = new Scanner(url.openStream())) {
                    initializeDimension(scanner);
                    mapGrid = new int[getMaxLatitude()][getMaxLongitude()];

                    for (int i = 0; i < getMaxLatitude(); i++) {
                        for (int j = 0; j < getMaxLongitude(); j++) {
                            mapGrid[i][j] = Integer.parseInt(scanner.next());
                            System.out.print(mapGrid[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            for (int i = 0; i < getMaxLatitude(); i++) {
                for (int j = 0; j < getMaxLongitude(); j++) {
                    System.out.print(mapGrid[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    private void initializeDimension(Scanner s) {
        for(int i=0; i<2; i++){
            if(i==0){
                setMaxLatitude(Integer.parseInt(s.next()));
            }else{
                setMaxLongitude(Integer.parseInt(s.next()));
            }
        }
    }

    public int getMaxLatitude() {
        return maxLatitude;
    }

    public void setMaxLatitude(int maxLatitude) {
        this.maxLatitude = maxLatitude;
    }

    public int getMaxLongitude() {
        return maxLongitude;
    }

    public void setMaxLongitude(int maxLongitude) {
        this.maxLongitude = maxLongitude;
    }

    public int[][] getMapGrid() {
        return mapGrid;
    }

    public void setMapGrid(int[][] mapGrid) {
        this.mapGrid = mapGrid;
    }

    public void findLongestPath() {
        for(int latitude = 0; latitude< getMaxLatitude(); latitude++){
            for(int longitude = 0; longitude< getMaxLongitude(); longitude++){
                List<Integer> path;
                if(canMoveEast(latitude, longitude)){
                    path = new ArrayList<>();
                    path.add(mapGrid[latitude][longitude]);
                    moveEast(latitude, longitude, path);
                }
                if(canMoveSouth(latitude, longitude)){
                    path = new ArrayList<>();
                    path.add(mapGrid[latitude][longitude]);
                    moveSouth(latitude, longitude, path);
                }
                if(canMoveWest(latitude, longitude)){
                    path = new ArrayList<>();
                    path.add(mapGrid[latitude][longitude]);
                    moveWest(latitude, longitude, path);
                }
                if(canMoveNorth(latitude, longitude)){
                    path = new ArrayList<>();
                    path.add(mapGrid[latitude][longitude]);
                    moveNorth(latitude, longitude, path);
                }
            }
        }
        printLongestPath();
    }

    private void printLongestPath() {
       System.out.println(getLongestPath());
    }

    private void move(int latitude, int longitude, List<Integer> path) {
        if(canMoveEast(latitude, longitude)) {
            moveEast(latitude, longitude, path);
        } else if(canMoveSouth(latitude, longitude)) {
            moveSouth(latitude, longitude, path);
        } else if(canMoveWest(latitude, longitude)) {
            moveWest(latitude, longitude, path);
        } else if(canMoveNorth(latitude, longitude)) {
            moveNorth(latitude, longitude, path);
        } else {
            checkAndReplaceIfLongestPath(path);
        }
    }

    public void checkAndReplaceIfLongestPath(List<Integer> path) {
        longestPath = getLongestPath();
        if(longestPath==null){
            System.out.print("Longest path is set to : ");
            System.out.println(path);
            setLongestPath(path);
        } else{
            int longestPathTotalDrop = longestPath.get(0)-longestPath.get(longestPath.size()-1);
            int totalDrop = path.get(0)-path.get(path.size()-1);
            if(path.size() > longestPath.size() || (path.size()==longestPath.size() && totalDrop > longestPathTotalDrop)){
                System.out.print("Replacing ");
                System.out.print(longestPath);
                System.out.print(" with ");
                System.out.println(path);
                setLongestPath(path);
            }
        }
    }

    public void moveEast(int latitude, int longitude, List<Integer> path){
        longitude++;
        path.add(mapGrid[latitude][longitude]);
        move(latitude, longitude, path);
    }

    public void moveSouth(int latitude, int longitude, List<Integer> path) {
        latitude++;
        path.add(mapGrid[latitude][longitude]);
        move(latitude, longitude, path);
    }

    public void moveWest(int latitude, int longitude, List<Integer> path) {
        longitude--;
        path.add(mapGrid[latitude][longitude]);
        move(latitude, longitude, path);
    }

    public void moveNorth(int latitude, int longitude, List<Integer> path) {
        latitude--;
        path.add(mapGrid[latitude][longitude]);
        move(latitude, longitude, path);
    }

    public boolean canMoveEast(int latitude, int longitude){
        return longitude < (getMaxLatitude() - 1) && mapGrid[latitude][longitude] > mapGrid[latitude][longitude + 1];
    }

    public boolean canMoveWest(int latitude, int longitude){
        return longitude > 0 && longitude <= (getMaxLongitude() - 1) && mapGrid[latitude][longitude] > mapGrid[latitude][longitude - 1];
    }

    public boolean canMoveNorth(int latitude, int longitude){
        return latitude > 0 && mapGrid[latitude][longitude] > mapGrid[latitude - 1][longitude];
    }

    public boolean canMoveSouth(int latitude, int longitude){
        return latitude < (getMaxLatitude() - 1) && mapGrid[latitude][longitude] > mapGrid[latitude + 1][longitude];
    }

    public List<Integer> getLongestPath() {
        return longestPath;
    }

    public void setLongestPath(List<Integer> longestPath) {
        this.longestPath = longestPath;
    }
}