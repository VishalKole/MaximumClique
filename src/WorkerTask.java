//******************************************************************************
//
// File:    WorkerTask.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;
import edu.rit.pj2.vbl.StringVbl;
import java.util.HashSet;

/**
 * This class acts as the worker for version 1 of the algorithm.
 *
 * @author  Vishal Kole, Srinath Obla, Akshay Sharma
 * @version 1.0
 */
public class WorkerTask extends Task {

    HashSet[] graph;

    MaximumCliqueVBL reductionVBL;

    /**
     * This is the main function for the program.
     *
     * @param strings    Contains the arguments.
     * @throws Exception Throws all Exceptions.
     */
    @Override
    public void main(String[] strings) throws Exception {

        reductionVBL = new MaximumCliqueVBL();
	    StringVbl filename = tryToReadTuple(new StringVbl());

        //Check if file can be loaded.
	    if(filename == null){
	        System.out.println(nodeName() + ": Could not load filename");
	        terminate(1);
	    }

	    //Create the graph.
       	graph = new CreateGraph(filename.stringValue()).GenerateGraph();
        HashSet<Integer> P = new HashSet<>();

        //Add all to set P.
        for (int i = 0; i < graph.length; ++i) {
            P.add(i);
        }

        //Begin execution of the algorithm.
        workerFor().exec(new Loop() {

            MaximumCliqueVBL thrreductionVBL;
            BronKerbosch algo;

            /**
             * This function sets up the thread local fields.
             */
            public void start() {
                thrreductionVBL = threadLocal(reductionVBL);
                algo = new BronKerbosch(graph);
            }

            /**
             * This function runs the algorithm.
             *
             * @param i           Vertex number.
             * @throws Exception  Throws all Exceptions.
             */
            @Override
            public void run(int i) throws Exception {

                algo.runBronKerbosch(algo.createConfigForVertex(i));
                thrreductionVBL.reduce(algo.size, algo.maximum);
            }
        });

        putTuple(reductionVBL);
    }

    /**
     * Number of cores required.
     *
     * @return Number of cores.
     */
    protected static int coresRequired(){
   	return 1;
    }
}
