//******************************************************************************
//
// File:    MaximumCliqueSequential.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import java.util.HashSet;
import java.util.Iterator;

import edu.rit.pj2.Task;

public class MaximumCliqueSequential extends Task {
    static HashSet[] graph;
    static int size = 0;
    static HashSet<Integer> maximum;

    public void main(String[] s) throws Exception {
        if(s.length < 1){
            System.out.println("Not enough arguments");
            usage();
            terminate(1);
        }
        CreateGraph g = new CreateGraph(s[0]);

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
	
    private static void usage(){
        System.out.println("Usage on non cluster: java pj2 MaximumCliqueSequential <source_file_path> \n" + "Usage on cluster computer: java pj2 jar=jarfile.jar MaximumCliqueSequential <source_file_path>");
    }
}
