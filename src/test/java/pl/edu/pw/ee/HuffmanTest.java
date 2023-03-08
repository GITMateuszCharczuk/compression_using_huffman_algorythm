package pl.edu.pw.ee;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HuffmanTest {

    @Test(expected = IllegalArgumentException.class)
    public void doesFileExistTest() {

        Huffman huf = new Huffman();
        huf.huffman("exampleFolderNameNotExactlyTooLongNotToMakeTheProgramCrashUnintentionally", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noFilesInFolderTest() {

        Huffman huf = new Huffman();
        huf.huffman("testDir", true);
        huf.huffman("testDir", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void undeadableFilesInFolderTest() {

        Huffman huf = new Huffman();
        huf.huffman("testDir2", true);
        huf.huffman("testDir2", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyFilesInFolder() {

        Huffman huf = new Huffman();
        huf.huffman("testDir0", true);
        huf.huffman("testDir0", false);
    }

    @Test
    public void oneLetterSequenceInFile() {

        Huffman huf = new Huffman();
        huf.huffman("testDir1", true);
        huf.huffman("testDir1", false);
        assertEqualsToFile("testDir1");
    }

    @Test
    public void Folder3TestCompressAndDecompress() {

        Huffman huf = new Huffman();
        huf.huffman("testDir3", true);
        huf.huffman("testDir3", false);
        assertEqualsToFile("testDir3");
    }

    @Test
    public void Folder4TestCompressAndDecompress() {
        Huffman huf = new Huffman();
        huf.huffman("testDir4", true);
        huf.huffman("testDir4", false);
        assertEqualsToFile("testDir4");
    }

    @Test
    public void Folder5TestCompressAndDecompress() {
        Huffman huf = new Huffman();
        huf.huffman("testDir5", true);
        huf.huffman("testDir5", false);
        assertEqualsToFile("testDir5");
    }

    @Test
    public void Folder6TestCompressAndDecompress() {
        Huffman huf = new Huffman();
        huf.huffman("testDir6", true);
        huf.huffman("testDir6", false);
        assertEqualsToFile("testDir6");
    }

    public void assertEqualsToFile(String pathToRootDir) {
        try {
            long isEqual = Files.mismatch(Paths.get(pathToRootDir + "/test.txt"), Paths.get(pathToRootDir + "/output.txt"));
            assertEquals(-1, isEqual);
        } catch (IOException ex) {
            assert false;
        }
    }

}
