//******************************************************************************
//
// File:    MaximumCliqueClusterMassivelyParallel2.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.vbl.StringVbl;
import edu.rit.pj2.Job;
import edu.rit.pj2.tuple.ObjectArrayTuple;
import java.util.HashSet;

/**
 * This class implements version 2 of the cluster parallel version of the algorithm.
 * It contains all the data and functionality required.
 *
 * @author  Vishal Kole, Srinath Obla, Akshay Sharma
 * @version 1.0
 */
public class MaximumCliqueClusterMassivelyParallel2 extends Job {
    HashSet[] graph;

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
        //Creating graph here just for the length.
        CreateGraph g = new CreateGraph(strings[0]);
        int vertices = g.getNumberOfVertices();

        //Eliminating tuple requirement by generating graphs locally
        //putTuple(9, new ObjectArrayTuple<HashSet>(graph));
        //Placing filename in tuple space for the workers
        StringVbl filename = new StringVbl(strings[0]);
        putTuple(1, filename);

        // Setting schedules and calling workers
        masterSchedule(guided);
        masterChunk(1);
        masterFor(0, (vertices*(vertices-1)) -1, WorkerTask2.class);
        rule().atFinish().task(ReduceTask.class);
    }

    /**
     * This function displays an error when there is a problem with the arguments passed in.
     */
    private static void usage(){
        System.out.println("Usage on cluster computer: java pj2 jar=jarfile.jar MaximumCliqueClusterMassivelyParallel <source_file_path>");
    }
}
