package testmap;

import java.util.List;

public interface MyMap<K, V> {
    /**
     * Inserts an entry with key 'key' and value 'value
     * Throws IllegalArgumentException if key is null
     * Throws IllegalArgumentException if value is null
     * @param key the key
     * @param value the value
     */
    void put(K key, V value);

    /**
     * Throws IllegalArgumentException if key is null
     * @param key the key to match
     * @return the value associated with key. Null if no key was found.
     */
    V get(K key);

    /**
     * Replaces the value of key.
     * Throws IllegalArgumentException if key is null
     * @param key the key to find
     * @param value the value to put instead of the existing value
     * @return
     */
    boolean replace(K key, V value);

    /**
     * Throws IllegalArgumentException if key is null
     * @param key the key to search for
     * @return returns true, if the map contains the specified key. Otherwise false.
     */
    boolean containsKey(K key);

    /**
     * @param value the value to search for
     * @return returns true if the value exists in the map. Otherwise false.
     */
    boolean containsValue(V value);

    /**
     * remove the entry of (key, value) from the map
     * Throws IllegalArgumentException if key is null
     * @param key the key to search for
     * @return returns true, if the key was found and removed. Otherwise false
     */
    V remove(K key);

    /**
     * remove the entry of (key, value) from the map, assuming the value provided matches the existing
     * @param key the key to search for
     * @param value the value to match
     * @return returns true if the entry was removed. Otherwise false
     */
    boolean remove(K key, V value);

    /**
     * @return the number of entries in the map
     */
    int size();

    /**
     * @return a list of all the keys
     */
    List<K> keyList();

    /**
     * @return a list of all the values
     */
    List<V> valueList();

    /**
     * @return returns true if the map has no entries. Otherwise false
     */
    boolean isEmpty();

}
