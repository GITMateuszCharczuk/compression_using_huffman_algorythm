package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static pl.edu.pw.ee.services.Utils.getCharsFromFile;
import pl.edu.pw.ee.services.TransmisionInterface;
import pl.edu.pw.ee.services.Utils;

public class Compress implements TransmisionInterface {// zrób private czy cos

    private char[] charArr;

    @Override
    public void transmision(File file) throws FileNotFoundException {

        String fileSubname = file.getName().substring(0, 4);

        if (fileSubname.compareTo("test") != 0) {
            return;
        }

        char[] originalCharArr = getCharsFromFile(file, false);

        charArr = Arrays.copyOf(originalCharArr, originalCharArr.length);
        List<Node> baseTree = convertCharsToNodes();
        Utils.baseTreeToFile(file.getParent(), baseTree);
        HuffmanTree tree = new HuffmanTree(baseTree);
        List<StorageNode> outputTree = tree.getOutputList();
        Utils.convertInputStringToFile(file.getParent(), outputTree, originalCharArr);
    }

    private List<Node> convertCharsToNodes() {

        List<Node> list = new ArrayList<>();

        for (char c : charArr) {
            Node comparsionNode = new Node(1, c, null, null);
            if (list.contains(comparsionNode)) {
                list.get(list.indexOf(comparsionNode)).increaseFrequency();
            } else {
                list.add(comparsionNode);
            }
        }

        return list;
    }

    public void setCharArr(char[] charArr) {

        this.charArr = charArr;
    }

}
