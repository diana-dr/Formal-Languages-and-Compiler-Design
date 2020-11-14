package model;

import java.lang.reflect.Array;
import java.util.*;

public class LanguageSpecification {

    private List<String> separators;
    private List<String> operators;
    private ArrayList<String> reservedWords;

    public LanguageSpecification() {
        separators = new ArrayList<>(Arrays.asList("[", "]", "{", "}", "(", ")", " ", ";",  "\t", ":"));
        operators = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "%", "<", "<=", "=", ">=", ">",
                    "==", "&&", "||", "!", "!=", "&", "|", "^", "+=", "-=", ",", "<-"));
        reservedWords = new ArrayList<>(Arrays.asList("char", "const", "do", "double", "else", "float", "for", "print",
                        "if", "int", "long", "short", "void", "while", "function", "in", "foreach"));

    }

    public List<String> getSeparators() {
        return separators;
    }

    public List<String> getOperators() {
        return operators;
    }

    public ArrayList<String> getReservedWords() {
        return reservedWords;
    }
}
