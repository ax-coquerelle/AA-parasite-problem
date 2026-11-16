package com.cs31920;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Get input from console, store relevant file in map.
        ArrayList<String> map = getInput();
        // Further processing can be done here before being transofrmed into a graph.
        int dimensions = Integer.parseInt(map.get(0)) * 2 + 1;

        int[][] mapArray = new int[dimensions][dimensions];
        for (int x = 0; x < dimensions; x++) {
            if (x % 2 == 1) {
                for (int y = 0; y < dimensions; y++) {
                    if (y % 2 == 0) {
                        mapArray[x][y] = 9;
                    }
                }
            }
        }
        for (int i = 1; i < map.size(); i++) {
            String[] line = map.get(i).split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int demand = Integer.parseInt(line[2]);
            if (demand < 0 || demand > 8) {
                System.err.println("Unsolvable instance due to invalid demand value: " + demand);
            }
            // Ensure the coordinates are valid, and withion the bounds
            if (x % 2 == y % 2 &&
                    x < dimensions &&
                    y < dimensions &&
                    x >= 0 && y >= 0) {
                mapArray[x][y] = demand;
            }
        }

        Graph g = new Graph(dimensions, mapArray);
    }

    public static ArrayList<String> getInput() {

        // INPUT FILE READING
        ArrayList<String> Map = new ArrayList<>();
        try {
            // Create a BufferedReader to read input from the console
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            String input = r.readLine();
            File f = new File(input);
            System.out.println("File exists: " + f.exists());
            // Read each line from the file and add it to the ArrayList used to build the
            // map
            try (Scanner sc = new Scanner(f)) {
                // Read each line from the file and add it to the ArrayList used to build the
                // map
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    Map.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
        return Map;
    }

    // Function to print the map in as a grid
    // non existing nodes still exist but are represented as 9s
    public static void printMap(int[][] map) {
        for (int[] map1 : map) {
            for (int j = 0; j < map1.length; j++) {
                System.out.print(map1[j] + " ");
            }
            System.out.println();
        }
    }
}