import java.util.Set;
import java.util.TreeSet;

/**
 * Interface Map<K extends comparable, V>
 * is an associative structure
 * Methods include: put, get, containsKey, remove
 * Map is a binary search tree
 *
 * @author nguye28l
 * @version Nov 13, I'm really ... :((
 */


public interface Map<K extends Comparable<K>, V> {
	/**
	 * put
	 * @param K key, V value
	 * load the value into the map at the location associated with the key
	 * replacing preexisting value associated with this key
	 */
	public void put(K key, V value);
	
	/**
	 * get
	 * @param K key
	 * @return the value V associated with Key key
	 */
	public V get(K key);
	
	/**
	 * containsKey
	 * @param K key
	 * @return true if there is a value associated with the value key in the map
	 * @return false otherwise
	 */
	public boolean containskey(K key);
	
	/**
	 * remove
	 * @param K key
	 * remove the entry associated with key
	 * @return the associated value
	 */
	public V remove(K key);
	
	/**
	 * keySet()
	 * @return a set of all variable keys in the map
	 * can build on the fly using a traversal or collect as new keys are added to the map
	 */
	public Set<K> keySet();
}
