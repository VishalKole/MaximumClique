import edu.rit.io.InStream;
import edu.rit.io.OutStream;
import edu.rit.pj2.Tuple;
import edu.rit.pj2.Vbl;

import java.io.IOException;
import java.util.HashSet;

public class MaximumCliqueVBL extends Tuple implements Vbl {

    int size;
    HashSet<Integer> maximum;

    MaximumCliqueVBL() {
        this.size = 0;
        this.maximum = new HashSet<>();
    }

    @Override
    public Object clone() {
        try {
            MaximumCliqueVBL vbl = (MaximumCliqueVBL) super.clone();
            vbl.size = this.size;
            vbl.maximum = (HashSet<Integer>) this.maximum.clone();
            return vbl;
        } catch (Exception ex) {
            throw new RuntimeException("GraphRadiusVBL clone error");
        }
    }


    @Override
    public void writeOut(OutStream outStream) throws IOException {
        outStream.writeLong(size);
        outStream.writeObject(maximum);
    }

    @Override
    public void readIn(InStream inStream) throws IOException {
        this.size = inStream.readInt();
        this.maximum = (HashSet<Integer>) inStream.readObject();
    }

    @Override
    public void set(Vbl vbl) {
        MaximumCliqueVBL VBLvar = (MaximumCliqueVBL) vbl;
        this.size = VBLvar.size;
        this.maximum = VBLvar.maximum;
    }

    @Override
    public void reduce(Vbl vbl) {
        MaximumCliqueVBL convVBL = (MaximumCliqueVBL) vbl;
        if (this.size < convVBL.size) {
            this.size = convVBL.size;
            this.maximum = convVBL.maximum;
        }
    }

    public void reduce(int size, HashSet<Integer> hset) {
        if (this.size < size) {
            this.size = size;
            this.maximum = hset;
        }
    }
}