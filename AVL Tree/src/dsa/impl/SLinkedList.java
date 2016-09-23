package dsa.impl;

import dsa.iface.List;
import dsa.iface.Node;

public class SLinkedList implements List {
   private SNode top;
   private int size;
   
   public int size(){
      return size;
   }
   
   public boolean isEmpty(){
      return size == 0;
   }
   
   public Node first(){
      return top;
   }
   
   public Node last(){
      SNode c = top;
      while(c.next != null){
         c = c.next;
      }
      return c;
   }
   
   public Node next(Node n){
      SNode n1 = (SNode) n;
      return n1.next;
   }
   
   public Node prev(Node n){
      SNode c = top;
      while(c != null && c.next != n){
         c = c.next;
      }
      return c;
   }
   public void insertAfter(Node n, Object e){
      SNode n1 = (SNode) n;
      SNode d = new SNode(e, n1.next);
      n1.next = d;
      size++;
   }
   
   public void insertBefore(Node n, Object e){
      SNode n1 = (SNode) n;
      if(n1 == top){
         insertFirst(e);
      } else {
         SNode b = (SNode) prev(n1);
         SNode d = new SNode(e, n1);
         b.next = d;
         size++;
      }
      
   }
   
   public void insertFirst(Object e){
      SNode n = new SNode(e, top);
      top = n;
      size++;
      
   }
   
   public void insertLast(Object e){
      if(isEmpty()){
         insertFirst(e);
      } else{
         SNode l = (SNode) last();
         SNode d = new SNode(e, null);
         l.next = d;
         size++;
      }
      
   }
   
   public Object remove(Node n){
      SNode n1 = (SNode) n;
      if(n1 == top){
         top = top.next;
         n1.next = null;
         
      } else {
         SNode b = (SNode) prev(n1);
         b.next = n1.next;
         n1.next = null;
      }
      size--;
      return n.element();
   }
   
   private class SNode implements Node {
      public SNode next;
      public Object element;
      
      public Object element() {
         return element;
      }
      
      public SNode( Object e, SNode n ) {
         element = e;
         next = n;
      }
   }

   @Override
   public Object replace( Node n, Object e ) {
      SNode n1 = (SNode) n;
      Object toReturn = n1.element();
      n1.element = e;
      return toReturn;
   }
}