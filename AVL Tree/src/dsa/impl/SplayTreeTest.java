package dsa.impl;

public class SplayTreeTest {
	public static void main(String[] args) {
		   SplayTree bt = new SplayTree();
		   bt.insert( 40 );
		   bt.insert( 50 );
		   bt.insert( 60 );
		   bt.remove( 60 );
		   bt.insert( 30 );
		   bt.remove( 50 );
		   bt.insert( 33 );
		   bt.remove( 30 );
		   bt.insert( 38 );
		   bt.insert( 10 );
		   bt.insert( 5 );
		   bt.remove( 40 );
		   bt.insert( 50 );
		   bt.insert( 60 );
		   bt.insert( 55 );
		   bt.remove( 5 );
		   bt.insert( 36 );
		   bt.remove( 60 );
		   bt.remove( 55 );
		   bt.remove( 50 );
		   bt.insert( 37 );
		   bt.remove( 10 );
		   bt.insert( 20 );
		   bt.insert( 40 );
		   bt.insert( 50 );
		   bt.insert( 10 );
		   bt.insert( 34 );
		   bt.insert( 60 );
		   bt.insert( 35 );
		   bt.insert( 25 );
		   bt.insert( 5 );
		   bt.remove( 38 );
		   TreePrinter.printTree(bt);
	}

}
