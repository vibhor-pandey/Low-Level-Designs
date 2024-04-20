package handler;

import core.Cache;
import core.impl.LRUCache;

public class CacheHandler<K, V> {

    private static final int CACHE_CAPACITY = 3;

    private final Cache<K, V> cache;

    public CacheHandler() {
        cache = new LRUCache<>(CACHE_CAPACITY);
    }


    public boolean setData(final K key, final V value) {
        return cache.set(key, value);
    }

    public V getData(final K key) {
        return cache.get(key);
    }
}
