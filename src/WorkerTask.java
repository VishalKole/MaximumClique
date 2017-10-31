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

        addTupleListener(new TupleListener<EmptyTuple>(new EmptyTuple()) {
            public void run(EmptyTuple tuple) {
                isMainJobDone = true;
            }
        });


        workerFor().schedule(guided).exec(new Loop() {

            MaximumCliqueVBL thrreductionVBL;
            BronKerbosch algo = new BronKerbosch(graph);
            BKConfig state;

            @Override
            public void run(int i) throws Exception {
                state = readTuple((new BKConfig()));
                algo.runBronKerbosch(state.R, state.P, state.X);
                thrreductionVBL.reduce(algo.size, algo.maximum);
            }
        });

        putTuple(reductionVBL);
    }
}
