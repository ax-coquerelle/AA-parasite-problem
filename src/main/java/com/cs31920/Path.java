package com.cs31920;

public class Path {
    Node start;
    Node end;
    int capacity;
    
    public Path(Node start, Node end, int capacity) {
        this.start = start;
        this.end = end;
        this.capacity = capacity;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
