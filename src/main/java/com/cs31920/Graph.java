package com.cs31920;

public class Graph {
    Node[] nodes;
    int dimensions;
    public Graph(int dimensions, int[][] mapArray) {
        this.nodes = new Node[dimensions * dimensions];
        this.dimensions = dimensions;

        //path array creation

        int count = 0;
        for( int x = 0; x < dimensions; x++){
            for(int y = 0; y < dimensions; y++){
                switch(mapArray[x][y]){
                    case 0:
                        //this is a regular empty node.
                        nodes[count] = new Node(new Path[8], new int[]{x,y});
                        break;
                    case 9:
                    //this means the node does not exist, so we skip it.
                    default:
                    // map out all connect nodes in a node array. 
                    // this node is a parasitic node, as it has a value of 1-8.
                    nodes[count] = new Node(true, mapArray[x][y], new Path[8], new int[]{x,y});
                }
                //used to increment the index of node array.
                count++;
            }
        }
    }
}
