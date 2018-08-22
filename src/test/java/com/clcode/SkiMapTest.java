package com.clcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SkiMapTest {
    private static SkiMap map;
    private static List<Integer> path;
    private static int[] mapDimension = {4,4};
    private static int[][] mapGrid = new int[][]{{4,8,7,3},{2,5,9,3},{6,3,2,5},{4,4,1,6}};

    @BeforeAll
    public static void init(){
        map = new SkiMap(mapDimension[0], mapDimension[1], mapGrid);
        map.setMapGrid(mapGrid);
        path = new ArrayList<>();
        path.add(4);
        path.add(3);
        path.add(2);
        path.add(1);
    }

    @Test
    public void testMapIsInitialized(){
        assertNotNull(map);
    }

    @Test
    public void testNoDefaultMapGrid(){
        SkiMap map = new SkiMap();
        assertNotNull(map.getMapGrid());
        assertEquals(50, map.getMapGrid()[0][0]);

    }

    @Test
    public void testConfigureMap(){
        assertNotNull(map.getMapGrid());
    }

    @Test
    public void testMapGridValuesInitialized(){
        assertNotEquals(map.getMapGrid()[0][0],0);
    }


    @Test
    public void testCanMoveEast(){
        assertFalse(map.canMoveEast(0,0 ));
        assertTrue(map.canMoveEast(2,0 ));
        assertTrue(map.canMoveEast(2,1));
        assertFalse(map.canMoveEast(1,0));

    }

    @Test
    public void testCanMoveWest(){
        assertFalse(map.canMoveWest(0,0));
        assertTrue(map.canMoveWest(0,1));
        assertFalse(map.canMoveWest(0,2));
        assertTrue(map.canMoveWest(1,2));
    }

    @Test
    public void testCanMoveNorth(){
        assertFalse(map.canMoveNorth(0,0));
        assertFalse(map.canMoveNorth(0,3));
        assertTrue(map.canMoveNorth(1,2));
        assertTrue(map.canMoveNorth(2,0));
    }

    @Test
    public void testCanMoveSouth(){
        assertTrue(map.canMoveSouth(0,0));
        assertTrue(map.canMoveSouth(2,0));
        assertFalse(map.canMoveSouth(3,0));
        assertFalse(map.canMoveSouth(2,1));
    }

    @Test
    public void testMoveEast(){
        path = new ArrayList<>();
        path.add(6); //Starting point
        map.moveEast(2,0, path);
        assertEquals(new Integer(3), path.get(1)); //The east of the starting point should be equals to 3
    }

    @Test
    public void testMoveSouth(){
        path = new ArrayList<>();
        path.add(4); //Starting point
        map.moveSouth(0,0, path);
        assertEquals(new Integer(2), path.get(1)); //The south of the starting point should be equals to 2
    }

    @Test
    public void testMoveWest(){
        path = new ArrayList<>();
        path.add(8);
        map.moveWest(0,1, path);
        assertEquals(new Integer(4), path.get(1)); //The west of the starting point should be equals to 4
    }

    @Test
    public void testMoveNorth(){
        path = new ArrayList<>();
        path.add(9);
        map.moveNorth(1,2, path);
        assertEquals(new Integer(7), path.get(1)); //The north of the starting point should be equals to 7
    }

    @Test
    public void testLongestPathChecking(){
        Integer[] pattern1 = new Integer[]{8,5,3,2,1};
        Integer[] pattern2 = new Integer[]{9,5,3,2,1};
        Integer[] pattern3 = new Integer[]{8,7,6,5,4,3,2,1,1,1,1,1};

        List<Integer> path1 = new ArrayList<>();
        List<Integer> path2 = new ArrayList<>();;
        List<Integer> path3 = new ArrayList<>();

        path1.addAll(Arrays.asList(pattern1));
        path2.addAll(Arrays.asList(pattern2));
        path3.addAll(Arrays.asList(pattern3));

        map.checkAndReplaceIfLongestPath(path1);
        assertEquals(path1, map.getLongestPath());
        map.checkAndReplaceIfLongestPath(path2);
        assertEquals(path2, map.getLongestPath());
        map.checkAndReplaceIfLongestPath(path1);
        assertEquals(path2, map.getLongestPath());
        map.checkAndReplaceIfLongestPath(path3);
        assertEquals(path3, map.getLongestPath());
    }
}