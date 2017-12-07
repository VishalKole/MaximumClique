//******************************************************************************
//
// File:    CreateGraph.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.util.Instance;
import java.util.*;
import java.io.*;

public class CreateGraph {
    private String path;
    private int v, e;


    CreateGraph(String s) {
        this.path = s;
    }

    public HashSet<Integer>[] GenerateGraphSpecGraph(GraphSpec gs){

        HashSet<Integer>[] g = new HashSet[gs.V()];

        for (int i = 0; i < gs.V(); i++){
            g[i] = new HashSet<Integer>();}

        Edge e = new Edge();

        //add every edge to the IntList variable list and to the other vertex list as well
        // do it for all the edges
        for (int i = 0; i < gs.E(); i++) {
            gs.nextEdge(e);
            g[e.v1].add(e.v2);
            g[e.v2].add(e.v1);
        }

        return g;
    }

    public HashSet<Integer>[] GenerateGraph() throws Exception {

        if(path.substring(0,13).equals("CompleteGraph")) return GenerateGraphSpecGraph((GraphSpec) Instance.newInstance(path));
        else return this.GenerateGraph1();
    }

    public HashSet<Integer>[] GenerateGraph1() throws Exception {
        Scanner sc;

        sc = new Scanner(new File(path));

        this.v = (sc.nextInt());
        HashSet<Integer>[] g = new HashSet[v];

        for (int i = 0; i < v; i++){
            g[i] = new HashSet<Integer>();}

        this.e = sc.nextInt();
        for (int i = 0; i < e; i++) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            g[a - 1].add(b - 1);
            g[b - 1].add(a - 1);
        }
        return g;
    }

    public int getNumberOfVertices(){
        int result=0;
        try {
            if (path.substring(0, 13).equals("CompleteGraph"))
                result = ((GraphSpec) Instance.newInstance(path)).V();
            else
                result = this.vertices();
        }
        catch(Exception e){}
        return result;
    }

    private int vertices(){

        Scanner sc;
        try {
        sc = new Scanner(new File(path));
        this.v = (sc.nextInt());
        sc.close();
        }
        catch(Exception e){}
        return this.v;
    }

    public HashSet<Integer> getHashSet(){
        HashSet<Integer> temp = new HashSet<>();
        for (int i = 0; i < v; i++)
            temp.add(i);

        return temp;
    }

}
