import edu.rit.pj2.Job;
import edu.rit.pj2.tuple.ObjectArrayTuple;
import java.util.HashSet;

public class MaximumCliqueParallel extends Job {
    HashSet[] graph;

    @Override
    public void main(String[] strings) throws Exception {
        CreateGraph g = new CreateGraph("./res/Test1.txt");
        graph = g.GenerateGraph();

        putTuple(9, new ObjectArrayTuple<HashSet>(graph));

        masterSchedule(guided);
        masterFor(0, this.graph.length - 1, WorkerTask.class);
        rule().atFinish().task(ReduceTask.class);

    }
}
