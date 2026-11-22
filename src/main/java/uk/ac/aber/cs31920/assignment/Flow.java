package uk.ac.aber.cs31920.assignment;

import java.util.Arrays;

public class Flow {
    private final Node[][] nodes;
    private final int dimension, source, sink, totalDemand;

    // Edge arrays: edge i and edge i^1 are a residual pair.
    private final int[] head, nextEdge, to, cap, baseCap;
    private int edgeCount = 0;

    private final int[] splitEdge;   // id -> index of in->out edge (-1 if parasite/placeholder)
    private final int[] sourceEdge;  // id -> index of source->out edge (-1 if none)
    private boolean impossible = false; // a parasite needs more pulses than it has incident edges

    //nodes and total demand value.
    public Flow(Node[][] nodes, int DVal){
        this.nodes = nodes;
        this.dimension = nodes.length;
        this.totalDemand = DVal;
        this.source = 2 * dimension * dimension;
        this.sink = source + 1;
    
        //review and potentially change
        int vertexCount = sink + 1;
        int maxEdges = 22 * dimension * dimension;          // generous upper bound, each edge stored twice
        head = new int[vertexCount];
        Arrays.fill(head, -1);
        nextEdge = new int[maxEdges];
        to = new int[maxEdges];
        cap = new int[maxEdges];
        baseCap = new int[maxEdges];
        splitEdge = new int[dimension * dimension];
        sourceEdge = new int[dimension * dimension];
        Arrays.fill(splitEdge, -1);
        Arrays.fill(sourceEdge, -1);

        for(int x = 0; x < nodes.length; x++){
            for(int y = 0; y< nodes[x].length; y++){
                Node node = nodes[x][y];
                int id = x* dimension + y;
                int in = 2*id, out = in + 1;


            }
        }
    }
    //run for each iteration
    public static boolean solveable(){
    

        return true;
    }
}
