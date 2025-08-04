package org.mars.sw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class DFSTopologicalBoom {

    private static List<Integer> getJumpPositions(List<Bomb> bombs) {
        List<Integer> pos = bombs.stream().map(x -> x.getP()).sorted().toList();
        List<Integer> res = new ArrayList<>();
        List<Integer>[] jumps = comtructAdj(pos,bombs);

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[jumps.length+1];

        for (int i = 0; i < pos.size(); i++) {
            if (visited[pos.get(i)] == false) {
                topologicalDFS(pos.get(i),stack,visited, jumps);
            }
        }

        if (!stack.empty()) {
            res = stack.stream().collect(Collectors.toList());
        }
        return res;
    }

    private static void topologicalDFS(int i, Stack<Integer> stack, boolean[] visited, List<Integer>[] jumps) {
        visited[i] = true;
        for (int next : jumps[i]) {
            if (!visited[next]) {
                topologicalDFS(next,stack,visited,jumps);
            }
        }
        stack.push(i);
    }

    private static List<Integer>[] comtructAdj(List<Integer> pos, List<Bomb> bombs) {

        List<Integer>[] adj = new ArrayList[pos.stream().max(Integer::compareTo).get().intValue() + 1];

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < bombs.size(); i++) {
            List<Integer> tmp = bombs.get(i).getJumpPositions();
            int tmpPost = bombs.get(i).getP();
            tmp.stream().forEach(x -> {
                if(pos.contains(x)){
                    adj[tmpPost].add(x);
                }
            });
        }
        return adj;
    }


    public static void main(String[] args)
    {
        List<Bomb> bombs = new ArrayList<>();
        bombs.add(new Bomb(2, 3, 2)); // nhảy đến 5, 8
        bombs.add(new Bomb(1, 2, 3)); // nhảy đến 3, 5, 7
        bombs.add(new Bomb(5, 1, 3)); // nhảy đến 6,7,8

        List<Integer> result = getJumpPositions(bombs);
        for (int node : result) {
            System.out.print(node + " ");
        }
        System.out.println();
    }


}

@Data
@AllArgsConstructor
@ToString
class Bomb {

    int p;  // vị trí ban đầu
    int r; //độ đàn hồi (mỗi bước nhảy đi r đơn vị)
    int c;// số lần nhảy

    List<Integer> getJumpPositions() {
        List<Integer> positions = new ArrayList<>();
        for (int i = 1; i <= c; i++) {
            positions.add(p + i * r);
        }
        return positions;
    }
}
