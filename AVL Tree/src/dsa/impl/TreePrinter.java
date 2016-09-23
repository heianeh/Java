package dsa.impl;

import dsa.iface.Iterator;
import dsa.iface.Node;
import dsa.iface.Tree;

public class TreePrinter {
   Tree tree;
   String output = "";

   private TreePrinter( Tree tree ) {
      this.tree = tree;
      this.visit( tree.root(), "" );
   }

   private void visit( Node position, Object data ) {
      output += data.toString() + ( position.element() == null ? "[]" : position.element() ) + "\n";
      Iterator it = tree.children( position );
      while ( it.hasNext() ) {
         visit( (Node) it.next(), data.toString() + "\t" );
      }
   }

   private String getString() {
      return output;
   }

   public static void printTree( Tree t ) {
      System.out.println( new TreePrinter( t ).getString() );
   }

   /*
    * public class BinarySearchTreePrinter { public static void printTree(
    * BinarySearchTree t ) { Queue q = new LinkedQueue(); q.enqueue( t.root() );
    * int width = 1, count = 0; while( !q.isEmpty() ) { Node n = (Node)
    * q.dequeue(); System.out.print( "[" + ( n.element() == null ? "" :
    * n.element() ) + "]\t" ); Iterator it = t.children( n ); while(
    * it.hasNext() ) { q.enqueue( it.next() ); System.err.println(
    * "Enqueing child of " + n.element() ); } count++; if ( count == width ) {
    * count = 0; width *= 2; System.out.println(); } } }
    */
   public static void main( String[] args ) {
	   
//      BasicBinarySearchTree bt = new BasicBinarySearchTree();
	   SplayTree bt = new SplayTree();
	   bt.insert( 23 );
	   bt.insert( 12 );
       bt.insert( 44 );
       bt.insert( 13 ); 
	   bt.insert( 1 );
	   bt.insert( 7 );
	   bt.insert( 22 );
	   bt.insert( 55 );
	   bt.insert( 43 );
	   bt.insert( 18 );
	   bt.remove( 1 );
	   bt.remove( 44 );
      TreePrinter.printTree( bt );
   }
}
