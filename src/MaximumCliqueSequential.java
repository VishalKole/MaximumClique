import java.util.HashSet;
import java.util.Iterator;

import edu.rit.pj2.Task;

public class MaximumCliqueSequential extends Task {
    static HashSet[] graph;
    static int size = 0;
    static HashSet<Integer> maximum;

    public void main(String[] s) throws Exception {
        if(strings.length < 1){
            System.out.println("Not enough arguments");
            usage();
            terminate(1);
        }
        CreateGraph g = new CreateGraph(strings[0]);

        try {
            graph = g.GenerateGraph();
            HashSet<Integer> P = g.getHashSet();
            BronKerbosch1(new HashSet<Integer>(), P, new HashSet<>());

            System.out.println("Vertices in the maximum clique: \n");
            for (Integer I : maximum) {
                System.out.print(I + " ");
            }

            System.out.println("\n");
            System.out.println("Maximum Clique Size: " + size);


        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void BronKerbosch1(HashSet<Integer> R, HashSet<Integer> P, HashSet<Integer> X) {

        if (P.isEmpty() && X.isEmpty()) {
            if (size < R.size()) {
                maximum = (HashSet<Integer>) R.clone();
                size = R.size();
            }
        }

        while (!P.isEmpty()) {
            Integer i;
            {
                Iterator<Integer> itr = P.iterator();
                i = itr.next();
            }

            HashSet<Integer> R2 = (HashSet<Integer>) R.clone();
            HashSet<Integer> P2 = (HashSet<Integer>) P.clone();
            HashSet<Integer> X2 = (HashSet<Integer>) X.clone();
            P2.retainAll(graph[i]);
            X2.retainAll(graph[i]);
            R2.add(i);
            BronKerbosch1(R2, P2, X2);
            P.remove(i);
            X.add(i);
        }
    }
	
    private static void usage(){
        System.out.println("Usage on non cluster: java pj2 MaximumCliqueSequential <source_file_path> \n" + "Usage on cluster computer: java pj2 jar=jarfile.jar MaximumCliqueSequential <source_file_path>");
    }
}
