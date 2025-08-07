package org.mars.sw;

import lombok.ToString;
import org.mars.common.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@ToString
class Graph {
    int V, E; // Number of vertices and edges
    Edge[] edges; // Array to store all edges
    int parent[];
    List<Edge> MST = new ArrayList<Edge>();

    // Constructor
    public Graph(int V, int E) {
        this.V = V;
        this.E = E;
        edges = new Edge[E];
    }

    void makeSet(int v) {
        parent = new int[v];
        for (int i = 0; i < v; i++) {
            parent[i] = i;
        }
    }

    int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return find(parent[v]);
    }

    void union(int v, int w) {
        int findV = find(v);
        int findW = find(w);
        parent[findV] = findW;
    }

    public void kruskalMST() {
        makeSet(this.V);
        List<Edge> edges = Arrays.asList(this.edges).stream().sorted((x,y) -> x.getWeight() - y.getWeight()).collect(Collectors.toList());
        for (Edge edge : edges) {
            if (MST.size() == this.V -1) break;
            if(find(edge.getEnd()) != find(edge.getStart())) {
                MST.add(edge);
                union(edge.getEnd(), edge.getStart());
            }
            System.out.println("");
        }
        System.out.println(MST.toString());
        System.out.println("Edges in the MST:");
        for (Edge edge : MST) {
            System.out.println(edge.getStart() + " - "
                    + edge.getEnd() + ": " + edge.getWeight());
        }
    }
}

public class Kruskals {
    public static void main(String[] args) {
        int V = 6; // Number of vertices
        int E = 9; // Number of edges

        Graph graph = new Graph(V, E);
        // Add edges
        graph.edges[0] = new Edge(0, 1, 7);
        graph.edges[1] = new Edge(0, 2, 8);
        graph.edges[2] = new Edge(1, 2, 3);
        graph.edges[3] = new Edge(1, 4, 6);
        graph.edges[4] = new Edge(2, 4, 4);
        graph.edges[5] = new Edge(2, 3, 3);

        graph.edges[6] = new Edge(3, 4, 2);
        graph.edges[7] = new Edge(3, 5, 2);
        graph.edges[8] = new Edge(4, 5, 5);

        // Function call to find MST using Kruskal's algorithm
        graph.kruskalMST();
    }
}
