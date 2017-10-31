import edu.rit.pj2.Job;
import edu.rit.pj2.tuple.EmptyTuple;
import edu.rit.pj2.tuple.ObjectArrayTuple;

import java.util.HashSet;

public class MaximumCliqueParallel extends Job {
    HashSet[] graph;
    HashSet<Integer> maximum;

    @Override
    public void main(String[] strings) throws Exception {
        CreateGraph g = new CreateGraph("./res/4N4E.txt");
        graph = g.GenerateGraph();

        putTuple(9, new ObjectArrayTuple<HashSet>(graph));

        masterSchedule(guided);
        masterFor(0, this.graph.length - 1, WorkerTask.class);
        rule().atStart().task(CreateTuples.class);
        rule().atFinish().task(ReduceTask.class);

        putTuple(new EmptyTuple());

    }
}
