package com.cs31920;

public class Graph {
    Node[] nodes;
    int dimensions;
    int[][] mapArray;
    public Graph(Node[] nodes, int dimensions, int[][] mapArray) {
        this.nodes = nodes;
        this.dimensions = dimensions;
        this.mapArray = mapArray;

        for( int x = 0; x < dimensions; x++){
            for(int y = 0; y < dimensions; y++){
                switch(mapArray[x][y]){
                    case 0:
                        //this is a regular empty node.
                        //nodes[x*dimensions+y] = new Node(new Path[8]);
                        break;
                    case 9:
                    //this means the node does not exist, so we skip it.
                    
                    default:
                    // map out all connect nodes in a node array. 
                    // this node is a parasitic node, as it has a value of 1-8.
                    // nodes[x*dimensions+y] = new Node();
                }
            }
        }
    }
}
