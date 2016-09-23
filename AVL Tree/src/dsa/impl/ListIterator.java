package dsa.impl;

import dsa.iface.Iterator;
import dsa.iface.List;
import dsa.iface.Node;

public class ListIterator implements Iterator {
   private Node node;
   private List list;

   public ListIterator( List list ) {
      this.list = list;
      node = list.first();
   }

   public boolean hasNext() {
      return node != null;
   }

   public Object next() {
      Object toReturn = node.element();
      node = list.next( node );
      return toReturn;
   }
}
