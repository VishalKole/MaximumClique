import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;

import java.util.HashSet;

/**
 *
 * Created by Srinath Obla on 03-11-2017.
 */
public class MaximumCliqueMulticoreParallel extends Task {


    private HashSet<Integer>[] graph;
    private BKConfig[] configs;

    @Override
    public void main(String[] strings) throws Exception {
        CreateGraph g = new CreateGraph("./res/Test1.txt");
        graph = g.GenerateGraph();

        MaximumCliqueVBL masterReducer = new MaximumCliqueVBL();

        // Getting all configurations in an Array
        //configs = new CreateBKConfigs(graph).getConfigs();

        HashSet<Integer> P = new HashSet<>();

        for (int i = 0; i < graph.length; ++i) {
            P.add(i);
        }

        parallelFor(0, graph.length - 1).exec(new Loop() {

            MaximumCliqueVBL thrReducer;
            BronKerbosch algo;
            BKConfig config;
            HashSet<Integer> cloneP;
            HashSet<Integer> R2;
            HashSet<Integer> X2;

            @Override
            public void start() throws Exception {
                thrReducer = threadLocal(masterReducer);
                algo = new BronKerbosch(graph);

                R2 = new HashSet<>();
                X2 = new HashSet<>();

            }

            @Override
            public void run(int i) throws Exception {

                cloneP = (HashSet<Integer>) P.clone();
                cloneP.remove(i);
                cloneP.retainAll(graph[i]);

                for (int j = 0; j < i; ++j) {
                    cloneP.remove(j);
                }

                R2.clear();
                R2.add(i);

                X2.clear();
                for (int j = 0; j < i; ++j) {
                    X2.add(j);
                }

                X2.retainAll(graph[i]);

                config = new BKConfig(R2, cloneP, X2);
                algo.runBronKerbosch(config);
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
}
