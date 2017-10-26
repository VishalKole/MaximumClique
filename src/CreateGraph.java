import java.util.*;
import java.io.*;

public class CreateGraph {
    String path;
    int v,e;

    CreateGraph(String s) {
        this.path = s;
    }

    public HashSet[] GenerateGraph() throws Exception {
        Scanner sc;


        // lolololol

            sc = new Scanner(new File(path));

        this.v = (sc.nextInt());
        HashSet<Integer>[] g = new HashSet[v];

        for(int i=0;i<v;i++)
            g[i]= new HashSet();

        this.e = sc.nextInt();
        for (int i = 0; i < e; i++) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            g[a-1].add(b-1);
            g[b-1].add(a-1);

        }

        return g;

    }

    public HashSet<Integer> getHashSet(){
        HashSet<Integer> temp = new HashSet<>();
        for(int i=0;i<v;i++)
            temp.add(i);

    return temp;
    }

}
