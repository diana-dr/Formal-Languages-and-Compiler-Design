public class Main {
    public static void main(String[] args) {
        HashTable<Integer, String> st = new HashTable();

        st.add("abc");
        st.add("def");
        st.add("jkl");

        System.out.println(st.toString());
    }
}
