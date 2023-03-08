package pl.edu.pw.ee.services;

import java.io.File;
import java.io.FileNotFoundException;

public interface TransmisionInterface {

    public void transmision(File file) throws FileNotFoundException;
}
