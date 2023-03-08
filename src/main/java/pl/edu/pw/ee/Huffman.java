package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import pl.edu.pw.ee.services.Utils;
import pl.edu.pw.ee.services.TransmisionInterface;

public class Huffman {

    public int huffman(String pathToRootDir, boolean compress) {

        TransmisionInterface transmisionInterface;

        if (compress == true) {
            transmisionInterface = new Compress();
        } else {
            transmisionInterface = new Decompress();
        }

        File[] folderFiles = Utils.ValidateInput(pathToRootDir);

        for (File f : folderFiles) {
            try {
                transmisionInterface.transmision(f);
            } catch (FileNotFoundException ex) {
                throw new IllegalArgumentException("File was not found");
            }
        }
        return compress ? Utils.getBits() : Utils.getChars();

    }
}
