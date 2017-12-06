import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;
import edu.rit.pj2.tuple.ObjectArrayTuple;

import java.util.HashSet;

public class WorkerTask2 extends Task {

    HashSet[] graph;

    MaximumCliqueVBL reductionVBL;

    /**
     *
     * @param strings
     * @throws Exception
     */
    @Override
    public void main(String[] strings) throws Exception {

        reductionVBL = new MaximumCliqueVBL();
        graph = readTuple(new ObjectArrayTuple<HashSet>()).item;

        workerFor().exec(new Loop() {

            MaximumCliqueVBL thrreductionVBL;
            BronKerbosch algo;

            public void start() {
                thrreductionVBL = threadLocal(reductionVBL);
                algo = new BronKerbosch(graph);
            }

            @Override
            public void run(int i) throws Exception {

                algo.runBronKerbosch(algo.createConfigForVertex2(i));
                thrreductionVBL.reduce(algo.size, algo.maximum);
            }
        });

        putTuple(reductionVBL);
    }

    protected static int coresRequired()
    {
        return 1;
    }
}