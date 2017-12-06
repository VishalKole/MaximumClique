import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;
import edu.rit.pj2.TupleListener;
import edu.rit.pj2.tuple.EmptyTuple;
import edu.rit.pj2.tuple.ObjectArrayTuple;

import edu.rit.pj2.vbl.StringVbl;
import java.util.HashSet;

public class WorkerTask extends Task {

    HashSet[] graph;
    boolean isMainJobDone;
    boolean areAllBKTuplesProcessed;

    MaximumCliqueVBL reductionVBL;

    @Override
    public void main(String[] strings) throws Exception {

        reductionVBL = new MaximumCliqueVBL();
	StringVbl filename = tryToReadTuple(new StringVbl());
	if(filename == null){
	    System.out.println(nodeName() + ": Could not load filename");
	    terminate(1);
	}
        
       	graph = new CreateGraph(filename.stringValue()).GenerateGraph();//readTuple(new ObjectArrayTuple<HashSet>()).item;
        HashSet<Integer> P = new HashSet<>();

        for (int i = 0; i < graph.length; ++i) {
            P.add(i);
        }

        workerFor().exec(new Loop() {

            MaximumCliqueVBL thrreductionVBL;
            BronKerbosch algo;
            BKConfig state;
            HashSet<Integer> cloneP;
            HashSet<Integer> R2;
            HashSet<Integer> X2;


            public void start() {
                thrreductionVBL = threadLocal(reductionVBL);
                algo = new BronKerbosch(graph);
                R2 = new HashSet<>();
                X2 = new HashSet<>();


            }
            @Override
            public void run(int i) throws Exception {
                System.out.println(i);
                System.out.flush();

                cloneP = (HashSet<Integer>) P.clone();
                cloneP.remove(i);
                cloneP.retainAll(graph[i]);

                for (int j = 0; j < i; ++j) {
                    cloneP.remove(j);
                }

                R2.clear();
                R2.add(i);

                X2.clear();
                for (int j = 0; j < i; ++j) {
                    X2.add(j);
                }

                X2.retainAll(graph[i]);

                state = new BKConfig(R2, cloneP, X2);

                algo.runBronKerbosch(state);
                thrreductionVBL.reduce(algo.size, algo.maximum);
            }
        });

        putTuple(reductionVBL);
    }
    
    protected static int coresRequired(){
   	return 1;
    }
}
