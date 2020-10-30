package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyScanner {

    public MyScanner() {}

    LanguageSpecification languageSpecification = new LanguageSpecification();

    public LanguageSpecification getLanguageSpecification() {
        return languageSpecification;
    }

    public boolean isIdentifier(String token) {

        String pattern = "[a-zA-Z]+([a-zA-Z]|[0-9])*(\\(.*\\):)?";
        return token.matches(pattern);
    }

    public boolean isOperator(String string) {
        for (String operator: languageSpecification.getOperators()) {
            if (string.equals(operator))
                return true;
        }
        return false;
    }

    public boolean isSeparator(String string) {
        for (String separator: languageSpecification.getSeparators()) {
            if (string.equals(separator))
                return true;
        }
        return false;
    }

    public boolean isConstant(String token) {

        String pattern = "^(0|[+\\-]?[1-9][0-9]*)$|^'.'$|^\".*\"$|\\[[1-9]+[0-9]*, " +
                "+[1-9]+[0-9]*]$|\\['[a-zA-Z]+',+ '[a-zA-Z]+'+]$" +
                "|\\[[1-9]+[0-9]*]$|\\['[a-zA-Z]+']$";
        return token.matches(pattern);
    }

    public boolean containsReservedWord(String string) {
        for (String word : languageSpecification.getReservedWords())
            if (string.contains(word))
                return true;
        return false;
    }

    public boolean isReservedWord(String string) {
        for (String word: languageSpecification.getReservedWords()) {
            if (string.equals(word))
                return true;
        }
        return false;
    }

    public Boolean isEscapedQuote(List<String>line, Integer index) {
        return index != 0 && line.get(index - 1).equals("\\");
    }

    public Pair<String, Integer> getStringToken(List<String>line, Integer index) {
            StringBuilder token = new StringBuilder();
            int quoteCount = 0;

            while (index < line.size() && quoteCount < 2) {
                if (line.get(index).equals("\"") && !isEscapedQuote(line, index))
                    quoteCount += 1;
                token.append(line.get(index));
                index += 1;
            }

            return new Pair<>(token.toString(), index);
    }

    public Pair<String, Integer> getOperatorToken(List<String> line, Integer index) {
        StringBuilder token = new StringBuilder();

        while (index < line.size() && isOperator(line.get(index))){
            token.append(line.get(index));
            index += 1;
        }
        return new Pair<>(token.toString(), index);
    }

    public Pair<String, Integer> getReservedWordToken(List<String> line, Integer index) {
        StringBuilder token = new StringBuilder();

        while (index < line.size() && isReservedWord(line.get(index))){
            token.append(line.get(index));
            index += 1;
        }
        return new Pair<>(token.toString(), index);
    }

    public ArrayList<String> tokenGenerator(List<String> line) {
        String token = "";
        ArrayList<String> tokens = new ArrayList<>();
        int index = 0;

        while (index < line.size()) {
//            System.out.println(line.get(index));
            if (line.get(index).equals("\"")) {
                if (!token.equals(""))
                    tokens.add(token);
                Pair<String, Integer> pair = getStringToken(line, index);
                index = pair.getIndex();
                token = pair.getToken();
                tokens.add(token);
                token = "";
            }

            else if (isOperator(line.get(index))) {
                if (!token.equals(""))
                    tokens.add(token);
                Pair<String, Integer> pair = getOperatorToken(line, index);
                index = pair.getIndex();
                token = pair.getToken();
                tokens.add(token);
                token = "";
            }

            else if (isReservedWord(line.get(index))) {
                if (!token.equals(""))
                    tokens.add(token);
                Pair<String, Integer> pair = getReservedWordToken(line, index);
                index = pair.getIndex();
                token = pair.getToken();
                tokens.add(token);
                token = "";
            }

            else if (isSeparator(line.get(index))) {
//                System.out.println(line.get(index));
                if (!token.equals(""))
                    tokens.add(token);
                Pair<String, Integer> pair = new Pair<>(line.get(index), index + 1);
                token = pair.getToken();
                index += 1;
                tokens.add(token);
                token = "";
            }

            else {
                token += line.get(index);
                index += 1;
            }
        }

        if (!token.equals(""))
            tokens.add(token);

        return tokens;
    }
}
