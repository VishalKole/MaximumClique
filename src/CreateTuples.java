import com.intellij.vcs.log.Hash;
import edu.rit.pj2.Task;
import edu.rit.pj2.tuple.ObjectArrayTuple;

import java.util.HashSet;

public class CreateTuples extends Task {

    HashSet[] graph;

    @Override
    public void main(String[] strings) throws Exception {

        graph = readTuple(new ObjectArrayTuple<HashSet>()).item;
        HashSet<Integer> verticesCovered = new HashSet<>();
        HashSet<Integer> P = new HashSet<>();

        for (int i = 0; i < graph.length; ++i) {
            P.add(i);
        }

        for (int i = 0; i < graph.length; ++i) {


            //BKConfig newConfig = new BKConfig();
            //putTuple();

        }

    }
}
