import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;

import java.util.HashSet;

/**
 *
 * Created by Srinath Obla on 03-11-2017.
 */
public class MaximumCliqueMulticoreParallel extends Task {


    private HashSet<Integer>[] graph;

    @Override
    public void main(String[] strings) throws Exception {
        if(strings.length < 1){
            System.out.println("Not enough arguments");
            usage();
            terminate(1);
        }
        CreateGraph g = new CreateGraph(strings[0]);
        graph = g.GenerateGraph();

        MaximumCliqueVBL masterReducer = new MaximumCliqueVBL();

        parallelFor(0, g.vertices() - 1).exec(new Loop() {

            MaximumCliqueVBL thrReducer;
            BronKerbosch algo;

            @Override
            public void start() throws Exception {
                thrReducer = threadLocal(masterReducer);
                algo = new BronKerbosch(graph);
            }

            @Override
            public void run(int i) throws Exception {

                algo.runBronKerbosch(algo.createConfigForVertex(i));
                thrReducer.reduce(algo.size, algo.maximum);
            }
        });

        System.out.println("Maximum Clique Size: " + masterReducer.size);
        System.out.println("\n");
        System.out.println("Vertices in the maximum clique: \n");
        for (Integer I : masterReducer.maximum) {
            System.out.print(I + " ");
        }

    }

    private static void usage(){
        System.out.println("Usage on non cluster: java pj2 MaximumCliqueMulticoreParallel <source_file_path> \n" +
                "Usage on cluster computer: java pj2 jar=jarfile.jar MaximumCliqueMulticoreParallel <source_file_path>");
    }
}
