package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import static pl.edu.pw.ee.services.Utils.getCharsFromFile;
import pl.edu.pw.ee.services.TransmisionInterface;
import pl.edu.pw.ee.services.Utils;

public class Decompress implements TransmisionInterface {

    List<Node> baseTree = null;
    String byteToConvert = null;
    List<StorageNode> outputList;

    @Override
    public void transmision(File file) throws FileNotFoundException {

        String fileSubname = file.getName().substring(0, 4);

        if (fileSubname.compareTo("byte") == 0) {
            byteToConvert = "";
            char[] charArr = getCharsFromFile(file, true);
            file.setWritable(true);
            for (int i = 0; i < charArr.length; i++) {//-1
                int howMuchToAddInFront = Integer.toBinaryString(charArr[i]).length();
                for (int j = 0; j < 7 - howMuchToAddInFront; j++) {
                    byteToConvert += "0";
                }
                byteToConvert += Integer.toBinaryString(charArr[i]);
            }

            byteToConvert = removeAdditionalBits(byteToConvert);

        } else if (fileSubname.compareTo("tree") == 0) {
            baseTree = Utils.getBaseTreeFromFile(file);

        }

        if (baseTree != null && byteToConvert != null) {
            HuffmanTree tree = new HuffmanTree(baseTree);

            outputList = tree.getOutputList();

            String finalOutput = byteToFinalOutput();

            Utils.toOutputFile(file.getParent(), finalOutput);
        }
    }

    private String byteToFinalOutput() {

        String finalOutput = "";
        String tmpStr = "";

        for (char c : byteToConvert.toCharArray()) {
            tmpStr += c;

            for (StorageNode n : outputList) {
                if (n.getByteCode().compareTo(tmpStr) == 0) {
                    finalOutput += n.getCharacter();
                    tmpStr = "";
                    break;
                }
            }
        }

        return finalOutput;
    }

    private String removeAdditionalBits(String byteToConvert) {

        int chowManyWasAdded = Integer.parseInt(byteToConvert.substring(0, 3), 2);
        int lengthOfLastBitSequence = chowManyWasAdded + 3 >= 7 ? 14 - (chowManyWasAdded + 3) : 7 - (chowManyWasAdded + 3);

        byteToConvert = byteToConvert.substring(3, byteToConvert.length());
        String lastBitSequence = byteToConvert.substring(byteToConvert.length() - lengthOfLastBitSequence, byteToConvert.length());
        byteToConvert = byteToConvert.substring(0, byteToConvert.length() - (chowManyWasAdded + lengthOfLastBitSequence));
        byteToConvert += lastBitSequence;

        return byteToConvert;
    }

}
