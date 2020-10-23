package Model;

public class Pair<Token, Index> {
    private final Token token;
    private final Index index;

    public Token getToken() {
        return token;
    }

    public Index getIndex() {
        return index;
    }

    public Pair(Token t, Index i) {
        this.token = t;
        this.index = i;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "token=" + token +
                ", index=" + index +
                '}';
    }
}
