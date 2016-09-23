package dsa.iface;

/**
 * Interface representing the Map ADT.
 *
 */
public interface Map {
   /**
    * Get the number of entries in the map.
    * 
    * @return
    */
   public int size();

   /**
    * Check whether the map is empty.
    * 
    * @return {@code true} if the map is empty, {@code false} otherwise.
    */
   public boolean isEmpty();

   /**
    * Get the value associated with a given key in the map.
    * 
    * @param k
    *           The key to search for.
    * @return The value associated with key {@code k}, or {@code null} if {@code k}
    *         is not a key in the map.
    */
   public Object get( Object k );

   /**
    * Add a new key/value pair to the map. If the key already exists in the map,
    * this method will replace its value, and return the value that
    * was replaced.
    * 
    * @param k
    *           The key to add.
    * @param v
    *           The value to associate with key {@code k}.
    * @return The value formerly associated with {@code k}, if {@code k} was
    *         already in the map, {@code false} otherwise.
    */
   public Object put( Object k, Object v );

   /**
    * Remove the entry with key {@code k} from the map.
    * 
    * @param k
    * @return The value that was associated with key {@code k}, or {@code null}
    *         if {@code k} was not in the map.
    */
   public Object remove( Object k );

   /**
    * Get an iterator over all the keys in the map.
    * 
    * @return
    */
   public Iterator keys();

   /**
    * Get an iterator over all the values in the map.
    * 
    * @return
    */
   public Iterator values();

   /**
    * Get an iterator over all the entries in the map.
    * 
    * @return
    */
   public Iterator entries();
}