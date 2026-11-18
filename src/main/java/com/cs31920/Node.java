package com.cs31920;

public class Node {
    // demand does not need to be instantiated, it is only used when a node is
    // parasitic.
    int demand;
    // maximum of 8 nodes connected.
    NodePath[] next = new NodePath[8];

    boolean emmitor;
    boolean parasite;
    boolean visited;
    //coordintes are used to find the node when searching via paths.
    int[] coordinates;

    //constructor for parasite nodes
    public Node(int[] coordinates,int dimensions, int[][] mapArray,boolean parasite, int demand) {
        this.parasite = parasite;
        this.demand = demand;
        this.coordinates = coordinates;
        createPathArray(coordinates, mapArray, dimensions);
    }
    //constructor for null nodes
    public Node(int[] coordinates, int dimensions, int[][] mapArray, int demand) {
        this.coordinates = coordinates;
        this.visited = false;
        createPathArray(coordinates, mapArray, dimensions);
    }
    //constructor for normal nodes
    public Node(int[] coordinates, int dimensions, int[][] mapArray) {
        this.coordinates = coordinates;
        this.visited = false;
        createPathArray(coordinates, mapArray, dimensions);
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

    private NodePath[] createPathArray(int[] coordinates,int[][] mapArray, int dimensions){ {
        double[][] offsets = { {+1, +1}, {+1, -1}, {-1, -1}, {-1, +1} };
        NodePath[] pathArray = new NodePath[8];
        
        if(coordinates[0]%2 == 1 && coordinates[1]%2 == 1){
            
        }else{

        }
        return pathArray;
    }
    }
}
