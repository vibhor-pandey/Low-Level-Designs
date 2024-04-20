package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;


@AllArgsConstructor
@Data
public class QueueNode<K, V>{
    QueueNode<K, V> prev;
    K key;
    V value;
    QueueNode<K, V> next;
    long timestamp = Instant.now().toEpochMilli();

    public QueueNode(QueueNode<K, V> prev, K key, V value, QueueNode<K, V> next) {
        this.prev = prev;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public String toString() {
        return value.toString();
    }
}
