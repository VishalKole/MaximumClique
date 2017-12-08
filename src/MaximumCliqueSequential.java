//******************************************************************************
//
// File:    MaximumCliqueSequential.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import java.util.HashSet;
import edu.rit.pj2.Task;

/**
 * This class implements the sequential version of the algorithm.
 *
 * @author  Vishal Kole, Srinath Obla, Akshay Sharma
 * @version 1.0
 */
public class MaximumCliqueSequential extends Task {
    static HashSet[] graph;
    static int size = 0;
    static HashSet<Integer> maximum;

    /**
     * This is the main function for the program.
     *
     * @param s           Contains the arguments.
     * @throws Exception  Throws all Exceptions.
     */
    public void main(String[] s) throws Exception {

        //Argument check.
        if(s.length < 1){
            System.out.println("Not enough arguments");
            usage();
            terminate(1);
        }

        //Graph creation.
        CreateGraph g = new CreateGraph(s[0]);

        //Run the algorithm and check handle any Exceptions.
        try {
            graph = g.GenerateGraph();
            BronKerbosch algo = new  BronKerbosch(graph);;
            HashSet<Integer> P = g.getHashSet();
            algo.runBronKerbosch(new HashSet<Integer>(), P, new HashSet<>());

            System.out.println("Vertices in the maximum clique: \n");
            for (Integer I : algo.maximum) {
                System.out.print(I + " ");
            }

            System.out.println("\n");
            System.out.println("Maximum Clique Size: " +algo.size);


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This function displays an error when there is a problem with the arguments passed in.
     */
    private static void usage(){
        System.out.println("Usage on non cluster: java pj2 MaximumCliqueSequential <source_file_path> \n" + "Usage on cluster computer: java pj2 jar=jarfile.jar MaximumCliqueSequential <source_file_path>");
    }
}
