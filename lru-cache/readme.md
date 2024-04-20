Project: Least Recently Used Cache
Description: Data Structure which provides caching feature using Least Recently Used Key Eviction Algorithm

Requirements:

Controller

- get(key) : Method which returns value of the key, if found else -1
- set(key, value) : Set Value for a Key, if data structure is full then Least Recently Used Key will be evicted

1) CacheMap
   - Cache<Key, Value>
   - cacheCapacity
   - put(key value)
   - get(key)

2) Cache (Interface)
   - get(key)
   - set(key)
   - evict(key)

3) LRU-Cache implements Cache
   - Overrides get(), set(), evict() for Least Recently User eviction

4) LFU-Cache implements Cache
   - Overrides get(), set(), evict() for Frequently Recently User eviction

5) CacheHandler : Interface b/w Main & Cache
