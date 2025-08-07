package org.mars.sw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.mars.common.Edge;
import org.w3c.dom.Node;

import java.util.*;

public class Dijkstra {
    // Construct adjacency list using ArrayList of ArrayList
    static List<Edge> adj[];

    static List<Edge>[] constructAdj(int[][] edges, int V) {
        // Initialize the adjacency list
                adj = new ArrayList[V];
        for (int i = 0; i < V; i++){
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            adj[edges[i][0]].add(new Edge(edges[i][0], edges[i][1], edges[i][2]));
        }


        return adj;
    }

    // Returns shortest distances from src to all other vertices
    static int[] dijkstra(int V, int[][] edges, int src) {

        // Create adjacency list
        List<Edge>[] adj = constructAdj(edges, V);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.getPriority()));

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Insert source with distance 0
        dist[src] = 0;

        Edge srcNode = new Edge(src,0,0,0);

        pq.offer(srcNode);

        while (!pq.isEmpty()) {
            Edge curNode = pq.poll();
            int start = curNode.getStart();
            int end = curNode.getEnd();

            for (Edge neighbor : adj[end]) {
                if (dist[neighbor.getEnd()] > dist[neighbor.getStart()] + neighbor.getWeight()) {
                    // Update distance of v
                    dist[neighbor.getEnd()] = dist[neighbor.getStart()] + neighbor.getWeight();
                    neighbor.setPriority(dist[neighbor.getEnd()]);
                    // Add updated pair to the queue
                    pq.offer(neighbor);
                }
            }
        }

        return dist;
    }

    // Driver program to test methods of graph class
    public static void main(String[] args) {
        int V = 6;
        int src = 0;

        // Edge list format: {u, v, weight}
        int[][] edges = {
                {0, 1, 1}, {0, 2, 7}, {0, 3, 4},
                {1, 4, 3},
                {2, 4, 5}, {2, 5, 8},
                {4, 5, 6},
                {5, 3, 3}
        };

        // Get shortest path distances
        int[] result = dijkstra(V, edges, src);

//         Print shortest distances in one line
        for (int d : result)
            System.out.print(d + " ");
    }


}
