import edu.rit.io.InStream;
import edu.rit.io.OutStream;
import edu.rit.pj2.Tuple;
import edu.rit.util.BitSet;

import java.io.IOException;
import java.util.HashSet;

public class BKConfig extends Tuple {

    HashSet<Integer>[] R;
    HashSet<Integer>[] P;
    HashSet<Integer>[] X;

    public BKConfig()
    {

    }

    @Override
    public void writeOut(OutStream outStream) throws IOException {
        outStream.writeObject(R);
        outStream.writeObject(P);
        outStream.writeObject(X);
    }

    @Override
    public void readIn(InStream inStream) throws IOException {
        this.R = (HashSet<Integer>[]) inStream.readObject();
        this.P = (HashSet<Integer>[]) inStream.readObject();
        this.X = (HashSet<Integer>[]) inStream.readObject();
    }
}
