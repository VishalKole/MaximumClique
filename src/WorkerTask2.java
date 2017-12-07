//******************************************************************************
//
// File:    WorkerTask2.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;
import edu.rit.pj2.TupleListener;
import edu.rit.pj2.tuple.EmptyTuple;
import edu.rit.pj2.tuple.ObjectArrayTuple;

import edu.rit.pj2.vbl.StringVbl;
import java.util.HashSet;

public class WorkerTask2 extends Task {

    HashSet[] graph;

    MaximumCliqueVBL reductionVBL;

    /**
     *
     * @param strings
     * @throws Exception
     */
    @Override
    public void main(String[] strings) throws Exception {

        reductionVBL = new MaximumCliqueVBL();
        StringVbl filename = tryToReadTuple(new StringVbl());
        if(filename == null){
            System.out.println(nodeName() + ": Could not load filename");
            terminate(1);
        }

        graph = new CreateGraph(filename.stringValue()).GenerateGraph();
        HashSet<Integer> P = new HashSet<>();

        for (int i = 0; i < graph.length; ++i) {
            P.add(i);
        }

        workerFor().exec(new Loop() {

            MaximumCliqueVBL thrreductionVBL;
            BronKerbosch algo;

            public void start() {
                thrreductionVBL = threadLocal(reductionVBL);
                algo = new BronKerbosch(graph);
            }

            @Override
            public void run(int i) throws Exception {

                algo.runBronKerbosch(algo.createConfigForVertex2(i));
                thrreductionVBL.reduce(algo.size, algo.maximum);
            }
        });

        putTuple(reductionVBL);
    }

    protected static int coresRequired(){
        return 1;
    }
}
