package org.mars.sw;

import lombok.ToString;

@ToString
public class UnionField {

    public int[] parent,size;

    UnionField(int n){
        makeSet(n);
    }

    void makeSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 1; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            return find(parent[x]);
//            return find[x] = find(parent[x]);
        }
        return x;
    }

    void union(int x, int y) {
        int a = find(x);
        int b = find(y);
        if(a != b){
            if(size[a] >= size[b]){
                parent[b] = a;
                size[a] += size[b];
            }else {
                parent[a] = b;
                size[b] += size[a];
            }
        }
    }


    public static void main(String[] args) {
        int n = 5;
        UnionField uf = new UnionField(5);
        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(1, 3);

        System.out.println(uf);
    }


}
