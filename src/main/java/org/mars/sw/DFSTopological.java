package org.mars.sw;

import java.util.*;
import java.util.stream.Collectors;

public class DFSTopological {

    private static List<Integer> topologicalSort(int v, int[][] edges) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[v];
        List<Integer>[] adj = contructAdj(v,edges);

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                topologicalDFS(i,stack,visited,adj);
            }
        }

        if (!stack.empty()) {
            res = stack.stream().collect(Collectors.toList());
        }
        Collections.reverse(res);
        return res;
    }

    private static void topologicalDFS(int i, Stack<Integer> stack, boolean[] visited, List<Integer>[] adj) {
        visited[i] = true;
        for (int next : adj[i]) {
            if (!visited[next]) {
                topologicalDFS(next,stack,visited,adj);
            }
        }
        stack.push(i);
    }

    private static List<Integer>[] contructAdj(int v, int[][] edges) {
        List<Integer>[] adj = new List[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            adj[edges[i][0]].add(edges[i][1]);
        }
        return adj;
    }

    public static void main(String[] args)
    {
        int v = 6;
        int[][] edges = {{2, 3}, {3, 1}, {4, 0},
                {4, 1}, {5, 0}, {5, 2}};

        List<Integer> ans = topologicalSort(v, edges);

        for (int node : ans) {
            System.out.print(node + " ");
        }
        System.out.println();
    }

}
