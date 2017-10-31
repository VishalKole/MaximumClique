import edu.rit.pj2.Task;
import edu.rit.pj2.tuple.ObjectArrayTuple;

import java.util.HashSet;

public class CreateTuples extends Task {

    HashSet[] graph;

    @Override
    public void main(String[] strings) throws Exception {

        graph = readTuple(new ObjectArrayTuple<HashSet>()).item;
        for (int i = 0; i < graph.length; ++i) {
            BKConfig newConfig = new BKConfig();

        }

    }
}
