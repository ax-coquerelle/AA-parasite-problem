package uk.ac.aber.cs31920.assignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        // Source - https://stackoverflow.com/q/22150650
        // Posted by k_rollo, modified by community. See post 'Timeline' for change history
        // Retrieved 2026-11-22, License - CC BY-SA 3.0
        for(String file:args){
            System.out.println(solveFile(file));
        }
    } //citation end


    public static String solveFile(String path) {
        // Get input from file, store file in map.
        try{
            ArrayList<String> map =  new ArrayList<>(Files.readAllLines(Path.of(path)));
      
        int dimensions = Integer.parseInt(map.get(0)) * 2 + 1;

        //Make a 2D array of the map with -1s representing non existing nodes.
        int[][] mapArray = handleInput(map);
        if( mapArray== null){
           return "no solution";
        }
        Node[][] nodes = nodeMap(mapArray, dimensions);
        int total = 0;
        for (Node[] row : nodes)
            for (Node n : row)
                if (n.isParasite()) total += n.getDemand();

        List<int[]> result = Search.greedy(nodes, total);
        if (result == null) return "no solution";

        StringBuilder res = new StringBuilder();
        for (int[] e : result) {
            if (res.length() > 0) res.append(' ');
            res.append(e[0]).append(' ').append(e[1]);
        }
        return res.toString();
    
          }catch (IOException e){
            return "no solution";
        }
    }

    // Function to print the map in as a grid
    // non existing nodes still exist but are represented as -1s
    public static void printGraph(Node[][] nodeMap) {
        for (int x = nodeMap.length-1; x >= 0; x --) {
            for (int y = nodeMap[x].length-1; y >= 0; y--) {
                System.out.print(String.format("%3d",nodeMap[x][y].getDemand()));
            }System.out.println();
        }
    }

    public static int[][] handleInput(ArrayList<String> map) {
        int dimensions = Integer.parseInt(map.get(0)) * 2 + 1;

        int[][] mapArray = new int[dimensions][dimensions];
        for (int x = 0; x < dimensions; x++) {
                for (int y = 0; y < dimensions; y++) {
                    mapArray[x][y] = (x % 2 == y % 2) ? 0 : -1;
            }
        }

        //parse and split all text into individual variables
        for (int i = 1; i < map.size(); i++) {
            String[] line = map.get(i).split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int demand = Integer.parseInt(line[2]);

            if (demand < 0 || demand > 8) {
                return null;
            }
            // coordinates are already validated within bounds, only assign
            if (x % 2 == y % 2) {
                mapArray[x][y] = demand;
            }
        }
        return mapArray;
    }

    //create a new Node map
    public static Node[][] nodeMap(int[][] mapArray, int dimensions) {
        Node[][] Nodes = new Node[mapArray.length][mapArray.length];
        for (int x = 0; x < mapArray.length; x++) {
            for (int y = 0; y < mapArray[x].length; y++) {
              switch(mapArray[x][y]){
                case 0 -> Nodes[x][y] = new Node(new int[]{x,y}, dimensions, mapArray); //normal node
                case -1 -> Nodes[x][y] = new Node(new int[]{x,y}, dimensions, -1); // empty node
                default -> Nodes[x][y] = new Node(new int[]{x,y},dimensions,true,mapArray[x][y]); //parasitic node
              }
            }     
        }       
        return Nodes;
    }
}