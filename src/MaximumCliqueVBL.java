//******************************************************************************
//
// File:    MaximumCliqueVBL.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.io.InStream;
import edu.rit.io.OutStream;
import edu.rit.pj2.Tuple;
import edu.rit.pj2.Vbl;

import java.io.IOException;
import java.util.HashSet;

/**
 * This class helps to read and publish tuples.
 *
 * @author  Vishal Kole, Srinath Obla, Akshay Sharma
 * @version 1.0
 */
public class MaximumCliqueVBL extends Tuple implements Vbl {

    int size;
    HashSet<Integer> maximum;

    /**
     * This is the default constructor for this class.
     */
    MaximumCliqueVBL() {
        this.size = 0;
        this.maximum = new HashSet<>();
    }

    /**
     * This function creates a copy of the object.
     *
     * @return  Returns a copy.
     * @throws RuntimeException Throws RuntimeException.
     */
    @Override
    public Object clone() {
        try {
            MaximumCliqueVBL vbl = (MaximumCliqueVBL) super.clone();
            vbl.size = this.size;
            vbl.maximum = (HashSet<Integer>) this.maximum.clone();
            return vbl;
        }
        catch (Exception ex) {
            throw new RuntimeException("GraphRadiusVBL clone error");
        }
    }

    /**
     * This function writes out the object to tuple space.
     *
     * @param outStream     The stream to write out to.
     * @throws IOException  Throws IOException.
     */
    @Override
    public void writeOut(OutStream outStream) throws IOException {
        outStream.writeLong(size);
        outStream.writeObject(maximum);
    }

    /**
     * This function reads in the object from tuple space.
     *
     * @param inStream      The stream to read in from.
     * @throws IOException  Throws IOException.
     */
    @Override
    public void readIn(InStream inStream) throws IOException {
        this.size = inStream.readInt();
        this.maximum = (HashSet<Integer>) inStream.readObject();
    }

    /**
     * This function sets the fields as required.
     *
     * @param vbl  The object to be used to set the values from.
     */
    @Override
    public void set(Vbl vbl) {
        MaximumCliqueVBL VBLvar = (MaximumCliqueVBL) vbl;
        this.size = VBLvar.size;
        this.maximum = VBLvar.maximum;
    }

    /**
     * This function performs the reduction operation on the tuples.
     *
     * @param vbl  The object to be reduced.
     */
    @Override
    public void reduce(Vbl vbl) {
        MaximumCliqueVBL convVBL = (MaximumCliqueVBL) vbl;
        if (this.size < convVBL.size) {
            this.size = convVBL.size;
            this.maximum = convVBL.maximum;
        }
    }

    /**
     * This function performs the reduction operation when the fields of the
     * object are sent in.
     *
     * @param size  The size of the clique.
     * @param hset  The actual maximum clique so far.
     */
    public void reduce(int size, HashSet<Integer> hset) {
        if (this.size < size) {
            this.size = size;
            this.maximum = hset;
        }
    }
}