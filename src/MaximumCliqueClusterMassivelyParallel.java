import edu.rit.pj2.Job;
import edu.rit.pj2.tuple.ObjectArrayTuple;
import java.util.HashSet;

public class MaximumCliqueClusterMassivelyParallel extends Job {
    HashSet[] graph;

    @Override
    public void main(String[] strings) throws Exception {
        CreateGraph g = new CreateGraph("./res/Test2.txt");
        graph = g.GenerateGraph();

        putTuple(9, new ObjectArrayTuple<HashSet>(graph));

        masterSchedule(guided);
        masterChunk(1);
        masterFor(0, this.graph.length - 1, WorkerTask.class);
        rule().atFinish().task(ReduceTask.class);

    }
}
