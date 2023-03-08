package pl.edu.pw.ee.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pl.edu.pw.ee.Node;
import pl.edu.pw.ee.StorageNode;

public class Utils {

    private static int bitsAfterCompression;

    private static int charsAfterDecompression;

    public static File[] ValidateInput(String pathToRootDir) {

        if (pathToRootDir == null) {

            throw new IllegalArgumentException("Input string can't be null!!!");
        }

        File folder = new File(pathToRootDir);
        File[] folderFiles = folder.listFiles();

        if (folderFiles == null && !folder.isDirectory()) {

            throw new IllegalArgumentException("Given file path must be a directory!!!");
        } else if (folderFiles.length == 0) {

            throw new IllegalArgumentException("Given dorectory is empty!!!");
        }

        for (File f : folderFiles) {

            if (!f.canRead()) {

                throw new IllegalArgumentException("Given file is unable to be read!!!");//sprawdz pisownie
            } else if (f.length() == 0) {

                throw new IllegalArgumentException("Given file is empty!!!");
            }
        }

        return folderFiles;
    }

    public static char[] getCharsFromFile(File inputFile, boolean compress) throws FileNotFoundException {

        String inputString = "";
        Scanner scanner = new Scanner(inputFile);
        scanner.useDelimiter("");

        while (scanner.hasNext()) {
            String nextChar = scanner.next();
            inputString += nextChar;
        }

        return inputString.toCharArray();
    }

    public static void baseTreeToFile(String parent, List<Node> baseTree) {

        File directory = new File(parent);
        char spacer = (char) 16;

        if (!directory.isDirectory()) {
            throw new RuntimeException("Something is wrong with output file!!!");
        }

        File treeOutputFile = new File(parent + "\\tree.txt");

        try {
            treeOutputFile = new File(parent + "\\tree.txt");
            treeOutputFile.createNewFile();

            FileWriter writer;

            writer = new FileWriter(treeOutputFile);

            for (Node n : baseTree) {
                writer.write(String.format("%c%c%d%c", n.getCharacter(), spacer, n.getFrequency(), spacer));
            }

            writer.close();
            treeOutputFile.setWritable(false);
        } catch (IOException ex) {

            throw new RuntimeException("Cannot write to file");
        }

    }

    public static void convertInputStringToFile(String parent, List<StorageNode> outputTree, char[] originalCharArr) {

        File directory = new File(parent);
        File byteCodeOutputFile;

        if (!directory.isDirectory()) {
            throw new RuntimeException("Something is wrong with output file!!!");
        }

        try {
            byteCodeOutputFile = new File(parent + "\\byte.txt");
            byteCodeOutputFile.createNewFile();
            FileWriter writer;
            writer = new FileWriter(byteCodeOutputFile);
            String finalBitsOutput = "";
            String bitsToConvert = "";
            int i = 1, k = 1;

            for (char c : originalCharArr) {

                for (StorageNode n : outputTree) {

                    if (n.getCharacter() == c) {

                        for (char ca : n.getByteCode().toCharArray()) {
                            bitsToConvert += ca;

                            if (i % 7 == 0) {
                                finalBitsOutput += bitsToConvert;
                                bitsToConvert = "";
                            }

                            i++;
                        }
                        break;
                    }
                }
            }

            int chowManyToAdd = 7 - ((finalBitsOutput.length() + bitsToConvert.length() + 3) % 7);
            String chowManyWasAddedByte = String.format("%" + 3 + "s", Integer.toBinaryString(chowManyToAdd)).replace(' ', '0');
            finalBitsOutput = chowManyWasAddedByte + finalBitsOutput;

            for (int j = 0; j < chowManyToAdd; j++) {
                finalBitsOutput += "0";
            }

            finalBitsOutput += bitsToConvert;
            bitsToConvert = "";

            for (char c : finalBitsOutput.toCharArray()) {
                bitsToConvert += c;

                if (k % 7 == 0) {
                    int decimal = Integer.parseInt(bitsToConvert, 2);
                    writer.write((char) decimal);
                    bitsToConvert = "";
                }

                k++;
            }

            bitsAfterCompression = finalBitsOutput.length() + (8 - (finalBitsOutput.length() % 8));
            writer.close();
            byteCodeOutputFile.setWritable(false);
        } catch (IOException ex) {

            throw new RuntimeException("Cannot write to file");
        }
    }

    public static List<Node> getBaseTreeFromFile(File file) {

        List<Node> list = new ArrayList<Node>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            char newCharacter = 'z';
            int character;
            int newFrequency;
            String tempBuf = "";
            int i = 0;

            while ((character = reader.read()) != -1) {
                tempBuf += (char) character != (char) 16 ? (char) character : "";
                if ((char) character == (char) 16) {
                    if (i % 2 == 0) {
                        newCharacter = tempBuf.charAt(0);
                    } else {
                        newFrequency = Integer.parseInt(tempBuf);
                        Node newNode = new Node(newFrequency, newCharacter, null, null);
                        list.add(newNode);
                    }
                    tempBuf = "";
                    i++;
                }
            }

            file.setWritable(true);
        } catch (FileNotFoundException ex) {

            throw new IllegalArgumentException("File was not found!!!");
        } catch (IOException ex) {

            throw new IllegalArgumentException("Something is wrong with file!!!");
        }

        return list;
    }

    public static void toOutputFile(String parent, String finalMessage) {

        File directory = new File(parent);
        File finalMessageFile;
        charsAfterDecompression = finalMessage.length();

        if (!directory.isDirectory()) {
            throw new RuntimeException("Something is wrong with output file!!!");
        }

        try {
            finalMessageFile = new File(parent + "\\output" + ".txt");
            finalMessageFile.createNewFile();
            FileWriter writer;
            writer = new FileWriter(finalMessageFile);
            writer.write(finalMessage);
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException("Cannot write to file");
        }
    }

    public static int getBits() {

        return Utils.bitsAfterCompression;
    }

    public static int getChars() {

        return Utils.charsAfterDecompression;
    }
}
