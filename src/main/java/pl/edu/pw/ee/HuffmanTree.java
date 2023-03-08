package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HuffmanTree {

    private PriorityQueue<Node> nodeQueue;

    private List<StorageNode> outputList;

    class PqComparator implements Comparator<Node> {

        public int compare(Node o1, Node o2) {

            return Integer.compare(o1.getFrequency(), o2.getFrequency());
        }
    }

    public HuffmanTree(List<Node> list) {

        nodeQueue = new PriorityQueue<Node>(list.size(), new PqComparator());
        outputList = new ArrayList<>();

        for (Node n : list) {
            nodeQueue.add(n);
        }

        buildTree();
    }

    private void buildTree() {

        Node root = null;

        if (nodeQueue.size() == 1) {
            root = new Node(0, (char) -1, nodeQueue.poll(), null);
        }

        while (nodeQueue.size() > 1) {

            Node node1 = nodeQueue.poll();

            Node node2 = nodeQueue.poll();

            int newNodeFreq = node1.getFrequency() + node2.getFrequency();
            Node newNode = new Node(newNodeFreq, (char) -1, node1, node2);
            root = newNode;

            nodeQueue.add(newNode);
        }

        fillOutputList(root, "");
    }

    private void fillOutputList(Node root, String message) {

        if (root.getLeft() == null && root.getRight() == null
                && root.getCharacter() != (char) -1) {

            outputList.add(new StorageNode(root.getCharacter(), message));

            return;
        }

        if (root.getLeft() != null) {
            fillOutputList(root.getLeft(), message + "0");
        }

        if (root.getRight() != null) {
            fillOutputList(root.getRight(), message + "1");
        }
    }

    public List<StorageNode> getOutputList() {

        return outputList;
    }

}
