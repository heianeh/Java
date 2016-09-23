package dsa.impl;

import dsa.iface.Queue;

public class LinkedQueue implements Queue {

   private int size = 0;
   private QNode front = null;
   private QNode rear = null;
   
   public LinkedQueue() {
      
   }
   
   @Override
   public void enqueue( Object value ) {
      QNode node = new QNode( value );
      if ( rear == null )
         front = node;
      else
         rear.next = node;
      rear = node;
      size++;
   }

   @Override
   public Object dequeue() {
      Object e = front.element;
      front = front.next;
      if ( front == null )
         rear = null;
      size--;
      return e;
   }

   @Override
   public int size() {
      return this.size;
   }

   @Override
   public boolean isEmpty() {
      return this.size == 0;
   }

   @Override
   public Object front() {
      return this.front.element;
   }
   
   @Override
   public String toString() {
      String out = "[" + size + " element(s)]: ";
      QNode n = front;
      while ( n != null ) {
         out += "[" + n.element + "] -> ";
         n = n.next;
      }
      return out;
   }
   
   private class QNode {
      public QNode next;
      public Object element;
      
      public QNode( Object element ) {
         this.element = element;
         this.next = null;
      }
   }
}


