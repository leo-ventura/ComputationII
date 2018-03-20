import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.Arrays;

public class TreeMultimap<K,V> implements Multimap<K,V>{
    private TreeMap<K, ArrayList<V>> map = new TreeMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public void put(K key, V value, V... values) {
        ArrayList<V> l;

        if (map.containsKey(key))
            l = map.get(key);
        else {
            l = new ArrayList<>();
            map.put(key, l);
        }

        l.add(value);
        if (values != null)
            for (V v : values)
                l.add(v);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<V> get(K key) {
        return map.get(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void replace(K key, V... newValues) {
        if(newValues.length > 0)
            map.put(key, new ArrayList<V>(Arrays.asList(newValues)));
        else
            map.put(key, null);
        
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeAll( K key, V... values ) throws Exception{
        ArrayList<V> e = new ArrayList<>();
        ArrayList<V> toRemove = new ArrayList<>();
        ArrayList<V> toKeep = new ArrayList<>();
        for (V v : values) {
            if (!map.get(key).contains(v))
                e.add(v);
            else toRemove.add(v);
        }
        for(V v : map.get(key)) {
            if(!toRemove.contains(v)) toKeep.add(v);
        }
        if(e.size() == 0)
            map.put(key, toKeep);
        else
            throw new NoSuchElementException(e.toString().substring(1, e.toString().length()-1));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeAny( K key, V... values ) throws Exception{
        ArrayList<V> e = new ArrayList<>();
        ArrayList<V> toRemove = new ArrayList<>();
        ArrayList<V> valores = new ArrayList<>();
        for (V v : values) {
            if (!map.get(key).contains(v))
                e.add(v);
            else
                toRemove.add(v);
        }
        for (V valor : map.get(key)) {
            if (!toRemove.contains(valor))
                valores.add(valor);
        }
        if (e.size() == values.length)
            throw new NoSuchElementException(e.toString().substring(1, e.toString().length() - 1));
        else
            map.put(key, valores);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains( K key ) {
        return map.get(key) != null;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean containsAny( K key, V... values ) {
        for(V v : values) {
            if(map.get(key).contains(v)) return true;
        }
        return false;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean containsAll( K key, V... values ) {
        if(!map.containsKey(key)) return false;
        for (V v : values) {
            if (!map.get(key).contains(v)) return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Entry<K,List<V>>> entrySet() {
        TreeMap<K, List<V>> tempmap = new TreeMap<>();
        for(Map.Entry<K, ArrayList<V>> m : map.entrySet()) {
            tempmap.put(m.getKey(), m.getValue());
        }
        return tempmap.entrySet();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<V> values() {
        Collection<V> c = new ArrayList<>();
        for(ArrayList<V> a : map.values()) {
            for(V v : a)
                c.add(v);
        }
        return c;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void forEach(BiConsumer<? super K, ? super V> action) {
        map.forEach((k,l) -> l.forEach(v -> action.accept(k,v)));
    }
}