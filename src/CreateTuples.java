//******************************************************************************
//
// File:    CreateTuples.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.Task;
import java.util.HashSet;

/**
 * This class creates the tuples.
 *
 * @author  Vishal Kole, Srinath Obla, Akshay Sharma
 * @version 1.0
 */
public class CreateTuples extends Task {

    HashSet[] graph;

    /**
     * This is the parameterized constructor for the class.
     *
     * @param graph The graph to be used.
     */
    CreateTuples(HashSet[] graph) {
        this.graph = graph;
    }

    public void main(String[] strings) throws Exception {

        //graph = readTuple(new ObjectArrayTuple<HashSet>()).item;
        HashSet<Integer> verticesCovered = new HashSet<>();
        HashSet<Integer> P = new HashSet<>();

        //Add to set P.
        for (int i = 0; i < graph.length; ++i) {
            P.add(i);
        }

        //Iterate through and generate the configurations.
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
