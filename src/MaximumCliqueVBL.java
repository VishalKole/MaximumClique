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
    public void writeOut(OutStream outStream) throws IOException {

    }

    @Override
    public void readIn(InStream inStream) throws IOException {

    }

    @Override
    public void set(Vbl vbl) {

    }

    @Override
    public void reduce(Vbl vbl) {

    }
}
