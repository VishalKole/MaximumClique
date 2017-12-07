//******************************************************************************
//
// File:    MaximumCliqueParallel.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************


import edu.rit.pj2.Job;
import edu.rit.pj2.tuple.ObjectArrayTuple;
import java.util.HashSet;

public class MaximumCliqueParallel extends Job {
    HashSet[] graph;
    HashSet<Integer> maximum;

    @Override
    public void main(String[] strings) throws Exception {

        if(strings.length < 1){
            System.out.println("Not enough arguments");
            usage();
            terminate(1);
        }
        CreateGraph g = new CreateGraph(strings[0]);

        graph = g.GenerateGraph();

        putTuple(9, new ObjectArrayTuple<HashSet>(graph));

        CreateTuples2 ct = new CreateTuples2(graph);
        ct.main(new String[1]);

        masterSchedule(guided);
        masterFor(0, this.graph.length - 1, WorkerTask.class);
        //rule().atStart().task(CreateTuples.class);
        rule().atFinish().task(ReduceTask.class);

        //putTuple(new EmptyTuple());

    }

    public class CreateTuples2 {//extends Task {

        HashSet[] graph;

        CreateTuples2(HashSet[] graph) {
            this.graph = graph;
        }

        public void main(String[] strings) throws Exception {

            //graph = readTuple(new ObjectArrayTuple<HashSet>()).item;
            HashSet<Integer> verticesCovered = new HashSet<>();
            HashSet<Integer> P = new HashSet<>();

            for (int i = 0; i < graph.length; ++i) {
                P.add(i);
            }

            for (int i = 0; i < graph.length; ++i) {
                HashSet<Integer> cloneP = (HashSet<Integer>) P.clone();
                cloneP.remove(i);
                cloneP.retainAll(graph[i]);
                cloneP.removeAll(verticesCovered);

                HashSet<Integer> R2 = new HashSet<>();
                R2.add(i);

                HashSet<Integer> X2 = new HashSet<>();
                X2.addAll(verticesCovered);
                X2.retainAll(graph[i]);

                BKConfig newConfig = new BKConfig(R2, cloneP, X2);
                putTuple(newConfig);

                verticesCovered.add(i);

            }

        }
    }

    private static void usage(){
        System.out.println("Usage on cluster computer: java pj2 jar=jarfile.jar MaximumCliqueParallel <source_file_path>");
    }
}
