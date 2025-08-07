package org.mars.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {

    public static void main(String[] args) {
        int a[] = {1,2,3,4};
        int b[] = {1,2,4};
        System.out.println(Arrays.compare(a, b));

        String chair = null;
        String table = "metal";
        chair = chair + table;
        System.out.println(chair);
    }
}

class Task {
    String name;
    int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String toString() {
        return name + " (priority " + priority + ")";
    }
}

class Main {
    public static void main(String[] args) {
        Comparator<Task> priorityComparator = Comparator.comparingInt(task -> task.priority);

        PriorityQueue<Task> pq = new PriorityQueue<>(priorityComparator);

        pq.add(new Task("Task A", 3));
        pq.add(new Task("Task B", 1));
        pq.add(new Task("Task C", 2));

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}