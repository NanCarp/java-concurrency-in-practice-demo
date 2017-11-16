public interface ConcurrentMap<K,V> extends Map<L, V> {
	// 仅当 K 没有相应地映射值时才插入
	V putIfAbsent(K key, V value);

	// 仅当 K 被映射到 V 时才移除
	boolean remove(K key, V value);

	// 仅当 K 被映射到 oldValue 时才替换为 newValue
	boolean replace(K key, V oldValue, V newValue);

	// 仅当 K 被映射到某个值时才替换为 newValue
	V replace(K Key, V newValue);
}