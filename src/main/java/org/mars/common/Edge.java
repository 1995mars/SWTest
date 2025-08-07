package org.mars.common;

import lombok.Data;

@Data
public class Edge implements Comparable<Edge> {

    int start, end, weight, priority;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Edge(int start, int end, int weight, int priority) {
        this.start = start;
        this.end = end;
        this.weight = weight;
        this.priority = priority;
    }

    // Compare two edges based on their weight
    @Override
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}