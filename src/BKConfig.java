//******************************************************************************
//
// File:    BKConfig.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.io.InStream;
import edu.rit.io.OutStream;
import edu.rit.pj2.Tuple;

import java.io.IOException;
import java.util.HashSet;

/**
 * This class holds information regarding the configuration for each step
 * of the Bron-Kerbosch algorithm and functionality associated with it.
 * <P>
 *
 * @author
 * @version
 */
public class BKConfig extends Tuple {

    HashSet<Integer> R;
    HashSet<Integer> P;
    HashSet<Integer> X;

    /**
     * This is the default constructor for the class.
     */
    BKConfig() {
    }

    /**
     * This is the paramterized constructor for the class.
     *
     * @param r  Set R.
     * @param p  Set P.
     * @param x  Set X.
     */
    BKConfig(HashSet<Integer> r, HashSet<Integer> p, HashSet<Integer> x)
    {
        this.R = r;
        this.P = p;
        this.X = x;
    }

    /**
     * Writes the values of the object to the given out stream.
     *
     * @param outStream     The out stream
     * @throws IOException  Thrown if an I/O error occurs.
     */
    @Override
    public void writeOut(OutStream outStream) throws IOException {
        outStream.writeObject(R);
        outStream.writeObject(P);
        outStream.writeObject(X);
    }

    /**
     * Reads the values of object from the in stream.
     *
     * @param inStream      The in stream.
     * @throws IOException  Thrown if an IO error occurs.
     */
    @Override
    public void readIn(InStream inStream) throws IOException {
        this.R = (HashSet<Integer>) inStream.readObject();
        this.P = (HashSet<Integer>) inStream.readObject();
        this.X = (HashSet<Integer>) inStream.readObject();
    }

    /**
     * This function retrieves set P.
     *
     * @return  Set P.
     */
    public HashSet<Integer> getP() {
        return P;
    }

    /**
     * This function retrieves set X.
     *
     * @return  Set X.
     */
    public HashSet<Integer> getX() {
        return X;
    }

    /**
     * This function retrieves set R.
     *
     * @return  Set R.
     */
    public HashSet<Integer> getR() {
        return R;
    }
}