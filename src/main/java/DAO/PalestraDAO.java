package DAO;

import Models.Palestra;

public class PalestraDAO  extends FileDAO<Palestra> {
    public PalestraDAO() {
        super("palestras.txt");
    }
    public PalestraDAO(String filename) {
        super(filename);
    }
}
