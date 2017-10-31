import edu.rit.io.InStream;
import edu.rit.io.OutStream;
import edu.rit.pj2.Tuple;
import edu.rit.util.BitSet;

import java.io.IOException;

public class BKConfig extends Tuple {

    BitSet[] R;
    BitSet[] P;
    BitSet[] X;

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
        this.R = (BitSet[]) inStream.readObject();
        this.P = (BitSet[]) inStream.readObject();
        this.X = (BitSet[]) inStream.readObject();
    }
}
