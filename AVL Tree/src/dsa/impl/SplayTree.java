/*SplayTree: The Splay Tree, including insert, remove and restructure.
 Author: Leo Date: 02/05/2015*/
package dsa.impl;

import dsa.iface.BinarySearchTree;
import dsa.iface.Node;

public class SplayTree extends ProperLinkedBinaryTree implements BinarySearchTree {

   /**
    * Constructor. Create a new, empty AVL Tree.
    * 
    */
   public SplayTree() {
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
   protected Node find( Node node, Object element ) {
      if ( isExternal( node ) )
         return node;

      @SuppressWarnings("unchecked")
      int result = ( (Comparable<Object>) element ).compareTo( node.element() );

      if ( result < 0 ) {
         return find( left( node ), element );
      }
      else if ( result > 0 ) {
         return find( right( node ), element );
      }
      else {
         return node;
      }
   }

   /**
    * Insert a new value into the AVL tree, and restructure it if necessary.
    * 
    * @param element
    *           The value to be inserted.
    */
   public void insert( Object element ) {
      Node find = find(root, element);
      if(isExternal(find))
    	  expandExternal(find, element);
      restructure(find);
   }

   /**
    * Remove a value from the AVL tree, and restructure it if necessary.
    * 
    * @param element
    *           The value to be removed
    */
   public void remove( Object element ) {
      Node find = find(root, element);
      Node move = left(right(find));
      if(isInternal(left(find))&&isInternal(right(find))){
		  while(isInternal(move)){
			  move = ((LTNode)move).left;
		  }
		  Node p = parent(parent(move));
    	  ((LTNode)find).element = ((LTNode)parent(move)).element;
    	  remove(parent(move));
    	  if(p != null)
    		  restructure(p);
      }
      else{
    	  Node m;
    	  if(isExternal(left(find)))
    		  m = right(find);
    	  else
    		  m = left(find);
    	  remove(find);
    	  if(parent(m)!=null)
    		  restructure(parent(m));
      }
   }
   
   /**
    * Restructure the AVL tree at node x. Tri-node restructuring should happen using x, y (the parent of x) and z (the parent of y).
    * @param node
    */
   protected void restructure( Node x ) {
	   if(x != root){
		   LTNode y = (LTNode) parent(x);
		   LTNode z = (LTNode) parent(y);
		   LTNode t0, t1, t2, t3;
		   if(z == null){
			   if(y.right == x){
				   t0 = y.left;
				   t1 = ((LTNode)x).left;
				   t2 = ((LTNode)x).right;
				   ((LTNode)x).left = y;
				   ((LTNode)x).right = t2;
				   y.left = t0;
				   y.right = t1;
				   
				   t1.parent = y;
				   t0.parent = y;
				   t2.parent = (LTNode) x;
			   }
			   else{
				   t0 = ((LTNode)x).left;
				   t1 = ((LTNode)x).right;
				   t2 = y.right;
				   ((LTNode)x).right = y;
				   ((LTNode)x).left = t0;
				   y.left = t1;
				   y.right = t2;
				   
				   t1.parent = y;
				   t2.parent = y;
				   t0.parent = (LTNode) x;
			   }
			   root = (LTNode) x;
			   ((LTNode) x).parent = y.parent;
			   y.parent = (LTNode) x;
		   }
		   else{
			   ((LTNode)x).parent = z.parent;
			   if(z.parent != null){
				   if(z.parent.left == z)
					   z.parent.left = (LTNode) x;
				   else
					   z.parent.right = (LTNode) x;
			   }
			   else{
				   root = (LTNode) x;
			   }
			   if((int)z.element > (int)y.element){
				   if((int)y.element > (int)((LTNode)x).element){
					   t0 = ((LTNode)x).left;
					   t1 = ((LTNode)x).right;
					   t2 = y.right;
					   t3 = z.right;
					   ((LTNode)x).right = y;
					   y.parent = (LTNode) x;
					   y.right = z;
					   z.parent = y;
					   ((LTNode)x).left = t0;
					   y.left = t1;
					   z.left = t2;
					   z.right = t3;
					   
					   t0.parent = (LTNode) x;
					   t1.parent = y;
					   t2.parent = z;
					   t3.parent = z;
				   }
				   else{
					   t0 = y.left;
					   t1 = ((LTNode)x).left;
					   t2 = ((LTNode)x).right;
					   t3 = z.right;
					   ((LTNode)x).right = z;
					   ((LTNode)x).left = y;
					   y.parent = (LTNode) x;
					   z.parent = (LTNode) x;
					   y.left = t0;
					   y.right = t1;
					   z.left = t2;
					   z.right = t3;
					   
					   t0.parent = y;
					   t1.parent = y;
					   t2.parent = z;
					   t3.parent = z;
				   }
			   }
			   else{
				   if((int)y.element > (int)((LTNode)x).element){
					   t0 = z.left;
					   t1 = ((LTNode)x).left;
					   t2 = ((LTNode)x).right;
					   t3 = y.right;
					   ((LTNode)x).right = y;
					   ((LTNode)x).left = z;
					   y.parent = (LTNode) x;
					   z.parent = (LTNode) x;
					   z.left = t0;
					   z.right = t1;
					   y.left = t2;
					   y.right = t3;
					   
					   t0.parent = z;
					   t1.parent = z;
					   t2.parent = y;
					   t3.parent = y;
				   }
				   else{
					   t0 = z.left;
					   t1 = y.left;
					   t2 = ((LTNode)x).left;
					   t3 = ((LTNode)x).right;
					   ((LTNode)x).left = y;
					   y.parent = (LTNode) x;
					   y.left = z;
					   z.parent = y;
					   ((LTNode)x).right = t3;
					   y.right = t2;
					   z.left = t0;
					   z.right = t1;
					   
					   t0.parent = z;
					   t1.parent = z;
					   t2.parent = y;
					   t3.parent = (LTNode) x;
				   }
			   }
		   }
		   restructure(x);
	   }
   }

   /**
    * Check if a value is contained within the AVL tree
    * 
    * @param element
    *           The value to look for.
    * @return {@code true} if the value is contained in the tree, {@code false}
    *         otherwise.
    */
   public boolean contains( Object element ) {
      return isInternal( find( root(), element ) );
   }
}
