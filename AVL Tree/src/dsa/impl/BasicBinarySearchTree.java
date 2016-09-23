package dsa.impl;

import dsa.iface.Node;
import dsa.iface.BinarySearchTree;

public class BasicBinarySearchTree extends ProperLinkedBinaryTree implements BinarySearchTree {

   /**
    * Constructor. Create a new, empty Binary Search Tree (with one external node).
    * 
    * @param e
    */
   public BasicBinarySearchTree() {
      super();
   }

   /**
    * Useful method to find the node containing a particular element within a
    * subtree.
    * 
    * @param node
    *           The root node of the subtree to be searched.
    * @param element
    *           The element to search for.
    * @return The node storing {@code element}, or the external node that was
    *         reached trying to find it, if {@code element} is not contained in
    *         the tree.
    */
   private Node find( Node node, Object element ) {
      // 1. Return the node if it is external
      if ( isExternal( node ) )
         return node;

      // 2. Compare the element of 'node' with 'element'
      @SuppressWarnings({ "unchecked", "rawtypes" })
	int result = ( (Comparable) element ).compareTo( node.element() );

      // 3. If 'element' is less than the node's element, call this method again
      // to search the left sub-tree.
      if ( result < 0 ) {
         return find( left( node ), element );
      }

      // 4. If 'element' is greater than the node's element, call this method
      // again to search the right sub-tree.
      else if ( result > 0 ) {
         return find( right( node ), element );
      }

      // 5. If 'element' is equal to the node's element, we've found it found
      // it! Return this node.
      else {
         return node;
      }
   }

   /**
    * Insert a new value into the binary search tree
    * 
    * @param element
    *           The value to be inserted.
    */
   public void insert( Object element ) {

      // 1. Use the 'find' method to find the external node where this should be
      // inserted (starting at the root)
      Node found = find( root(), element );

      // 2. Expand the external node that is found, to insert the element.
      // You can use the expandExternal(Node n, Object e) method from
      // ProperLinkedBinaryTree for this.
      if ( isExternal( found ) ) {
         expandExternal( found, element );
      }
   }

   /**
    * Remove a value from the binary search tree
    * 
    * @param element
    *           The value to be removed
    */
   public void remove( Object element ) {
      // 1. Use the 'find' method (starting at the root) to find the node
      // containing 'element'.
      Node found = find( root(), element );

      // 2. Check that an internal node was returned (otherwise you cannot
      // remove it).
      if ( isInternal( found ) ) {

         // 3. If the node returned has an external child, you can use the
         // remove(Node n) method from ProperLinkedBinaryTree to remove it.
         if ( isExternal( right( found ) ) || isExternal( left( found ) ) ) {
            remove( found );
         }
         else {

            // 4. If the node has two internal children, find the node that has
            // the
            // next biggest value above element.
            // Swap the values of the two nodes.
            // Now remove the correct node, using the remove(Node n) method from
            // ProperLinkedBinaryTree.
            Node toSwap = right( found );
            while ( isInternal( left( toSwap ) ) ) {
               toSwap = left( toSwap );
            }
            replace( found, toSwap.element() );
            remove( toSwap );
         }
      }
   }

   /**
    * Check if a value is contained within the binary search tree
    * 
    * @param element
    *           The value to look for.
    * @return {@code true} if the value is contained in the tree, {@code false}
    *         otherwise.
    */
   public boolean contains( Object element ) {
      // 1. Use the 'find' method to find the node that contains 'element' (if
      // it is in the tree).

      // 2. If 'find' returned an internal node, return true. Otherwise return
      // false.

      return isInternal( find( root(), element ) );
   }
}
