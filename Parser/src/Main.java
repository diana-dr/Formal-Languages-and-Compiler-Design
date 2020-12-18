import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Grammar grammar = new Grammar("g2.txt");
        System.out.println(grammar.toString());

        RecursiveDescendent rd = new RecursiveDescendent(grammar);
        ArrayList<String> sequence = new ArrayList<>();
        sequence.add("a");
        sequence.add("a");
        sequence.add("c");
        sequence.add("b");
        sequence.add("c");

        List<String> productionString = rd.scanSequence(sequence);
        ParserOutput parserOutput = new ParserOutput(grammar);
        parserOutput.addProductionString(productionString);

//        System.out.println(parserOutput);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("out1.txt"));
        bufferedWriter.write(parserOutput.toString());
        bufferedWriter.close();

//        Grammar grammar = new Grammar("g2.txt");
//        System.out.println(grammar.toString());
//
//        RecursiveDescendent rd = new RecursiveDescendent(grammar);
//        List<String> sequenceFromPIF = sequenceFromPIF("PIF2.out");
//        System.out.println("Sequence: " + sequenceFromPIF);
//
//        List<String> productionString = rd.scanSequence(sequenceFromPIF);
//        ParserOutput parserOutput = new ParserOutput(grammar);
//        parserOutput.addProductionString(productionString);
//
////        System.out.println(parserOutput);
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("out2.txt"));
//        bufferedWriter.write(parserOutput.toString());
//        bufferedWriter.close();

//        while (true) {
//            printMenu();
//            String command = "";
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            command = reader.readLine();
//            switch (command) {
//                case "1": {
//                    for (String nt : grammar.getNonTerminals())
//                        System.out.println(grammar.toStringProduction(nt));
//                    break;
//                }
//                case "2": {
//                    String nonTerminal = "";
//                    System.out.print("Choose nonterminal: ");
//                    nonTerminal = reader.readLine();
//                    System.out.println(grammar.toStringProduction(nonTerminal));
//                    break;
//                }
//                case "0": System.exit(0);
//            }
//
//        }
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

    public static void printMenu() {
        System.out.println("1.Print all productions");
        System.out.println("2.Print productions of selected nonterminal");
        System.out.println("0.EXIT");
        System.out.print("Option: ");
    }
}
