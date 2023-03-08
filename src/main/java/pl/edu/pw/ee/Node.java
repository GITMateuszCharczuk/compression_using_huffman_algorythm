package pl.edu.pw.ee;

public class Node implements Comparable<Node> {

    private int frequency;

    private char character;

    private Node left;

    private Node right;

    public Node(int frequency, char character, Node left, Node right) {

        this.frequency = frequency;
        this.character = character;
        this.left = left;
        this.right = right;
    }

    public int getFrequency() {

        return frequency;
    }

    public void setFrequency(int frequency) {

        this.frequency = frequency;
    }

    public void increaseFrequency() {

        this.frequency++;
    }

    public char getCharacter() {

        return character;
    }

    public void setCharacter(char character) {

        this.character = character;
    }

    public Node getLeft() {

        return left;
    }

    public void setLeft(Node left) {

        this.left = left;
    }

    public Node getRight() {

        return right;
    }

    public void setRight(Node right) {

        this.right = right;
    }

    @Override
    public boolean equals(Object item) {

        if (!(item instanceof Node)) {
            return false;
        }

        Node nodeToCompare = (Node) item;

        return this.getCharacter() == nodeToCompare.getCharacter();
    }

    @Override
    public int compareTo(Node o) {

        return Integer.compare(this.frequency, o.frequency);
    }

    @Override
    public String toString() {

        return character + " - " + frequency;
    }
}
