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
    }

    public Node(Path[] next, int[] coordinates, boolean emmitor) {
        this.next = next;
        this.coordinates = coordinates;
        this.emmitor = emmitor;
        this.visited = true;
    }

    public Node(Path[] next, int[] coordinates) {
        this.next = next;
        this.coordinates = coordinates;
        this.visited= false;
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
    
}
