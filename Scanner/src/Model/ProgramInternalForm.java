package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProgramInternalForm<Key, Value> {

    private ArrayList<Pair<Key, Value>> content;

     public ProgramInternalForm() {
        this.content = new ArrayList<>();
    }

    public void add(Key code, Value id) {
        this.content.add(new Pair<>(code, id));
    }

    @Override
    public String toString() {
         StringBuilder str = new StringBuilder();
         for (Pair<Key, Value> pair : this.content) {
             str.append("Code: ").append(pair.getToken()).append(", ID: ").append(pair.getIndex()).append("\n");
         }
        return str.toString();
    }
}
