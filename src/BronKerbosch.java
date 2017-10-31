
import java.util.HashSet;
import java.util.Iterator;

public class BronKerbosch {

    HashSet[] graph;
    int size = 0;
    HashSet<Integer> maximum;

    BronKerbosch(HashSet[] graph) {
        this.graph = graph;
    }

    public void runBronKerbosch(BKConfig configuration){
        runBronKerbosch(configuration.getR(), configuration.getP(), configuration.getX());
    }

    public void runBronKerbosch(HashSet<Integer> R, HashSet<Integer> P, HashSet<Integer> X) {

        if (P.isEmpty() && X.isEmpty()) {

            if (this.size < R.size()) {
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
            this.runBronKerbosch(R2, P2, X2);

            P.remove(i);
            X.add(i);
        }
    }
}
