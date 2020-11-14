package model;

public class SymbolTable<Key, Value> {

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

    public SymbolTable() {
        st = new Node[noOfChains];
    }

    private void resize() {
        SymbolTable<Key, Value> temp = new SymbolTable<Key, Value>();
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

    public int add(Value val) {
        if (size >= 10* noOfChains) resize();
        int key = hash(val);

//        while (st[key] != null)
//            key = (key+1) % noOfChains;

        Node x = st[key];
        if (x != null && val.equals(x.val)) {
            x.val = val;
        }
        size++;
        st[key] = new Node(key, val, st[key]);

        return key;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < noOfChains; i++) {
            if (st[i] != null) {
//                str.append("position ").append(i).append('\n');
                str.append(toStringST(st[i]) + '\n');
            }
        }
        return str.toString();
    }

    public String toStringST(Node st) {
        return "position: " + st.key + "; val: " + st.val;
    }
}
