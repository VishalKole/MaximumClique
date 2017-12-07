//******************************************************************************
//
// File:    BronKerbosch.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 */
public class BronKerbosch {

    HashSet[] graph;
    int size = 0;
    HashSet<Integer> maximum;
    HashSet<Integer> P = new HashSet<>();

    BronKerbosch(HashSet[] graph) {
        this.graph = graph;
        for (int i = 0; i < graph.length; ++i) {
            P.add(i);
        }
    }

    public void runBronKerbosch(BKConfig configuration) {
        runBronKerbosch(configuration.getR(), configuration.getP(), configuration.getX());
    }

    public void runBronKerbosch(HashSet<Integer> R, HashSet<Integer> P, HashSet<Integer> X) {

        if (P.isEmpty() && X.isEmpty()) {

            if (this.size < R.size()) {
                maximum = (HashSet<Integer>) R.clone();
                size = R.size();
            }
        }

        while (!P.isEmpty()) {
            Integer i;
            {
                Iterator<Integer> itr = P.iterator();
                i = itr.next();
            }

            HashSet<Integer> R2 = (HashSet<Integer>) R.clone();
            HashSet<Integer> P2 = (HashSet<Integer>) P.clone();
            HashSet<Integer> X2 = (HashSet<Integer>) X.clone();
            P2.retainAll(graph[i]);
            X2.retainAll(graph[i]);
            R2.add(i);
            this.runBronKerbosch(R2, P2, X2);

            P.remove(i);
            X.add(i);
        }
    }

    public BKConfig createConfigForVertex(int i) {
        HashSet<Integer> cloneP;
        HashSet<Integer> R2;
        HashSet<Integer> X2;
        R2 = new HashSet<>();
        X2 = new HashSet<>();

        cloneP = (HashSet<Integer>) this.P.clone();
        cloneP.remove(i);
        cloneP.retainAll(graph[i]);

        for (int j = 0; j < i; ++j)
            cloneP.remove(j);

        R2.add(i);
        for (int j = 0; j < i; ++j)
            X2.add(j);
        X2.retainAll(graph[i]);

        return new BKConfig(R2, cloneP, X2);
    }

    public BKConfig createConfigForVertex2(int i) {

        int node = i / (graph.length - 1);
        int point = i - (node * (graph.length - 1));

        if(point>=node) point++;

        HashSet<Integer> cloneP;
        HashSet<Integer> R2;
        HashSet<Integer> X2;
        R2 = new HashSet<>();
        X2 = new HashSet<>();

        cloneP = (HashSet<Integer>) this.P.clone();


        cloneP.remove(node);
        R2.add(node);

        cloneP.remove(point);
        R2.add(point);

        cloneP.retainAll(graph[node]);
        cloneP.retainAll(graph[point]);

        for (int j = 0; j < point; ++j)
            cloneP.remove(j);

        for (int j = 0; j < point; ++j)
            if(j!=node) X2.add(j);

        X2.retainAll(graph[point]);

        return new BKConfig(R2, cloneP, X2);
    }
}