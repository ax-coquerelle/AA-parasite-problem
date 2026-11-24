package uk.ac.aber.cs31920.assignment;

import java.util.Arrays;
import java.util.List;

public class Flow {
    private final Node[][] nodes;
    private final int dimension, source, sink, totalDemand;

    // edge e and edge e prime are a residual pair.
    private final int[] head, nextEdge, tail, capacity, baseCap;
    private int edgeCount = 0;

    private final int[] splitEdge; // cell id index of its in->out edge -1 if none
    private final int[] sourceEdge; // cell id index of its source->out edge -1 if none
    private final int[] parentEdge, queue; // BFS array allocated once
    private boolean impossible = false;

    public Flow(Node[][] nodes, int totalDemand) {
        this.nodes = nodes;
        this.dimension = nodes.length;
        this.totalDemand = totalDemand;
        this.source = 2 * dimension * dimension;
        this.sink = source + 1;

        //using linked foward star lists, we map each node to an index and use
        int vertexCount = sink + 1;
        int maxEdges = 22 * dimension * dimension; // each edge stored twice as a high upper bound
        head = new int[vertexCount];
        Arrays.fill(head, -1);
        nextEdge = new int[maxEdges];
        tail = new int[maxEdges];
        capacity = new int[maxEdges];
        baseCap = new int[maxEdges];
        splitEdge = new int[dimension * dimension];
        sourceEdge = new int[dimension * dimension];
        Arrays.fill(splitEdge, -1); // fill with non existent paths
        Arrays.fill(sourceEdge, -1);
        parentEdge = new int[vertexCount];
        queue = new int[vertexCount];

        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                Node node = nodes[x][y];
                if (node.getDemand() == -1)
                    continue; // case handling for empty cell (skips)
                int id = x * dimension + y;
                int in = 2 * id, out = in + 1;

                if (node.isParasite()) {
                    addEdge(in, sink, node.getDemand()); // drain, capacity = demand
                    if (node.getDemand() > node.getNext().length)
                        impossible = true; // set impossible if demand is bigger than edges it has access to
                    continue; // parasites pass nothing onward
                }
                splitEdge[id] = addEdge(in, out, 1); // one pulse per node
                sourceEdge[id] = addEdge(source, out, 0); // opened only for emitters
                for (NodePath p : node.getNext()) {
                    int neighbourId = p.end[0] * dimension + p.end[1];
                    addEdge(out, 2 * neighbourId, 1); // out of this cell, into the neighbour
                }
            }
        }
    }

 
    private int addEdge(int vStart, int vEnd, int capacity) {
        tail[edgeCount] = vEnd;
        baseCap[edgeCount] = capacity;
        nextEdge[edgeCount] = head[vStart];
        head[vStart] = edgeCount++;
        tail[edgeCount] = vStart;
        baseCap[edgeCount] = 0;
        nextEdge[edgeCount] = head[vEnd];
        head[vEnd] = edgeCount++;
        return edgeCount - 2;
    }

    public int maxFlow(List<int[]> emitters) {
        if (impossible)
            return 0;
        System.arraycopy(baseCap, 0, capacity, 0, edgeCount); // reset tail the starting capacities
        for (int[] e : emitters) {
            int id = e[0] * dimension + e[1];
            capacity[splitEdge[id]] = 0; // nothing can pass through an emitter
            capacity[sourceEdge[id]] = nodes[e[0]][e[1]].getNext().length; // one pulse per incident edge
        }
        return augment();
    }

    public boolean isFeasible(List<int[]> emitters) {
        return maxFlow(emitters) == totalDemand;
    }

    // Repeated BFS augmentation; stops at totalDemand or when no route is left.
    private int augment() {
        int flow = 0;
        while (flow < totalDemand) {
            Arrays.fill(parentEdge, -1);
            int qHead = 0, qTail = 0;
            queue[qTail++] = source;
            parentEdge[source] = -2; // marks the source as visited
            while (qHead < qTail && parentEdge[sink] == -1) {
                int u = queue[qHead++];
                for (int e = head[u]; e != -1; e = nextEdge[e]) {
                    int v = tail[e];
                    if (capacity[e] > 0 && parentEdge[v] == -1) {
                        parentEdge[v] = e;
                        queue[qTail++] = v;
                    }
                }
            }
            if (parentEdge[sink] == -1)
                break; // no augmenting path left

            int push = Integer.MAX_VALUE; // smallest spare capacity on the route
            for (int v = sink; v != source; v = tail[parentEdge[v] ^ 1])
                push = Math.min(push, capacity[parentEdge[v]]);
            for (int v = sink; v != source; v = tail[parentEdge[v] ^ 1]) {
                capacity[parentEdge[v]] -= push;
                capacity[parentEdge[v] ^ 1] += push;
            }
            flow += push;
        }
        return flow;
    }
}