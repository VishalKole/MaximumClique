//******************************************************************************
//
// File:    ReduceTask.java
//
// This Java source file is part of the Team research investigation for the
// partial completion of the coursework
//
//******************************************************************************

import edu.rit.pj2.Task;

public class ReduceTask extends Task {
    @Override
    public void main(String[] strings) throws Exception {
        MaximumCliqueVBL VBL = new MaximumCliqueVBL();

        MaximumCliqueVBL templateVBL = new MaximumCliqueVBL();

        MaximumCliqueVBL StorageVBL;

        while ((StorageVBL = tryToTakeTuple(templateVBL)) != null)
            VBL.reduce(StorageVBL);

        System.out.println("Maximum Clique Size: " + VBL.size);
        System.out.println("\n");
        System.out.println("Vertices in the maximum clique: \n");
        for (Integer I : VBL.maximum) {
            System.out.print(I + " ");
        }
    }
}