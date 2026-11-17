package com.cs31920;

public class Node {
    // demand does not need to be instantiated, it is only used when a node is
    // parasitic.
    int demand;
    // maximum of 8 nodes connected.
    Path[] next = new Path[8];

    boolean emmitor;
    boolean parasite;
    boolean visited;
    int[] coordinates = new int[2];

    public Node(boolean parasite, int demand, Path next[], int[] coordinates) {
        this.parasite = parasite;
        this.demand = demand;
        this.next = next;
        this.coordinates = coordinates;
        createPathArray(coordinates);
    }

    public Node(Path[] next, int[] coordinates, boolean emmitor) {
        this.next = next;
        this.coordinates = coordinates;
        this.emmitor = emmitor;
        this.visited = true;
        createPathArray(coordinates);
    }

    public Node(Path[] next, int[] coordinates) {
        this.next = next;
        this.coordinates = coordinates;
        this.visited = false;
        createPathArray(coordinates);
    }

    // Getters and setters for the Node class
    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public Path[] getNext() {
        return next;
    }

    public void setNext(Path[] next) {
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

    private Path[] createPathArray(int[] coordinates) {
        Path[] pathArray = new Path[8];

        for (int i = 0; i < 8; i++) {
            // odd coordinates cannot have more than 4 paths
            if (coordinates[0] % 2 == 1 && coordinates[1] % 2 == 1 && i > 4) {
                return pathArray;
            } else if (coordinates[0] % 2 == 1 && coordinates[1] % 2 == 0) {
                return pathArray;

                // pathArray[i] = new Path(coordinates, );
            }
            
        }
        return pathArray;
    }
}
