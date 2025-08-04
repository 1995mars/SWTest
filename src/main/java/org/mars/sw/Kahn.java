package org.mars.sw;

import java.util.*;

public class Kahn {

    // We mainly take input graph as a set of edges. This function is
    // mainly a utility function to convert the edges to an adjacency
    // list
    static List<Integer>[] constructadj(int V, int[][] edges) {
        List<Integer>[] adj = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
        }

        return adj;
    }

    // Function to return list containing vertices in Topological order
    static int[] topologicalSort(int V, int[][] edges) {
        List<Integer>[] adj = constructadj(V, edges);
        int[] indegree = new int[V];

        // Calculate indegree of each vertex
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj[i]) {
                indegree[neighbor]++;
            }
        }

        // Queue to store vertices with indegree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] result = new int[V];
        int index = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            result[index++] = node;

            for (int neighbor : adj[node]) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        // Check for cycle
        if (index != V) {
            System.out.println("Graph contains a cycle!");
            return new int[0];
        }

        return result;
    }

    public static void main(String[] args) {
        int V = 6;
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {4, 5}, {5, 1}, {5, 2}};

        int[] result = topologicalSort(V, edges);

        if (result.length > 0) {
            for (int i : result) {
                System.out.print(i + " ");
            }
        }
    }
}