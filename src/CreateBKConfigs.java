//******************************************************************************
//
// File:    CreateBKConfigs.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.Task;
import edu.rit.pj2.tuple.ObjectArrayTuple;

import java.io.IOException;
import java.util.HashSet;

/**
 * This class is used to create configurations for
 * the algorithm.
 *
 * @author  Vishal Kole, Srinath Obla, Akshay Sharma
 * @version 1.0
 */
public class CreateBKConfigs extends Task {

    private HashSet<Integer>[] graph;
    private final int BUILD_TUPLE = 1;
    private final int BUILD_ARRAY = 2;
    private BKConfig[] configs;

    /**
     * This is the main function.
     *
     * @param strings      Strings to be used.
     * @throws Exception   Throws all Exceptions.
     */
    @Override
    public void main(String[] strings) throws Exception {
        graph = readTuple(new ObjectArrayTuple<HashSet<Integer>>()).item;
        buildConfigs(BUILD_TUPLE);
    }

    /**
     * This is the parameterized constructor for the class.
     *
     * @param graph The graph to be used.
     */
    CreateBKConfigs(HashSet<Integer>[] graph) {
        this.graph = graph;
    }

    /**
     * This function retrieves the configurations.
     *
     * @return The configurations.
     */
    public BKConfig[] getConfigs() {
        configs = new BKConfig[graph.length];
        buildConfigs(BUILD_ARRAY);
        return configs;
    }

    /**
     * This function is used to build the configurations.
     *
     * @param BUILD_TYPE The type of data structure to be used.
     */
    @SuppressWarnings("unchecked")
    public void buildConfigs(int BUILD_TYPE) {
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

            if (BUILD_TYPE == BUILD_ARRAY)
                configs[i] = newConfig;
            else {
                try {
                    putTuple(newConfig);
                } catch (IOException exception) {
                    System.out.println("Error placing tuple!");
                    exception.printStackTrace();
                }
            }

            verticesCovered.add(i);

        }
    }
}
