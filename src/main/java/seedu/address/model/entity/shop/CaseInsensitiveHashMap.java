package seedu.address.model.entity.shop;

import java.util.HashMap;

/**
 * A HashMap that is case-insensitive.
 * Keys are converted to uppercase before being stored.
 * @param <V> The type of the value.
 */
public class CaseInsensitiveHashMap<V> extends HashMap<String, V> {
    @Override
    public V get(Object key) {
        if (key instanceof String) {
            return super.get(((String) key).toUpperCase());
        }
        return null;
    }

    @Override
    public V put(String key, V value) {
        return super.put(key.toUpperCase(), value);
    }
    @Override
    public boolean containsKey(Object key) {
        if (key instanceof String) {
            return super.containsKey(((String) key).toUpperCase());
        }
        return false;
    }
    @Override
    public V remove(Object key) {
        if (key instanceof String) {
            return super.remove(((String) key).toUpperCase());
        }
        return null;
    }
}
