package uk.ac.aber.cs31920.assignment;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private static final int[][] diagonal = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };
    private static final int[][] cardinal = { { 2, 0 }, { -2, 0 }, { 0, 2 }, { 0, -2 } };
    // demand does not need to be instantiated, it is only used when a node is
    // parasitic.
    private int demand;
    
    // maximum of 8 nodes connected.
    private NodePath[] next = new NodePath[8];

    private boolean emmitor;
    private boolean parasite;
    private boolean visited;
    // coordintes are used to find the node when searching via paths.
    private int[] coordinates;

    // constructor for parasite nodes
    public Node(int[] coordinates, int dimensions, int[][] mapArray, boolean parasite, int demand) {
        this.parasite = parasite;
        this.demand = demand;
        this.coordinates = coordinates;
        next = createPathArray(dimensions);
    }

    // constructor for null nodes
    public Node(int[] coordinates, int dimensions, int[][] mapArray, int demand) {
        this.coordinates = coordinates;
        this.visited = false;
        this.demand = demand;
        next = createPathArray(dimensions);
    }

    // constructor for normal nodes
    public Node(int[] coordinates, int dimensions, int[][] mapArray) {
        this.coordinates = coordinates;
        this.visited = false;
        next = createPathArray(dimensions);
    }

    // simple helper function, adds a path with the offset coordinates with capacity
    // 1
    private void addPaths(List<NodePath> paths, int[][] offsets, int dimensions) {
        for (int[] o : offsets) {
            int x = coordinates[0] + o[0];
            int y = coordinates[1] + o[1];
            if (x >= 0 && y >= 0 && x < dimensions && y < dimensions)
                paths.add(new NodePath(coordinates, new int[] { x, y }, 1));
        }
    }

    // function to create the paths to each node
    //debugging help from claude for a rewritten createPathArray.
    private NodePath[] createPathArray(int dimensions) {

        ArrayList<NodePath> paths = new ArrayList<>();
        //if coords are even or not
        boolean evenX = coordinates[0] % 2 == 0;
        boolean evenY = coordinates[1] % 2 == 0;

        if (evenX != evenY)
            // if 1 node is even, return empty array so code work without null check
            return new NodePath[0];

        addPaths(paths, diagonal, dimensions); // real nodes have diagonals
        if (evenX)
            addPaths(paths, cardinal, dimensions); // even/even nodes link along cardinal directions
        return paths.toArray(new NodePath[0]);
    }

    // Getters and setters for the Node class
    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public NodePath[] getNext() {
        return next;
    }

    public void setNext(NodePath[] next) {
        this.next = next;
    }

    public boolean isEmmitor() {
        return emmitor;
    }

    public void setEmmitor(boolean emmitor) {
        this.emmitor = emmitor;
    }

    public boolean isParasite() {
        return parasite;
    }

    public void setParasite(boolean parasite) {
        this.parasite = parasite;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
