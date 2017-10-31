import edu.rit.pj2.Task;
import edu.rit.pj2.Tuple;
import edu.rit.pj2.TupleListener;
import edu.rit.pj2.tuple.EmptyTuple;
import edu.rit.pj2.tuple.ObjectArrayTuple;

import java.util.HashSet;

public class WorkerTask extends Task {

    HashSet[] graph;
    boolean isMainJobDone;
    boolean areAllBKTuplesProcessed;

    @Override
    public void main(String[] strings) throws Exception {
        graph = readTuple(new ObjectArrayTuple<HashSet>()).item;

        addTupleListener(new TupleListener<EmptyTuple>(new EmptyTuple())
        {
            public void run(EmptyTuple tuple)
            {
                isMainJobDone = true;
            }
        });
    }
}
