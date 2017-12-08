//******************************************************************************
//
// File:    MaximumCliqueMulticoreParallel2.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;
import java.util.HashSet;

/**
 * This class implements version 2 of the multicore parallel version of the algorithm.
 * It contains all the data and functionality required.
 *
 * @author  Vishal Kole, Srinath Obla, Akshay Sharma
 * @version 1.0
 */
public class MaximumCliqueMulticoreParallel2 extends Task {


    private HashSet<Integer>[] graph;

    /**
     * This is the main function to be run.
     *
     * @param strings     Contains the arguments.
     * @throws Exception  Throws all Exception that might occur.
     */
    @Override
    public void main(String[] strings) throws Exception {

        //Argument check.
        if(strings.length < 1){
            System.out.println("Not enough arguments");
            usage();
            terminate(1);
        }

        //Graph creation.
        CreateGraph g = new CreateGraph(strings[0]);
        graph = g.GenerateGraph();

        //Set up reducer.
        MaximumCliqueVBL masterReducer = new MaximumCliqueVBL();

        //Distribute the vertices.
        parallelFor(0, (graph.length*(graph.length-1)) - 1).schedule(guided).chunk(1).exec (new Loop() {

            MaximumCliqueVBL thrReducer;
            BronKerbosch algo;

            /**
             * This function sets up the thread local fields.
             *
             * @throws Exception Throws all Exceptions.
             */
            @Override
            public void start() throws Exception {
                thrReducer = threadLocal(masterReducer);
                algo = new BronKerbosch(graph);
            }

            /**
             * This function runs the algorithm.
             *
             * @param i            The vertex.
             * @throws Exception   Throws all Exceptions.
             */
            @Override
            public void run(int i) throws Exception {

                algo.runBronKerbosch(algo.createConfigForVertex2(i));
                thrReducer.reduce(algo.size, algo.maximum);
            }
        });

        //Debug outputs.
        System.out.println("Maximum Clique Size: " + masterReducer.size);
        System.out.println("\n");
        System.out.println("Vertices in the maximum clique: \n");

        //Can view progress through the graph vertices.
        for (Integer I : masterReducer.maximum) {
            System.out.print(I + " ");
        }
    }

    /**
     * This function displays an error when there is a problem with the arguments passed in.
     */
    private static void usage(){
        System.out.println("Usage on non cluster: java pj2 MaximumCliqueMulticoreParallel <source_file_path> \n" +
                "Usage on cluster computer: java pj2 jar=jarfile.jar MaximumCliqueMulticoreParallel <source_file_path>");
    }

    /**
     * Number of cores required.
     *
     * @return Number of cores.
     */
    protected static int coresRequired()
    {
        return 1;
    }
}
