import edu.rit.pj2.vbl.StringVbl;
import edu.rit.pj2.Job;
import edu.rit.pj2.tuple.ObjectArrayTuple;
import java.util.HashSet;

public class MaximumCliqueClusterMassivelyParallel2 extends Job {
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
        int vertices = g.getNumberOfVertices();

        //Eliminating tuple requirement by generating graphs locally
        //putTuple(9, new ObjectArrayTuple<HashSet>(graph));
        //Placing filename in tuple space for the workers
        StringVbl filename = new StringVbl(strings[0]);
        putTuple(1, filename);

        // Setting schedules and calling workers
        masterSchedule(guided);
        masterChunk(1);
        masterFor(0, (vertices*(vertices-1)) -1, WorkerTask2.class);
        rule().atFinish().task(ReduceTask.class);

    }

    private static void usage(){
        System.out.println("Usage on cluster computer: java pj2 jar=jarfile.jar MaximumCliqueClusterMassivelyParallel <source_file_path>");
    }
}
