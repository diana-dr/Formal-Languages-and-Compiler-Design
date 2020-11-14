package model;

import java.util.List;

public class Transition {
    private String startState;
    private String value;
    private List<String> endState;

    public Transition() {
    }

    public String getValue() {
        return value;
    }

    public String getStartState() {
        return startState;
    }

    public List<String> getEndState() {
        return endState;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setEndState(List<String> endState) {
        this.endState = endState;
    }

    @Override
    public String toString() {
        return "δ(" + startState + "," + value + ") = " + endState + "\n";
    }
}
