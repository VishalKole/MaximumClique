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
    }

    @Override
    public Object clone() {
        try {
            MaximumCliqueVBL vbl = (MaximumCliqueVBL) super.clone();
            vbl.size = this.size;
            vbl.maximum = (HashSet) this.maximum.clone();
            return vbl;
        } catch (Exception ex) {
            throw new RuntimeException("GraphRadiusVBL clone error");
        }
    }


    @Override
    public void writeOut(OutStream outStream) throws IOException {
        outStream.writeLong(size);
        for (Integer i : maximum)
            outStream.writeLong(i);
    }

    @Override
    public void readIn(InStream inStream) throws IOException {
        this.size = inStream.readInt();
        maximum.clear();
        for (int i = 0; i < size; i++)
            maximum.add(inStream.readInt());
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
            this.maximum.clear();
            for (Integer i : convVBL.maximum)
                maximum.add(i);
        }
    }

    public void reduce(int size, HashSet<Integer> hset) {
        if (this.size < size) {
            this.maximum.clear();
            for (Integer i : hset)
                maximum.add(i);
        }
    }
}
