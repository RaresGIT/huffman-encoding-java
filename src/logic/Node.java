package logic;

public class Node implements Comparable<Node>{

    public Character myChar;
    public Integer myFrequency;
    public Node Left,Right;

    public Node(char myChar, int myFrequency) {
        this.myChar = myChar;
        this.myFrequency = myFrequency;
    }

    public Node(int myFrequency,Node leftNode, Node rightNode)
    {
        this.myFrequency=myFrequency;
        this.Left = leftNode;
        this.Right = rightNode;
    }

    public Node() {

    }

    public Node getLeft() {
        return Left;
    }

    public void setLeft(Node left) {
        Left = left;
    }

    public Node getRight() {
        return Right;
    }

    public void setRight(Node right) {
        Right = right;
    }

    public boolean isLeaf() {
        return Left == null && Right == null;
    }
    @Override
    public int compareTo(Node o) {
        return this.myFrequency.compareTo(o.myFrequency);
    }
}
