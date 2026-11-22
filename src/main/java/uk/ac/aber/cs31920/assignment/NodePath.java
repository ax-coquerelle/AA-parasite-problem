package uk.ac.aber.cs31920.assignment;

public class NodePath {
    int[] start;
    int[] end;
    int capacity;
    
    public NodePath(int[] startCoordinates, int[] endCoordinates, int capacity) {
        this.start = startCoordinates;
        this.end = endCoordinates;
        this.capacity = capacity;
    }

    public int[] getStart() {
        return start;
    }

    public void setStart(int[] startCoordinates) {
        this.start = startCoordinates;
    }

    public int[] getEnd() {
        return end;
    }

    public void setEnd(int[] endCoordinates) {
        this.end = endCoordinates;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
