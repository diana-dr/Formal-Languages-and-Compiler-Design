import model.FiniteAutomata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RunFA {
    public static void main(String[] args) throws IOException {
        FiniteAutomata FA = new FiniteAutomata("FA/fa.txt");
        FA.readFromFile();

        while (true) {
            display_menu();
            String command = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter command: ");
            command = reader.readLine();
            Scanner scanner = new Scanner(System.in);

            switch (command) {
                case "1":
                    System.out.println("States: ");
                    System.out.println(FA.getSetOfStates());
                    System.out.println("\n");
                    break;
                case "2":
                    System.out.println("Alphabet: ");
                    System.out.println(FA.getAlphabet());
                    System.out.println("\n");
                    break;
                case "3":
                    System.out.println("Transitions: ");
                    System.out.println(FA.getTransitionsList());
                    System.out.println("\n");
                    break;
                case "4":
                    System.out.println("Final states: ");
                    System.out.println(FA.getFinalStates());
                    System.out.println("\n");
                    break;
                case "5":
                    if (FA.isDeterministic())
                        System.out.println("FA is deterministic.");
                    else System.out.println("FA is nondeterministic.");
                    System.out.println("\n");
                    break;
                case "6":
                    if(!FA.isDeterministic()) {
                        System.out.println("Can't verify sequences if the FA is not deterministic!");
                        break;
                    }
                    System.out.print("Enter the sequence: ");
                    System.out.println(FA.isAccepted(scanner.nextLine()) ? "The sequence is accepted." :
                            "The sequence is not accepted.");
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.err.println("Unrecognized option");
                    break;
            }
        }
    }

    private static void display_menu() {
        System.out.println("1 - Show states");
        System.out.println("2 - Show alphabet");
        System.out.println("3 - Show transitions");
        System.out.println("4 - Show final states");
        System.out.println("5 - Check if the FA is deterministic");
        System.out.println("6 - Check if a sequence is accepted");
        System.out.println("0 - Exit \n");
    }
}
