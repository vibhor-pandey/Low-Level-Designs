package core;

public interface Cache<K, V> {

    V get(final K key);

    boolean set(final K key, final V value);

    boolean clear();
}
