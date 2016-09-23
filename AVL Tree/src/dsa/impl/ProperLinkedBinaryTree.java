package dsa.impl;

import dsa.iface.BinaryTree;
import dsa.iface.Iterator;
import dsa.iface.List;
import dsa.iface.Node;
import dsa.iface.Queue;

public class ProperLinkedBinaryTree implements BinaryTree {

   protected LTNode root;
   private int size;

   /**
    * Constructor - create an empty tree
    */
   public ProperLinkedBinaryTree() {
      root = new LTNode( null, null );
      size = 1;
   }

   /**
    * Expand an external node - Store a value in the external node - Create two
    * external nodes as children, making {@code n} an internal node
    * 
    * @param n
    *           The node to expand. An exception will be thrown if it is not
    *           external.
    * @param e
    *           The value to be stored in node {@code n}.
    */
   public void expandExternal( Node n, Object e ) {
      if ( isInternal( n ) )
         throw new RuntimeException( "Not an external node" );
      LTNode node = (LTNode) n;
      node.element = e;
      node.left = new LTNode( null, node );
      node.right = new LTNode( null, node );
      size += 2;
   }

   /**
    * Remove a node from the tree
    * 
    * @param n
    *           The node to be removed
    */
   public Object remove( Node n ) {
      LTNode node = (LTNode) n;
      if ( isExternal( node.left ) ) {
         if ( node == root ) {
            root = node.right;
            node.right.parent = null;
         }
         else if ( node.parent.left == node ) {
            node.parent.left = node.right;
            node.right.parent = node.parent;
            node.left.parent = null;
         }
         else {
            node.parent.right = node.right;
            node.right.parent = node.parent;
            node.left.parent = null;
         }
         node.left = null;
         node.right = null;
         node.parent = null;
      }
      else if ( isExternal( node.right ) ) {
         if ( node == root ) {
            root = node.left;
            node.left.parent = null;
         }
         else if ( node.parent.left == node ) {
            node.parent.left = node.left;
            node.left.parent = node.parent;
            node.right.parent = null;
         }
         else {
            node.parent.right = node.left;
            node.left.parent = node.parent;
            node.right.parent = null;
         }
         node.left = null;
         node.right = null;
         node.parent = null;
      }
      else {
         throw new RuntimeException( "Cannot remove a node with two internal children." );
      }

      Object temp = node.element;
      node.element = null;
      size -= 2;

      return temp;
   }

   @Override
   public Node root() {
      return root;
   }

   @Override
   public Node parent( Node n ) {
      return ( (LTNode) n ).parent;
   }

   @Override
   public Iterator children( Node n ) {
      LTNode node = (LTNode) n;
      List l = new SLinkedList();
      if ( isInternal( n ) ) {
         l.insertLast( node.left );
         l.insertLast( node.right );
      }
      return new ListIterator( l );
   }

   @Override
   public boolean isInternal( Node n ) {
      return hasLeft( n ) || hasRight( n );
   }

   @Override
   public boolean isExternal( Node n ) {
      return !hasLeft( n ) && !hasRight( n );
   }

   @Override
   public boolean isRoot( Node n ) {
      return root == n;
   }

   @Override
   public int size() {
      return size;
   }

   @Override
   public boolean isEmpty() {
      return size == 0;
   }

   @Override
   public Iterator iterator() {
      List elements = new SLinkedList();
      Iterator it = nodes();
      while ( it.hasNext() ) {
         Node n = (Node) it.next();
         if ( isInternal( n ) ) {
            elements.insertLast( n.element() );
         }
      }
      return new ListIterator( elements );
   }

   @Override
   public Iterator nodes() {
      Queue toVisit = new LinkedQueue();
      toVisit.enqueue( root() );
      List nodes = new SLinkedList();
      while ( !toVisit.isEmpty() ) {
         Node n = (Node) toVisit.dequeue();
         nodes.insertLast( n );
         Iterator it = children( n );
         while ( it.hasNext() ) {
            toVisit.enqueue( it.next() );
         }
      }
      return new ListIterator( nodes );
   }

   @Override
   public Object replace( Node n, Object e ) {
      Object toReturn = n.element();
      ( (LTNode) n ).element = e;
      return toReturn;
   }

   @Override
   public Node left( Node n ) {
      return ( (LTNode) n ).left;
   }

   @Override
   public Node right( Node n ) {
      return ( (LTNode) n ).right;
   }

   @Override
   public boolean hasLeft( Node n ) {
      return left( n ) != null;
   }

   @Override
   public boolean hasRight( Node n ) {
      return right( n ) != null;
   }

   /*
    * Inner class to represent a node that's part of a linked binary tree
    */
   protected class LTNode implements Node {
      LTNode parent;
      LTNode left, right;
      Object element;

      public LTNode( Object e, LTNode p ) {
         this( e, p, null, null );
      }

      public LTNode( Object e, LTNode p, LTNode l, LTNode r ) {
         element = e;
         parent = p;
         left = l;
         right = r;
      }

      public Object element() {
         return element;
      }
   }
}
