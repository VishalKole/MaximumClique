# Computing Maximum Cliques in Parallel

### Topic Overview
In an undirected graph, a clique is a subgraph which is complete, that is, every vertex in the
subgraph is connected to every other vertex. A maximum clique is the largest clique in the entire
graph. Maximum cliques are widely used to analyse graphs in fields such as bioinformatics, food
webs, communication networks and even chemistry. This is generally done by finding all the
maximal cliques in graph. A maximal clique in is a clique which can no longer be augmented by
adding another neighbouring vertex, that is, it isn’t a subset of a larger clique. Our goal is to find the
maximum clique of a graph in reasonable time using parallel computing.

### Description of the Computational Problem Solved
Cliques are a subset of vertices present in an undirected graph such that each vertex is directly
connected to all the other vertices in the set, thereby making it a complete graph.

<img src="https://upload.wikimedia.org/wikipedia/commons/d/d0/VR_complex.svg" height="300" width="300">
Source - wikipedia.org/wiki/Clique_(graph_theory)

In the above figure, there are several cliques present, but the light blue polygons are all maximal
cliques and the dark blue polygons are maximum cliques.
Maximal clique are cliques which cannot be extended by including another adjacent vertex of the
graph in the set. Some authors define cliques to be maximal by definition. Maximal cliques should
not be confused with maximum cliques. A Maximum clique is a clique in a graph such that there
cannot be no clique with more vertices in it. This is also called the clique number of a graph. A
maximum clique is always maximal, the converse is not always true.

<img src="https://github.com/VishalKole/MaximumClique/blob/master/images/example%201.PNG" height="300" width="700">

In the figure given above the vertices on the left form a maximal clique while on the right form
maximum clique in the example.
Finding maximum clique in graphs is a known NP complete problem. Finding maximum cliques can
give important insights in the graph and could be very useful. Clique problem has been in the graph
theory domain for years now and there are several algorithms to find the maximum clique in the
graphs. Although, due to the computational complexity it is impossible to run a sequential algorithm
on a large dataset and expect a reasonable runtime. We tried to reduce the total runtime to
compute the maximum clique in a graph with parallel computing. Bron-Kerbosch was modified to
run in parallel and solve the problem to find the maximum clique in parallel.

### Design and Operation of the Sequential Program
The Bron Kerbosch algorithm is a recursive implementation to find the maximum cliques in a
graph. The algorithm uses three sets of vertices to build a clique step by step. The sets are named **R**, **P** and **X** and contain the following vertices:
* **R**: The set of vertices that are part of the current maximum clique,
* **P**: The set of vertices that are candidates for **R**,
* **X**: The set of vertices that are excluded for the current maximum clique.
Below is the pseudocode that described the Bron-Kerbosch algorithm:
```
   BronKerbosch1(R, P, X):
       if P and X are both empty:
           report R as a maximal clique
       for each vertex v in P:
           BronKerbosch1(R ⋃ {v}, P ⋂ N(v), X ⋂ N(v))
           P := P \ {v}
           X := X ⋃ {v}
```

In the first call to the algorithm, '**R** and **X** are empty; The current maximum clique is yet to be found
and there are no vertices to exclude. **P** contains all the vertices of the graph. For every vertex in **P**,
it will select it as a candidate **v**, and place it in **R**. It then only retains those vertices in **P** which are
the neighbors of the **v**. This is because a vertex not connected to **v** cannot be part of the proposed
maximum clique in **R**. After a recursive call is made with the new sets, **v** is removed from **P** and it
added to the excluded set **v** When there are no more candidates remaining and there no more
vertices in **X**, **R** is reported to be the maximum clique.

### Design and Operation of the Parallel Program
The Bron-Kerbosch works on by computing the sets R, P and X and checking for the cliques while
computing on these sets. We could visualize the recursion tree as given below, where every thread
explores through a subtree of the recursive tree searching for the cliques and reports the maximum
at the end after the reducing the individual results.

<img src="https://github.com/VishalKole/MaximumClique/blob/master/images/parallel%20example.PNG" height="300" width="700">

We send these configurations to each core or the worker to start the processing of the algorithm.
Each of the core then computes on the configuration it has been provided with and reports back
the maximal clique it has found in recursive call with the algorithm.

<img src="https://github.com/VishalKole/MaximumClique/blob/master/images/thread%20exe.PNG" height="300" width="700">

To explore the program’s speedup, we had constructed the algorithm to work on both, a multicore
and cluster platform. Since there generally would be more configurations than number of threads,
every thread is instructed to explore a set of configurations. In our first implementation, the
configurations were constructed as a preprocessing step. The multicore program had these
configurations stored as a common datastore for all the threads. The nodes of a cluster received
these configurations in the form of tuples sent by the master node. Improvements in the program
structure eliminated the time taken to transport the tuples; a procedure was written to compute a
configuration based on the selected vertex.
We started with the configuration creation in the shared memory, so the multicore program was our
first preference to work with. But since then we eliminated the need to create the configurations insequential manner before the execution of the parallel part, we created a multi-cluster program to
have good flexibility with adding more nodes and architecture if needed.
As we explained in presentation - 4, our initial parallel multicore and cluster implementations ran
accurately and got the right results but failed to give good results when it came to scaling. So we
decided to implement a variant, which we termed version 2, for both multicore and cluster. In
version 1, we explored one level deep for each vertex in the graph. It became apparent that this
was leading to worse speed up that expected, since the tree produced during the algorithm’s
execution was always deeper for certain nodes. In order to remedy this (at least partially), version 2
went two levels deep into the graph. As can be seen from the results below, this end up producing
significantly better strong scaling results. Unfortunately, weak scaling remained the same.

### Developer's Manual

1. You will require Java version 1.8 or higher to compile the source code.
2. Download the **pj2** library from https://www.cs.rit.edu/~ark/pj2.shtml
3. Set the classpath to pj2 library by using command:
export CLASSPATH=.:<PATH_TO_PJ2_JAR>
4. Change the directory to the src from the project folder
5. Compile all the files using Java compiler:
javac *.java
6. This step is to execute the program in cluster only: create a jar file with the generated
classes. To create the jar file, execute:
jar cf prj.jar *.class
7. You are good to go!

### User's Manual

After downloading the project folder you will have all the graph resources in the ‘res’ folder.
#### Steps for cluster execution:
1. Run with:
```
java pj2 jar=pro.jar debug=makespan workers=<W> <FUNCTION> <RES>
```
* `<W>` = number of workers you want to create (1 and above)
* `<FUNCTION>` = **MaximumCliqueClusterMassivelyParallel** (version 1) `OR` = **MaximumCliqueClusterMassivelyParallel2** (version 2)
* `<RES>` = one of the test file from the RES folder with the complete address to the directory `OR` = CompleteGraph(<K>), `<K>`  is the number of connected vertices you want to create in the graph.
  
#### Steps for multi-core execution:
1. Run with:
```
java pj2 debug=makespan cores=<C> <FUNCTION> <RES>
```
* `<C>` = number of workers you want to create (1 and above)
* `<FUNCTION>` = **MaximumCliqueMulticoreParallel** (version 1) `OR` = **MaximumCliqueMulticoreParallel2** (version 2)
* `<RES>` = one of the test file from the RES folder with the complete address to the Directory `OR` = CompleteGraph(<K>), `<K>` is the number of connected vertices you want to create in the graph.

### Strong Scaling Performance Data
As can be seen from the graphs below, the efficiency drops off quite rapidly and there is hardly any
improvement in run time even after increasing the number of cores.

#### Multicore (V1)
<img src="https://github.com/VishalKole/MaximumClique/blob/master/images/SS%20v1.PNG" height="500" width="500">

#### Cluster (V1)
Similarly, as can be seen from the graphs below, the efficiency drops off quite rapidly here as well,
and there is hardly any significant improvement in run time even after increasing the number of
ndos. But the the performance seems to be more uniform across different data sets.

<img src="https://github.com/VishalKole/MaximumClique/blob/master/images/Mc%20v2.PNG" height="500" width="500">

#### Multicore (V2)
As can be clearly seen, the efficiency and speedups are significantly better here compared to V1.
But the overall run times have gone up. There seems to have been a trade off of run time vs speed
up.
<img src="https://github.com/VishalKole/MaximumClique/blob/master/images/MC%20v3.PNG" height="500" width="500">

#### Cluster (V2)
Similar to the multicore graphs above, cluster v2 is a significant improvement over cluster v1 in
terms of efficiency and speed ups achieved. The cost again seems to be present in the form of run
times.
<img src="https://github.com/VishalKole/MaximumClique/blob/master/images/v4.PNG" height="500" width="500">

#### Strong Scaling Results
<img src="https://github.com/VishalKole/MaximumClique/blob/master/images/SS%20vals.PNG" height="500" width="500">
