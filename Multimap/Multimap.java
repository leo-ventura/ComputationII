import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.*;

public interface Multimap<K,V> {
    @SuppressWarnings("unchecked") void put( K key, V value, V... values );
    List<V> get( K key );
    @SuppressWarnings("unchecked") void replace( K key, V... newValues );
    void removeAll( K key, V... values ) throws Exception;
    void removeAny( K key, V... values ) throws Exception;
    boolean contains( K key );
    boolean containsAny( K key, V... values );
    boolean containsAll( K key, V... values );
    Set<Entry<K,List<V>>> entrySet();
    Set<K> keySet();
    Collection<V> values();
    @SuppressWarnings("unchecked") void forEach(BiConsumer<? super K, ? super V> action);
}