import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grammar {

    private List<String> terminals;
    private List<String> nonTerminals;
    private HashMap<String, List<List<String>>> productions;
    private String startSymbol;
    private String fileName;

    public Grammar(String fileName) throws IOException {
        this.terminals = new ArrayList<>();
        this.nonTerminals = new ArrayList<>();
        this.productions = new HashMap<>();
        this.fileName = fileName;
        this.readFromFile();
    }

    private void validateElement(String element) {
        if (!this.nonTerminals.contains(element) && !this.terminals.contains(element))
            throw new RuntimeException("The element '" + element + "' is not a terminal or a nonterminal!");
    }

    private void validateProductionElement(List<String> productionElements) {
        for (String element : productionElements) {
            this.validateElement(element);
        }
    }

    private void readFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.fileName));

        this.nonTerminals = Arrays.asList(reader.readLine().split(","));
        this.terminals = Arrays.asList(reader.readLine().split(","));
        this.startSymbol = reader.readLine();

        this.validateElement(this.startSymbol);

        while (true) {
            String line = reader.readLine();

            if (line == null || line.equals("")) {
                break;
            }

            List<String> production = Arrays.asList(line.strip().split("->"));
            String nonTerminal = production.get(0).strip();
            String[] rightSide = production.get(1).strip().split("\\|");

            for (String element : rightSide) {

                List<String> productionElements =  Arrays.asList(element.strip().split(" "));
                this.validateProductionElement(productionElements);
                List<List<String>> existingProductions = this.productions.get(nonTerminal);

                if (existingProductions == null) {
                    existingProductions = new ArrayList<>();
                    existingProductions.add(productionElements);
                    this.productions.put(nonTerminal, existingProductions);
                } else {
                    existingProductions.add(productionElements);
                }
            }
        }

        reader.close();
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    @Override
    public String toString() {
        return "Grammar \n" + "\nNonTerminals = " + nonTerminals + "\n\nTerminals = " + terminals + "\n\nStart Symbol = '" + startSymbol + '\'';
    }

    public String toStringProduction(String nonterminal) {
        return nonterminal +
                " = " +
                productions.get(nonterminal) + "\n";
    }
}
