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

/**
 * This class creates a graph based on the input string and generates the graph
 * that needs to be processed.
 *
 * @author  Vishal Kole, Srinath Obla, Akshay Sharma
 * @version 1.0
 */
public class CreateGraph {

    //Declaration of fields.
    private String path;
    private int v, e;

    /**
     * This function sets the path of the file.
     *
     * @param s  The file path.
     */
    CreateGraph(String s) {
        this.path = s;
    }

    /**
     * This function generates the graph if it is associated with the GraphSpec
     * interface.
     *
     * @param gs The graphspec.
     * @return   Returns the graph.
     */
    public HashSet<Integer>[] GenerateGraphSpecGraph(GraphSpec gs){

        HashSet<Integer>[] g = new HashSet[gs.V()];

        for (int i = 0; i < gs.V(); i++){
            g[i] = new HashSet<Integer>();}

        Edge e = new Edge();

        //Add every edge to the IntList variable list and to the other vertex list as well.
        //Do it for all the edges.
        for (int i = 0; i < gs.E(); i++) {
            gs.nextEdge(e);
            g[e.v1].add(e.v2);
            g[e.v2].add(e.v1);
        }

        return g;
    }

    /**
     * This function chooses the function to use in order to generate the graph
     * based on the argument.
     *
     * @return            The generated graph.
     * @throws Exception  Throws all Exceptions.
     */
    public HashSet<Integer>[] GenerateGraph() throws Exception {

        //If GraphSpec.
        if(path.substring(0,13).equals("CompleteGraph")){
            GraphSpec gs = (GraphSpec) Instance.newInstance(path);
            this.v=gs.V();
            this.e = gs.E();
            return GenerateGraphSpecGraph(gs);

        }
        //Otherwise.
        else return this.GenerateGraph1();
    }

    /**
     * This function generates the graph if the argument is a file path
     * to a text file.
     *
     * @return            The graph.
     * @throws Exception  throws all Exceptions.
     */
    public HashSet<Integer>[] GenerateGraph1() throws Exception {
        Scanner sc;

        sc = new Scanner(new File(path));

        this.v = (sc.nextInt());
        HashSet<Integer>[] g = new HashSet[v];

        //Create all the elements.
        for (int i = 0; i < v; i++){
            g[i] = new HashSet<Integer>();}

        this.e = sc.nextInt();

        //Create the neighbours.
        for (int i = 0; i < e; i++) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            g[a - 1].add(b - 1);
            g[b - 1].add(a - 1);
        }
        return g;
    }

    /**
     * This function returns the number of vertices in the graph.
     *
     * @return  The number of vertices present in the graph.
     */
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

    /**
     * This function returns the number of vertices if it is in a text file.
     *
     * @return The number of vertices present.
     */
    private int vertices(){

        Scanner sc;

        //Catch potential exceptions.
        try {
            sc = new Scanner(new File(path));
            this.v = (sc.nextInt());
            sc.close();
        }
        catch(Exception e){}

        return this.v;
    }

    /**
     * This function returns the hash set.
     *
     * @return The hash set.
     */
    public HashSet<Integer> getHashSet(){
        HashSet<Integer> temp = new HashSet<>();
        for (int i = 0; i < v; i++)
            temp.add(i);

        return temp;
    }

}
