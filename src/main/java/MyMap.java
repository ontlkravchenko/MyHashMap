import java.util.ArrayList;
import java.util.List;

public class MyMap<K, V> {
    private ArrayList<Pair<K, V>>[] main;
    private int capacity;

    public MyMap() {
        this.main = new ArrayList[32];
        this.capacity = 32;
    }

    public void put(K key, V value) {
        List<Pair<K, V>> pairsOfKey = getListOnKey(key);
        int index = getIndexOfKey(pairsOfKey, key);

        if (index >= 0) {
            pairsOfKey.get(index).setValue(value);
        } else {
            pairsOfKey.add(new Pair<>(key, value));
        }

        if (timeForGrow()) {
            grow();
        }
    }

    public V get(K key) {
        List<Pair<K, V>> list = getListOnKey(key);
        int index = getIndexOfKey(list, key);

        if (index >= 0) {
            return list.get(index).getValue();
        } else {
            return null;
        }
    }

    public void remove(K key) {
        if (!containsKey(key)) return;

        List<Pair<K, V>> list = getListOnKey(key);
        int index = getIndexOfKey(list, key);
        list.remove(index);
    }

    public boolean containsKey(K key) {

        for (int i = 0; i < main.length; i++) {

            if (main[i] != null) {

                int index = getIndexOfKey(main[i], key);

                if (index >= 0) {
                    return true;
                }
            }
        }

        return false;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    private ArrayList<Pair<K, V>> getListOnKey(K key) {
        int index = hash(key);

        if (main[index] == null) {
            main[index] = new ArrayList<>();
        }

        return main[index];
    }

    private int getIndexOfKey(List<Pair<K, V>> list, K key) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey().equals(key)) {
                return i;
            }
        }

        return -1;
    }

    private boolean timeForGrow() {
        int count = 0;

        for (int i = 0; i < main.length; i++) {
            if (main[i] == null) {
                count++;
            }
        }

        return (count / main.length) >= 0.75;
    }

    private void grow() {
        int newCapacity = main.length * 2;

        this.capacity = newCapacity;
        ArrayList<Pair<K, V>>[] newMain = new ArrayList[newCapacity];

        for (int i = 0; i < main.length; i++) {
            copyToNew(main[i], newMain);
        }

        main = newMain;
    }

    private void copyToNew(ArrayList<Pair<K,V>> pairs, ArrayList<Pair<K,V>>[] newMain) {

        int i = 0;
        for (Pair<K, V> pair : pairs) {
            int index = hash(pair.getKey());

            if (newMain[i] == null) {
                newMain[i] = new ArrayList<>();
            }

            Pair<K, V> newPair = new Pair<>(pair.getKey(), pair.getValue());
            newMain[i].add(newPair);

            i++;
        }
    }
}

