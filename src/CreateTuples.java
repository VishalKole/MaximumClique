//******************************************************************************
//
// File:    CreateTuples.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.Task;
import edu.rit.pj2.tuple.ObjectArrayTuple;

import java.util.HashSet;

public class CreateTuples extends Task {

    HashSet[] graph;

    CreateTuples(HashSet[] graph) {
        this.graph = graph;
    }

    public void main(String[] strings) throws Exception {

        //graph = readTuple(new ObjectArrayTuple<HashSet>()).item;
        HashSet<Integer> verticesCovered = new HashSet<>();
        HashSet<Integer> P = new HashSet<>();

        for (int i = 0; i < graph.length; ++i) {
            P.add(i);
        }

        for (int i = 0; i < graph.length; ++i) {
            HashSet<Integer> cloneP = (HashSet<Integer>) P.clone();
            cloneP.remove(i);
            cloneP.retainAll(graph[i]);
            cloneP.removeAll(verticesCovered);

            HashSet<Integer> R2 = new HashSet<>();
            R2.add(i);

            HashSet<Integer> X2 = new HashSet<>();
            X2.addAll(verticesCovered);
            X2.retainAll(graph[i]);

            BKConfig newConfig = new BKConfig(R2, cloneP, X2);
            putTuple(newConfig);

            verticesCovered.add(i);

        }

    }
}
