/**
 * @author Kai Lu
 * Hashtable implementation in Java with following methods:
 * 1. containsKey: tests if the specified object is a key in this hashtable
 * 2. put: puts an entry in hashtable. If existed, update the value
 * 3. get: fetches the value stored for a given key
 * 4. remove: remove the entry in hashtable for a given key
 * 5. resize: resize the hashtable for given new size.
 * 
 * HashEntry will contain the following fields:
 * 1. final K key: key value
 * 2. V value: value to be stored
 * 3. HashEntry<K, V> next: the next hash entry's reference
 */
public class KLHashtable<K, V> {
    /**
     * Generic HashEntry class, acts like a linked list node
     * @author Kai Lu
     * @param <K> key
     * @param <V> value
     */
    static class HashEntry<K,V> {
        final K key;
        V value;
        HashEntry<K,V> next;

        public HashEntry(K k, V v, HashEntry<K,V> n){
            key = k;
            value = v;
            next = n;
        }
    }
    
    private HashEntry<K, V>[] entries;
    
    /**
     * Constructor
     * @param size
     */
    @SuppressWarnings("unchecked")
    public KLHashtable(int size) {
        entries = new HashEntry[size];
    }
    
    /**
     * using the hashCode() of the key to get the hash index
     * @param key
     * @return hashIndex
     */
    private int getIndex(K key) {
        int hashIndex = key.hashCode() % entries.length;
        while (hashIndex < 0) {
            hashIndex += entries.length;
        }
        return hashIndex;
    }
    
    /**
     * tests if this hashtable already has the key or not
     * @param key
     * @return
     */
    public boolean containsKey(K key) {
        int hashIndex = getIndex(key);
        for (HashEntry<K, V> entry = entries[hashIndex]; entry != null; entry = entry.next) {
            if (key.equals(entry.key)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Maps the specified key to the specified value in this hashtable.
     * @param key the hashtable key
     * @param value the value
     * @return the previous value of the specified key in this hashtable, or null if it did not have one
     */
    public V put(K key, V value) {
        int hashIndex = getIndex(key);
        
        /* check if the same key already exists in the hashtable and if so update the entry with the new value */
        for (HashEntry<K, V> entry = entries[hashIndex]; entry != null; entry = entry.next) {
            if (key.equals(entry.key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        
        /* If doesn't exist, add the new hash entry created below to the linked list at entris[hashIndex] position */
        HashEntry<K, V> newNode = new HashEntry<K, V>(key, value, null);
        if (entries[hashIndex] == null) {
            entries[hashIndex] = newNode;
        } else {
            HashEntry<K, V> runner = entries[hashIndex];
            while (runner.next != null) {
                runner = runner.next;
            }
            runner.next = newNode;
        }
        
        return null;
    }
    
    /**
     * Removes the key (and its corresponding value) from this hashtable. 
     * This method does nothing if the key is not in the hashtable.
     * @param key the key to be removed
     * @return the value to which the key had been mapped in this hashtable, or null if the key did not have a mapping
     */
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int hashIndex = getIndex(key);
        HashEntry<K, V> prev = null;
        V removedV = null;
        for (HashEntry<K, V> entry = entries[hashIndex]; entry != null; entry = entry.next) {
            if (key.equals(entry.key)) {
                removedV = entry.value;
                /* update the linked list at hashIndex */
                if (prev == null) {
                    entries[hashIndex] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                break;
            }
            prev = entry;
        }
        return removedV;
    }
    
    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    public V get(K key) {
        int hashIndex = getIndex(key);
        for (HashEntry<K, V> entry = entries[hashIndex]; entry != null; entry = entry.next) {
            if (key.equals(entry.key)) {
                return entry.value;
            }
        }
        return null;
    }
    
    /**
     * update the size of the hashtable. copy all the content from old hashtable
     * @param size new hashtable size
     */
    public void resize(int size) {
        KLHashtable<K, V> newHashtable = new KLHashtable<K, V>(size);
        for (HashEntry<K, V> entry : entries) {
            for (; entry != null; entry = entry.next) {
                newHashtable.put(entry.key, entry.value);
                remove(entry.key);
            }
        }
        entries = newHashtable.entries;
    }
}
