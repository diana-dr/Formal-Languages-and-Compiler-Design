import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Grammar grammar = new Grammar("g1.txt");
        System.out.println(grammar.toString());

        while (true) {
            printMenu();
            String command = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            command = reader.readLine();
            switch (command) {
                case "1": {
                    for (String nt : grammar.getNonTerminals())
                        System.out.println(grammar.toStringProduction(nt));
                    break;
                }
                case "2": {
                    String nonTerminal = "";
                    System.out.print("Choose nonterminal: ");
                    nonTerminal = reader.readLine();
                    System.out.println(grammar.toStringProduction(nonTerminal));
                    break;
                }
                case "0": System.exit(0);
        }

        }
    }

    public static void printMenu() {
        System.out.println("1.Print all productions");
        System.out.println("2.Print productions of selected nonterminal");
        System.out.println("0.EXIT");
        System.out.print("Option: ");
    }
}
