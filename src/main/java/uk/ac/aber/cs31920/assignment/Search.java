
package uk.ac.aber.cs31920.assignment;

import java.util.ArrayList;
import java.util.List;

// Greedy emitter placement: repeatedly add the emitter that raises the max flow most.
public class Search {

    //Returns emitter positions {x, y}, or null if greedy algo cannot find a solution
    public static List<int[]> greedy(Node[][] nodes, int totalDemand) {
        Flow flow = new Flow(nodes, totalDemand);

        // Candidate positions = real nodes that are not parasites.
        List<int[]> free = new ArrayList<>();
        List<int[]> parasites = new ArrayList<>();
        for (int x = 0; x < nodes.length; x++)
            for (int y = 0; y < nodes.length; y++) {
                //unable to use switch due to shifting condition :(
                if (nodes[x][y].getDemand() == -1) continue;
                if (nodes[x][y].isParasite()) parasites.add(new int[] {x, y});
                else free.add(new int[] {x, y});
            }

        // Tie-breaker is the distance from each candidate to its nearest parasite.
        int[] dist = new int[free.size()];
        for (int i = 0; i < free.size(); i++) {
            dist[i] = Integer.MAX_VALUE;
            for (int[] p : parasites)
                dist[i] = Math.min(dist[i],
                    Math.max(Math.abs(free.get(i)[0] - p[0]), Math.abs(free.get(i)[1] - p[1])));
        }

        List<int[]> emitters = new ArrayList<>();
        boolean[] used = new boolean[free.size()];
        int current = 0;

        while (current < totalDemand) {
            int bestIdx = -1, bestFlow = current;       // only accept strict improvements
            for (int i = 0; i < free.size(); i++) {
                if (used[i]) continue;
                emitters.add(free.get(i));
                int f = flow.maxFlow(emitters);
                emitters.remove(emitters.size() - 1);
                if (f > bestFlow || (f == bestFlow && bestIdx != -1 && better(nodes, free, dist, i, bestIdx))) {
                    bestFlow = f;
                    bestIdx = i;
                    if (f == totalDemand) break;   // cant do better than this (f == totalDemand)
                }
            }
            if (bestIdx == -1) return null;             // no candidate improves the flow = no solution
            used[bestIdx] = true;
            emitters.add(free.get(bestIdx));
            current = bestFlow;
        }
        return emitters;
    }

    // Tie-break: higher degree first (8 beats 5 beats 3), then closer to a parasite.
    private static boolean better(Node[][] nodes, List<int[]> free, int[] dist, int a, int b) {
        int da = nodes[free.get(a)[0]][free.get(a)[1]].getNext().length;
        int db = nodes[free.get(b)[0]][free.get(b)[1]].getNext().length;
        if (da != db) return da > db;
        return dist[a] < dist[b];
    }
}

