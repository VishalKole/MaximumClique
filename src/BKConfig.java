import edu.rit.io.InStream;
import edu.rit.io.OutStream;
import edu.rit.pj2.Tuple;

import java.io.IOException;
import java.util.HashSet;

public class BKConfig extends Tuple {

    HashSet<Integer> R;
    HashSet<Integer> P;
    HashSet<Integer> X;

    public BKConfig(HashSet<Integer> r, HashSet<Integer> p, HashSet<Integer> x)
    {
        this.R = r;
        this.P = p;
        this.X = x;
    }

    @Override
    public void writeOut(OutStream outStream) throws IOException {
        outStream.writeObject(R);
        outStream.writeObject(P);
        outStream.writeObject(X);
    }

    @Override
    public void readIn(InStream inStream) throws IOException {
        this.R = (HashSet<Integer>) inStream.readObject();
        this.P = (HashSet<Integer>) inStream.readObject();
        this.X = (HashSet<Integer>) inStream.readObject();
    }

    public HashSet<Integer> getP() {
        return P;
    }

    public HashSet<Integer> getX() {
        return X;
    }

    public HashSet<Integer> getR() {
        return R;
    }
}
