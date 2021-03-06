package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FiniteAutomata {
    private List<String> setOfStates;
    private List<String> alphabet;
    private List<Transition> transitionsList;
    private List<String> finalStates;
    private boolean isDeterministic = false;
    private String fileName;

    public boolean isDeterministic() {
        return isDeterministic;
    }

    public FiniteAutomata(String fileName) {
        this.fileName = fileName;
        this.setOfStates = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.transitionsList = new ArrayList<>();
        this.finalStates = new ArrayList<>();
    }

    public void checkDeterministic() {
        for (Transition t1 : transitionsList) {
            int i = 0;
            for (Transition t2 : transitionsList) {
                if (t1.getStartState().equals(t2.getStartState()) && t1.getValue().equals(t2.getValue()))
                    i ++;
            }
            if (i >= 2) {
                isDeterministic = true;
                break;
            }
        }
    }

    public void readFromFile() throws FileNotFoundException {
        File file = new File(this.fileName);
        Scanner scanner = new Scanner(file);

        // Set of states
        String setOfStatesText = scanner.nextLine();
        String setOfStates = scanner.nextLine();
        this.setOfStates = Arrays.asList(setOfStates.split(","));

        // Alphabet
        String alphabetText = scanner.nextLine();
        String alphabet = scanner.nextLine();
        this.alphabet = Arrays.asList(alphabet.split(","));

        // Transitions
        String transitionsText = scanner.nextLine();
        String transition = "";

        //  As long as we have transitions, we should read them
        while (true) {
            transition = scanner.nextLine();
            if (transition.equals("FINAL STATES")) {
                break;
            }

            List<String> transitions = Arrays.asList(transition.split(","));
            Transition model = new Transition();
            model.setStartState(transitions.get(0));
            model.setValue(transitions.get(1));
            List<String> endStates = new ArrayList<String>();
            for (int i = 2; i < transitions.size(); i++) {
                endStates.add(transitions.get(i));
            }
            model.setEndState(endStates);

            this.transitionsList.add(model);
        }

        // Final states
        String finalStates = scanner.nextLine();
        this.finalStates = Arrays.asList(finalStates.split(","));

        scanner.close();
        checkDeterministic();
    }

    public boolean isAccepted(String sequence) {
        if(!isDeterministic) throw new RuntimeException("Can't verify sequences if the FA is not deterministic!");

        char[] charSequence = sequence.toCharArray();

        for (String state : setOfStates) {

            for (int i = 0; i < charSequence.length; i++) {

                if (!alphabet.contains(String.valueOf(charSequence[i])))
                    return false;

                for (Transition t : transitionsList) {
                    if (t.getStartState().equals(state) && t.getValue().equals(String.valueOf(charSequence[i]))) {

                        for (String endState : t.getEndState())
                            if (finalStates.contains(endState) && i == charSequence.length - 1)
                                return true;
                            break;
                    }
                }
            }
        }
        return false;
    }

    public List<String> getSetOfStates() {
        return setOfStates;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public List<Transition> getTransitionsList() {
        return transitionsList;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }
}
