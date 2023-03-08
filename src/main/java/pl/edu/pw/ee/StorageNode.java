package pl.edu.pw.ee;

public class StorageNode {

    private char character;

    private String byteCode;

    public StorageNode(char character, String byteCode) {

        this.character = character;
        this.byteCode = byteCode;
    }

    public char getCharacter() {

        return character;
    }

    public void setCharacter(char character) {

        this.character = character;
    }

    public String getByteCode() {

        return byteCode;
    }

    public void setByteCode(String byteCode) {

        this.byteCode = byteCode;
    }

    @Override
    public String toString() {

        return character + " - " + byteCode;
    }

}
