package recursiveDescendant;

import utilities.Grammar;

import java.util.*;

public class RecursiveDescendant {

    public Configuration configuration;
    public Grammar grammar;

    public RecursiveDescendant(Grammar grammar) {
        this.grammar = grammar;

        // q - normal state
        configuration = new Configuration("q", 1, new Stack<>(), new Stack<>());

        configuration.workingStack.push("ε");

        configuration.inputStack.push("ε");
        configuration.inputStack.push(grammar.getStartSymbol());
    }

    public List<String> run(List<String> w) throws Exception {

        // f - final state
        // e - error state
        while (!configuration.state.equals("f") && !configuration.state.equals("e")) {
//            System.out.println(configuration);
            String inputTop = configuration.inputStack.peek();
            String workingTop = configuration.workingStack.peek();

            if (configuration.position == w.size() + 1 && configuration.inputStack.peek().equals("ε")) {
                System.out.println("success");
                success();
            }

            if (configuration.state.equals("q")) {
                if (grammar.getNonTerminals().contains(inputTop)) {
                    System.out.println("expand");
                    expand();
                } else if (w.size() >= configuration.position && w.get(configuration.position - 1).equals(inputTop)) {
                    System.out.println("advance");
                    advance();
                } else if ((w.size() < configuration.position || !w.get(configuration.position - 1).equals(inputTop))) {
                    System.out.println("momentary insuccess");
                    momentaryInsuccess();
                }
                // b - back state
            } else if (configuration.state.equals("b")) {
                if (!grammar.getTerminals().contains(workingTop)) {
                    System.out.println("another try");
                    anotherTry();
                } else {
                    System.out.println("back");
                    back();
                }
            }
        }
        if (configuration.state.equals("f")) {
            System.out.println("Sequence accepted\n");
            return configuration.workingStack;
        } else {
            throw new Exception("Syntax Error");
        }
    }

    public void expand() {
        String nonTerminal = configuration.inputStack.pop();
        List<String> result = new ArrayList<>(grammar.productions.get(nonTerminal).get(0));
        Collections.reverse(result);
        result.forEach(s -> configuration.inputStack.push(s));
        configuration.workingStack.add(nonTerminal + "#" + "1");
    }

    public void advance() {
        configuration.position++;
        String terminal = configuration.inputStack.pop();
        configuration.workingStack.push(terminal);
    }

    public void momentaryInsuccess() {
        configuration.state = "b";
    }

    public void back() {
        configuration.position--;
        configuration.inputStack.push(configuration.workingStack.pop());
    }

    public void anotherTry() {
        String topWorking = configuration.workingStack.peek();
        List<String> topWorkingList = Arrays.asList(topWorking.split("#"));

        List<String> grammar = this.grammar.productions.get(topWorkingList.get(0)).get(Integer.parseInt(topWorkingList.get(1)) - 1);

        if (Integer.parseInt(topWorkingList.get(1)) != this.grammar.productions.get(topWorkingList.get(0)).size()) {
            configuration.state = "q";

            grammar.forEach(s -> configuration.inputStack.pop());
            configuration.workingStack.pop();
            configuration.workingStack.push(topWorkingList.get(0) + "#" + (Integer.parseInt(topWorkingList.get(1)) + 1));

            List<String> gammar2 = new ArrayList<>(this.grammar.productions.get(topWorkingList.get(0)).
                    get(Integer.parseInt(topWorkingList.get(1))));
            Collections.reverse(gammar2);
            gammar2.forEach(s -> configuration.inputStack.push(s));

        } else if (configuration.position == 1 && topWorkingList.get(0).equals(this.grammar.getStartSymbol())) {
            configuration.state = "e";
        } else {
            configuration.workingStack.pop();
            grammar.forEach(s -> configuration.inputStack.pop());
            configuration.inputStack.push(topWorkingList.get(0));
        }
    }

    public void success() {
        configuration.state = "f";
    }
}
