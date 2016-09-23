package dsa.iface;

public interface BinaryTree extends Tree {
    public Node left(Node n);
    public Node right(Node n);
    public boolean hasLeft(Node n);
    public boolean hasRight(Node n);
}
