/*AVLTree: The AVL Tree, including insert, remove, height and restructure.
 Author: Leo Date: 02/05/2015*/
package dsa.impl;

import dsa.iface.BinarySearchTree;
import dsa.iface.Node;

public class AVLTree extends ProperLinkedBinaryTree implements BinarySearchTree {

   /**
    * Constructor. Create a new, empty AVL Tree.
    * 
    */
   public AVLTree() {
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
    	  restructure(left(p));
      }
      else{
    	  Node m;
    	  if(isExternal(left(find)))
    		  m = right(find);
    	  else
    		  m = left(find);
    	  remove(find);
    	  restructure(m);
      }
   }
   
   /**
    * Restructure the AVL tree at node x. Tri-node restructuring should happen using x, y (the parent of x) and z (the parent of y).
    * @param node
    */
   protected void restructure( Node x ) {
		if (x != root()) {
			Node y = parent(x);
			Node z = parent(y);
			if (y != null && z != null && isBalanced(y)) {
				if (isBalanced(z)) {
					restructure(y);
				} 
				else {
					if (height(right(z)) < height(left(z))) {
						Node t0 = left(x);
						Node t1 = right(x);
						Node t2 = right(y);
						Node t3 = right(z);
						if (height(right(y)) > height(left(y))) {
							t0 = left(y);
							t1 = left(x);
							t2 = right(x);
							t3 = right(z);
							Node t = y;
							y = x;
							x = t;
						}
						if(z == root)
							root = (LTNode) y;
						else if(left(parent(z)) == z)
							((LTNode)parent(z)).left = (LTNode) y;
						else
							((LTNode)parent(z)).right = (LTNode) y;
						((LTNode) y).parent = (LTNode) parent(z);
						((LTNode) y).right = (LTNode) z;
						((LTNode) y).left = (LTNode) x;
						((LTNode) z).parent = (LTNode) y;
						((LTNode) x).parent = (LTNode) y;
						
						((LTNode) x).left = (LTNode) t0;
						((LTNode) x).right = (LTNode) t1;
						((LTNode) z).left = (LTNode) t2;
						((LTNode) z).right = (LTNode) t3;
						
						((LTNode) t0).parent = (LTNode) x;
						((LTNode) t1).parent = (LTNode) x;
						((LTNode) t2).parent = (LTNode) z;
						((LTNode) t3).parent = (LTNode) z;
					} else {
						Node t0 = left(z);
						Node t1 = left(y);
						Node t2 = left(x);
						Node t3 = right(x);
						if (height(right(y)) < height(left(y))) {
							t1 = left(x);
							t2 = right(x);
							t3 = right(y);
							Node t = y;
							y = x;
							x = t;
						}
						if(z == root)
							root = (LTNode) y;
						else if(left(parent(z)) == z)
							((LTNode)parent(z)).left = (LTNode) y;
						else
							((LTNode)parent(z)).right = (LTNode) y;
						((LTNode) y).parent = (LTNode) parent(z);
						((LTNode) y).right = (LTNode) x;
						((LTNode) y).left = (LTNode) z;
						((LTNode) z).parent = (LTNode) y;
						((LTNode) x).parent = (LTNode) y;

						((LTNode) z).left = (LTNode) t0;
						((LTNode) z).right = (LTNode) t1;
						((LTNode) x).left = (LTNode) t2;
						((LTNode) x).right = (LTNode) t3;

						((LTNode) t0).parent = (LTNode) z;
						((LTNode) t1).parent = (LTNode) z;
						((LTNode) t2).parent = (LTNode) x;
						((LTNode) t3).parent = (LTNode) x;
					}
					restructure(z);
				}
			}
			else if (!isBalanced(y)) {
				Node k;
				Node l;
				if (height(right(y)) > height(left(y))) {
					k = right(y);
					Node t0 = left(y);
					Node t1, t2, t3;
					if (height(right(k)) < height(left(k))) {
						l = left(k);
						t1 = left(l);
						t2 = right(l);
						t3 = right(k);
						Node t = k;
						k = l;
						l = t;
					} else {
						l = right(k);
						t1 = left(k);
						t2 = left(l);
						t3 = right(l);
					}
					if(y == root)
						root = (LTNode) k;
					else if(left(parent(y)) == k)
						((LTNode)parent(y)).left = (LTNode) k;
					else
						((LTNode)parent(y)).right = (LTNode) k;
					((LTNode) k).parent = (LTNode) parent(y);
					((LTNode) k).right = (LTNode) l;
					((LTNode) k).left = (LTNode) y;
					((LTNode) y).parent = (LTNode) k;
					((LTNode) l).parent = (LTNode) k;

					((LTNode) y).left = (LTNode) t0;
					((LTNode) y).right = (LTNode) t1;
					((LTNode) l).left = (LTNode) t2;
					((LTNode) l).right = (LTNode) t3;

					((LTNode) t0).parent = (LTNode) y;
					((LTNode) t1).parent = (LTNode) y;
					((LTNode) t2).parent = (LTNode) l;
					((LTNode) t3).parent = (LTNode) l;
				} else {
					k = left(y);
					Node t0, t1, t2, t3;
					if (height(right(k)) > height(left(k))) {
						l = right(k);
						t0 = left(k);
						t1 = left(l);
						t2 = right(l);
						t3 = right(y);
					} else {
						l = left(k);
						t0 = left(l);
						t1 = right(l);
						t2 = right(k);
						t3 = right(y);
					}
					if(y == root)
						root = (LTNode) k;
					else if(left(parent(y)) == k)
						((LTNode)parent(y)).left = (LTNode) k;
					else
						((LTNode)parent(y)).right = (LTNode) k;
					((LTNode) k).parent = (LTNode) parent(y);
					((LTNode) k).right = (LTNode) y;
					((LTNode) k).left = (LTNode) l;
					((LTNode) y).parent = (LTNode) k;
					((LTNode) l).parent = (LTNode) k;

					((LTNode) l).left = (LTNode) t0;
					((LTNode) l).right = (LTNode) t1;
					((LTNode) y).left = (LTNode) t2;	
					((LTNode) y).right = (LTNode) t3;

					((LTNode) t0).parent = (LTNode) l;
					((LTNode) t1).parent = (LTNode) l;
					((LTNode) t2).parent = (LTNode) y;
					((LTNode) t3).parent = (LTNode) y;
				}
				restructure(k);
			}
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

   private boolean isBalanced( Node n ) {
      if((height(right(n))-height(left(n)))> -2 && (height(right(n))-height(left(n)))< 2)
    	  return true;
      else
    	  return false;
   }

   private int height( Node n ) {
      if(isExternal(n))
    	  return 0;
      else if(isExternal(right(n))){
    	  return height(left(n))+1;
      }
      else if(isExternal(left(n))){
    	  return height(right(n))+1;
      }
      else{
    	  int i = height(left(n))+1;
    	  int j = height(right(n))+1;
    	  return i>j?i:j;
      }
   }
}
