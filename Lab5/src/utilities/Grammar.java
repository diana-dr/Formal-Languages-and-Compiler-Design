package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Grammar {

    public List<String> nonTerminals;
    public List<String> terminals;
    public Map<String, List<List<String>>> productions;
    public String startSymbol;

    public Grammar() {
        nonTerminals = new ArrayList<>();
        terminals = new ArrayList<>();
        productions = new HashMap<>();
        startSymbol = "";
    }

    public static Grammar readFromFile(String fileName) throws Exception {
        Grammar grammar = new Grammar();
        Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));

        String nonterminalsLine = sc.nextLine();
        grammar.nonTerminals = Arrays.asList(nonterminalsLine.split(" "));

        String terminalsLine = sc.nextLine();
        grammar.terminals = new ArrayList<>(Arrays.asList(terminalsLine.split(" ")));
        grammar.startSymbol = sc.nextLine();
        String line;

        for (String nonterminal : grammar.nonTerminals) {
            grammar.productions.put(nonterminal, new ArrayList<>());
        }

        while (sc.hasNext()) {
            line = sc.nextLine();
            String[] parts = line.split("->");
            String[] productions = parts[1].split("\\|");
            for (String production : productions) {
                String[] symbols = production.split(" ");
                grammar.productions.get(parts[0]).add(Arrays.asList(symbols));
            }

        }
        sc.close();

        if(!grammar.validate()){
            throw new Exception("Invalid grammar");
        }
        return grammar;
    }

    public boolean validate() {
        if (!nonTerminals.contains(startSymbol)) {
            return false;
        }
        for (String key : productions.keySet()) {
            if (!nonTerminals.contains(key)) {
                return false;
            }
        }
        for (List<List<String>> rhs: productions.values()){
            for (List<String> values: rhs){
                for (String value: values){
                    if(!(nonTerminals.contains(value) || terminals.contains(value))){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public String getStartSymbol() {
        return startSymbol;
    }

    public List<String> getNonTerminals() {
        return this.nonTerminals;
    }

    public List<String> getTerminals() {
        return this.terminals;
    }

    public String getProductions() {
        return productions.keySet().stream().map(this::getForProduction).collect(Collectors.joining("\n"));
    }

    public String getForProduction(String nonterminal) {
        List<List<String>> productions = this.productions.get(nonterminal);
        StringBuilder sb = new StringBuilder();

        sb.append(nonterminal).append("->");

        List<String> aux = new ArrayList<>();

        productions.forEach(p -> aux.add(String.join(" ", p)));
        sb.append(String.join("|", aux));

        return sb.toString();
    }
}
