import java.util.Arrays;

public class HashTable<Key, Value> {

    private int size;
    private int noOfChains = 10;
    private Node[] st;

    private static class Node {
        private final Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    public HashTable() {
        st = new Node[noOfChains];
    }

    private void resize() {
        HashTable<Key, Value> temp = new HashTable<Key, Value>();
        for (int i = 0; i < noOfChains; i++) {
            for (Node x = st[i]; x != null; x = x.next) {
                temp.add((Value) x.val);
            }
        }

        this.noOfChains = temp.noOfChains;
        this.size = temp.size;
        this.st = temp.st;
    }

    private int hash(Value value) {
        return (value.hashCode()) % noOfChains;
    }

    public int getSize() {
        return size;
    }

    public boolean contains(Value value) {
        return get(value) != null;
    }

    public Key get(Value value) {
        int i = hash(value);
        for (Node x = st[i]; x != null; x = x.next) {
            if (value.equals(x.val))
                return (Key) x.key;
        }
        return null;
    }

    public void add(Value val) {
        if (size >= 10* noOfChains) resize();
        Integer key = hash(val);

        for (Node x = st[key]; x != null; x = x.next) {
            if (val.equals(x.val)) {
                x.val = val;
                return;
            }
        }
        size++;
        st[key] = new Node(key, val, st[key]);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < noOfChains; i++) {
            if (st[i] != null) {
                str.append("position ").append(i).append('\n');
                str.append(toStringST(st[i]) + '\n');
            }
        }
        return str.toString();
    }

    public String toStringST(Node st) {
        return "key: " + st.key + "; val: " + st.val;
    }
}
