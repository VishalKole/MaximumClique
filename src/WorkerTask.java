import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;
import edu.rit.pj2.TupleListener;
import edu.rit.pj2.tuple.EmptyTuple;
import edu.rit.pj2.tuple.ObjectArrayTuple;

import java.util.HashSet;

public class WorkerTask extends Task {

    HashSet[] graph;
    boolean isMainJobDone;
    boolean areAllBKTuplesProcessed;
    MaximumCliqueVBL reductionVBL;

    @Override
    public void main(String[] strings) throws Exception {

        reductionVBL = new MaximumCliqueVBL();
        graph = readTuple(new ObjectArrayTuple<HashSet>()).item;


        ///


        workerFor().schedule(guided).exec(new Loop() {

            MaximumCliqueVBL thrreductionVBL;
            BronKerbosch algo;
            BKConfig state;

            public void start() {
                thrreductionVBL = threadLocal(reductionVBL);
                algo = new BronKerbosch(graph);

            }
            @Override
            public void run(int i) throws Exception {
                state = takeTuple((new BKConfig()));
                algo.runBronKerbosch(state);
                thrreductionVBL.reduce(algo.size, algo.maximum);
            }
        });

        putTuple(reductionVBL);
    }
}