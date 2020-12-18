import java.util.*;

public class ParserOutput {

    Grammar grammar;
    Map<String, Integer> noOfChildren;
    List<String> values;
    List<Integer> father;
    List<Integer> leftChild;
    List<Integer> rightSibling;

    public ParserOutput(Grammar grammar) {
        this.grammar = grammar;
        this.values = new ArrayList<>();
        this.father = new ArrayList<>();
        this.leftChild = new ArrayList<>();
        this.rightSibling = new ArrayList<>();
        this.noOfChildren = new HashMap<>();

        for (String nonTerminal : grammar.getNonTerminals()) {
            List<List<String>> productions = grammar.getProductions().get(nonTerminal);

            for (int i = 0; i < productions.size(); i++) {
                noOfChildren.put(nonTerminal + "#" + (i + 1), productions.get(i).size());
            }
        }

        for (String terminal : grammar.getTerminals()) {
            noOfChildren.put(terminal, 0);
        }
        noOfChildren.put("ε", 1);
    }

    public void addProductionString(List<String> productionString) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (String production : productionString) {
            int father = stack.pop();
            int index = add(production, father);

            for (int i = 0; i < noOfChildren.get(production); i++) {
                stack.push(index);
            }
        }
    }

    public int add(String node, int parent) {
        values.add(node);
        rightSibling.add(-1);
        leftChild.add(-1);

        int index = values.size() - 1;

        if (parent == -1)
            return index;

        if (leftChild.get(parent) == -1)
            leftChild.set(parent, index);
        else {
            int current = leftChild.get(parent);
            int previous = -1;

            while (current != -1) {
                previous = current;
                current = rightSibling.get(current);
            }
            rightSibling.set(previous, index);
        }
        father.add(parent);

        return index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Values: ");
        sb.append(this.values).append("\n");
        sb.append("Father: ");
        sb.append(this.father).append("\n");
        sb.append("Left child: ");
        sb.append(this.leftChild).append("\n");
        sb.append("Right sibling: ");
        sb.append(this.rightSibling).append("\n\n");

        if(this.values.size() == 0)
            return sb.toString();

        sb.append(subtree(0));

        return sb.toString();
    }

    private String subtree(int node) {
        StringBuilder sb = new StringBuilder();
        String value = values.get(node);

        if(value.contains("#"))
            value = value.split("#")[0];

        sb.append(value).append("\n");
        List<String> subtrees = new ArrayList<>();

        int child = leftChild.get(node);

        while (child != -1) {
            String subtreeString = subtree(child);
            String shiftedSubtree = Arrays.stream(subtreeString.split("\n")).map(l -> "\t" + l)
                    .reduce("", (s, t) -> s + t + "\n");
            String linkedSubtree = " -- " + shiftedSubtree.substring(1);
            subtrees.add(linkedSubtree);

            child = rightSibling.get(child);
        }

        List<String> B = new ArrayList<>();

        if (subtrees.size() > 0) {
            for (String a : subtrees.subList(0, subtrees.size() - 1)) {
                String b = Arrays.stream(a.split("\n")).map(l -> " \t|" + l.substring(1))
                        .reduce("", (s, t) -> s + t + "\n");
                B.add(b);
            }
            B.add(" \t\\" + subtrees.get(subtrees.size() - 1).substring(1));
        }

        B.forEach(sb::append);
        return sb.toString();
    }
}
