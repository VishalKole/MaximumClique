import edu.rit.pj2.Job;
import edu.rit.pj2.tuple.ObjectArrayTuple;
import java.util.HashSet;

public class MaximumCliqueClusterMassivelyParallel extends Job {
    HashSet[] graph;

    @Override
    public void main(String[] strings) throws Exception {
	if(strings.length < 1){
            System.out.println("Not enough arguments");
            usage();
            terminate(1);
        }
        //Creating graph here just for the length.
        CreateGraph g = new CreateGraph(strings[0]);
        graph = g.GenerateGraph();

        //Eliminating tuple requirement by generating graphs locally
        //putTuple(9, new ObjectArrayTuple<HashSet>(graph));

        masterSchedule(guided);
        masterChunk(1);
        masterFor(0, this.graph.length - 1, WorkerTask.class);
        rule().atFinish().task(ReduceTask.class);

    }
	
    private static void usage(){
        System.out.println("Usage on cluster computer: java pj2 jar=jarfile.jar MaximumCliqueClusterMassivelyParallel <source_file_path>");
    }
}
