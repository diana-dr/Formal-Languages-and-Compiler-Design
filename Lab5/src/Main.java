import recursiveDescendant.RecursiveDescendant;
import utilities.Grammar;
import utilities.ParserOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Grammar grammar = Grammar.readFromFile("g1.txt");
            System.out.println("Grammar:");
            printGrammar(grammar);

            RecursiveDescendant algorithm = new RecursiveDescendant(grammar);
            List<String> w = sequenceFromFile("inputSeq.txt");
            System.out.println("Sequence: " + w + "\n");

            List<String> productionString = algorithm.run(w);
            ParserOutput parserOutput = new ParserOutput(grammar);
            parserOutput.addProductionString(productionString);

            System.out.println(parserOutput);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("out1.txt"));
            bufferedWriter.write(parserOutput.toString());
            bufferedWriter.close();

//            Grammar grammar = Grammar.readFromFile("g2.txt");
//            printGrammar(grammar);
//
//            RecursiveDescendant algorithm = new RecursiveDescendant(grammar);
//            List<String> w = sequenceFromPIF("PIF.out");
//            System.out.println("Sequence: " + w);
//
//            List<String> productionString = algorithm.run(w);
//            ParserOutput parserOutput = new ParserOutput(grammar);
//            parserOutput.addProductionString(productionString);
//
//            System.out.println(parserOutput);
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("out2.txt"));
//            bufferedWriter.write(parserOutput.toString());
//            bufferedWriter.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> sequenceFromPIF(String pifFileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(pifFileName)));
        List<String> w = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            w.add(line.substring(0, line.indexOf(" -> ")));
        }
        return w;
    }

    public static List<String> sequenceFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
        return Arrays.asList(scanner.nextLine().split(" "));
    }

    public static void printGrammar(Grammar grammar) {
        System.out.println("Nonterminals:" + grammar.getNonTerminals());
        System.out.println("Terminals:" + grammar.getTerminals());
        System.out.println("Productions:\n" + grammar.getProductions());
        System.out.println("Production for S:");
        System.out.println(grammar.getForProduction("S"));
        System.out.println();
    }
}
