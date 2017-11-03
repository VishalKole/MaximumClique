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
        CreateGraph g = new CreateGraph("./res/200N987E.txt");
        graph = g.GenerateGraph();

        MaximumCliqueVBL masterReducer = new MaximumCliqueVBL();

        // Getting all configurations in an Array
        configs = new CreateBKConfigs(graph).getConfigs();

        parallelFor(0, configs.length - 1).exec(new Loop() {

            MaximumCliqueVBL thrReducer;
            BronKerbosch algo;
            BKConfig config;

            @Override
            public void start() throws Exception {
                thrReducer = threadLocal(masterReducer);
                algo = new BronKerbosch(graph);
            }

            @Override
            public void run(int i) throws Exception {
                config = configs[i];
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
