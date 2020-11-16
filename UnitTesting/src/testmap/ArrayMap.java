package testmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ArrayMap<K, V> implements MyMap<K, V> {

    private KeyValue[] entities;
    private int index = 0;

    public ArrayMap() {
        entities = (KeyValue[]) java.lang.reflect.Array.newInstance(KeyValue.class, 7);
    }

    @Override
    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");
        if (containsKey(key))
            throw new IllegalArgumentException("Duplicate key not allowed");

        if (index == entities.length) {
            expand();
        }
        entities[index] = new KeyValue(key, value);
        index++;
    }

    private void expand() {
        KeyValue[] tmp = (KeyValue[]) java.lang.reflect.Array.newInstance(KeyValue.class, entities.length*2);
        System.arraycopy(entities, 0, tmp, 0, entities.length);
        entities = tmp;
    }

    @Override
    public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("Null not allowed");

        for (KeyValue kv : entities) {
            if (kv.key.equals(key)) return kv.value;
        }
        return null; // found nothing, return null
    }

    @Override
    public boolean replace(K key, V value) {
        // -- null value?
        if (key == null)
            throw new IllegalArgumentException("Null not allowed");
        if (!containsKey(key))
            return false;
        for (KeyValue entity : entities) {
            if (entity.key.equals(key)) {
                entity.value = value;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsKey(K key) {
        // -- null key?
        for (KeyValue entity : entities) {
            if (entity != null && entity.key.equals(key)) return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        if (value == null)
            return false;
        for (KeyValue entity : entities) {
            if (entity.value.equals(value)) return true;
        }
        return false;
    }

    @Override
    public V remove(K key) {
        if (key == null)
            return null;
        if (!containsKey(key))
            return null;
        int idx = -1;
        for (int i = 0; i < entities.length; i++) {
            if (entities[i].key.equals(key)) {
                idx = i;
                break;
            }
        }
        V valueToRemove = entities[idx].value;
        entities[idx] = null;
        for (int i = idx; i < entities.length - 1; i++) {
            entities[i] = entities[i + 1];
        }
        index--;
        return valueToRemove;
    }

    @Override
    public boolean remove(K key, V value) {
        V v = get(key);
        if (v.equals(value)) {
            V remove = remove(key);
            return remove != null;
        }
        return false;
    }

    @Override
    public int size() {
        // --
        return index;
    }

    @Override
    public List<K> keyList() {
        List<K> keys = new ArrayList<>(index);
        for (KeyValue entity : entities) {
            keys.add(entity.key);
        }
        return keys;
    }

    @Override
    public List<V> valueList() {
        List<V> values = new ArrayList<>(index);
        for (KeyValue entity : entities) {
            values.add(entity.value);
        }
        return values;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    private class KeyValue {
        K key;
        V value;

        public KeyValue(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
