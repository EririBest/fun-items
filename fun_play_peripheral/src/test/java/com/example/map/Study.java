package com.example.map;

/**
 * @author 覃汉宇
 * @date 2023/03/17/15:21
 * @brief
 */
public class Study<K,V> {
    Entry<K,V> [] table;
    private static final int CAPACITY = 8;
    private int size;

    public Object put(K key,V value){
        int hashCode = key.hashCode();
        int index = hashCode % table.length;
        index = index<0?-index:index;
        for (Entry<K,V> entry=table[index];entry!=null;entry = entry.next) {
            if (key.equals(entry.key)){
                V oldValue = entry.value;
                entry.value = value;
                return  oldValue;
            }
        }
        addEntry(key,value,index);
        return null;
    }
    public void addEntry(K kye, V value, int index) {
        table[index] = new Entry<K,V>(kye,value,table[index]);
    }

    public Study() {
        this.table = new Entry[CAPACITY];
    }

    public int getSize(){
        return size;
    }

    class Entry<K,V>{
        public K key;
        public V value;
        public Entry<K,V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
