package dsa.iface;

public interface Queue {
   
   public void enqueue( Object value );
   
   public Object dequeue();
   
   public int size();
   
   public boolean isEmpty();
   
   public Object front();
}
