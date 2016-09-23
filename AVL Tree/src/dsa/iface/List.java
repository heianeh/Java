package dsa.iface;


public interface List {
      public int size();
      public boolean isEmpty();
      public Node first();
      public Node last();
      public Node prev(Node p);
      public Node next(Node p);
      public void insertFirst(Object e);
      public void insertLast(Object e);
      public void insertBefore(Node p, Object e);
      public void insertAfter(Node p, Object e);
      public Object replace(Node p, Object e);
      public Object remove(Node p);
}