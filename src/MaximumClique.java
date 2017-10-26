import java.util.HashSet;
import java.util.Iterator;

public class MaximumClique {
    static HashSet[] graph;
    static int size = 0;
    static HashSet<Integer> maximum;

    public static void main(String[] s) {

        //
        //CreateGraph g = new CreateGraph("D:\\RIT\\SEM3\\Parallel Prog\\project\\4N4E.txt");
        CreateGraph g = new CreateGraph("D:\\RIT\\SEM3\\Parallel Prog\\project\\200N987E.txt");

        try {
            graph = g.GenerateGraph();
            HashSet<Integer> P = g.getHashSet();
            BronKerbosch1(new HashSet<Integer>(), P, new HashSet<Integer>());

            for (Integer I : maximum) {
                System.out.print(I + " ");
            }

            System.out.println("\\n"+size);


        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void BronKerbosch1(HashSet<Integer> R, HashSet<Integer> P, HashSet<Integer> X) {

        if (P.isEmpty() && X.isEmpty()) {
            if (size < R.size()) {
                maximum = (HashSet) R.clone();
                size = R.size();
            }

        }

        while (!P.isEmpty()) {
            Integer i;
            {
                Iterator<Integer> itr = P.iterator();
                i = itr.next();
            }

            HashSet<Integer> R2 = (HashSet) R.clone();
            HashSet<Integer> P2 = (HashSet) P.clone();
            HashSet<Integer> X2 = (HashSet) X.clone();
            P2.retainAll(graph[i]);
            X2.retainAll(graph[i]);
            R2.add(i);
            BronKerbosch1(R2, P2, X2);
            P.remove(i);
            X.add(i);

        }
    }
}

