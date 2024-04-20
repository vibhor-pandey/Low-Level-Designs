package core.impl;

import core.Cache;
import model.QueueNode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V> {

    private static final int TTL_IN_MINUTES = 1440;

    private static final int DEFAULT_CAPACITY = 5;

    private final Map<K, QueueNode<K, V>> cacheMap;

    private final QueueNode<K, V> head;

    private final QueueNode<K, V> tail;

    private final int capacity;


    public LRUCache() {
        this.capacity = DEFAULT_CAPACITY;
        this.cacheMap = new HashMap<>(capacity);
        this.head = new QueueNode<>(null, null, null, null);
        this.tail = new QueueNode<>(head, null, null, null);
        head.setNext(tail);
        tail.setPrev(head);
    }

    public LRUCache(final int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>(capacity);
        this.head = new QueueNode<>(null, null, null, null);
        this.tail = new QueueNode<>(null, null, null, null);
        head.setNext(tail);
        tail.setPrev(head);
    }

    private void evict() {
        final QueueNode<K, V> node = tail.getPrev();
        System.out.println("EVICT[KEY] - " + node.getKey());
        tail.setPrev(tail.getPrev().getPrev());
        tail.getPrev().setNext(tail);
        cacheMap.remove(node.getKey());
    }


    @Override
    public V get(K key) {
        System.out.println("GET[KEY] -  - " + key);
        if(!cacheMap.containsKey(key)) {
            return null;
        }
        final QueueNode<K, V> node = cacheMap.get(key);
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());

        System.out.println("Adding Key next to Head - " + key);
        node.setNext(head.getNext());
        node.setPrev(head);
        head.getNext().setPrev(node);
        head.setNext(node);

        return node.getValue();
    }

    @Override
    public boolean set(final K key, final V value) {
        System.out.println("SET[KEY] - " + key);
        final QueueNode<K, V> node;
        if(cacheMap.containsKey(key)) {
            node = cacheMap.get(key);
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        } else {
            node = new QueueNode<>(head, key, value, null);
        }

        if (cacheMap.size() == capacity) {
            evict();
        }
        node.setNext(head.getNext());
        node.setPrev(head);
        head.getNext().setPrev(node);
        head.setNext(node);

        cacheMap.put(key, node);
        return true;
    }

    @Override
    public boolean clear() {
        System.out.println("CLEAR[]");
        cacheMap.clear();
        head.setNext(tail);
        tail.setPrev(head);
        return true;
    }
}
