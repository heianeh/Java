package dsa.iface;

/**
 * Interface representing a Tree data structure.
 * @author daithi
 *
 */

public interface Tree {
   /**
    * Get the root node of the tree.
    * @return
    */
    public Node root();
    
    /**
     * Get the parent of node {@code n}
     * @param n
     * @return
     */
    public Node parent(Node n);
    
    /**
     * Get an {@link Iterator} to iterate over the child nodes of {@code n}.
     * @param n
     * @return
     */
    public Iterator children(Node n);
    
    /**
     * Test whether node {@code n} is an internal node (i.e. has children).
     * @param n
     * @return
     */
    public boolean isInternal(Node n);
    
    /**
     * Test whether node {@code n} is external (i.e. has no children).
     * @param n
     * @return
     */
    public boolean isExternal(Node n);
    
    /**
     * Test whether node {@code n} is the root node in the tree.
     * @param n
     * @return
     */
    public boolean isRoot(Node n);
    
    /**
     * Get the size of the tree (i.e. the number of nodes it contains).
     * @return
     */
    public int size();
    
    /**
     * Test whether the tree is empty.
     * @return
     */
    public boolean isEmpty();
    
    /**
     * Get an {@link Iterator} that iterates over all the elements contained in the tree's nodes. 
     * @return
     */
    public Iterator iterator();
    
    /**
     * Get an {@link Iterator} that iterates over all the nodes in the tree.
     * @return
     */
    public Iterator nodes();
    
    /**
     * Replace the element contained in a node.
     * @param n
     * @param t
     * @return The element that was formerly contained in the node before replacement.
     */
    public Object replace(Node n, Object t);
}
