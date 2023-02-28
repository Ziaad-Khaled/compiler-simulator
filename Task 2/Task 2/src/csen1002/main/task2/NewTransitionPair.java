package csen1002.main.task2;

import java.util.ArrayList;
import java.util.HashSet;

public class NewTransitionPair {
    char symbol;
    HashSet<Integer> targets;

    public NewTransitionPair(char symbol, HashSet<Integer> targets)
    {
        this.symbol = symbol;
        this.targets = targets;
    }

    @Override
    public String toString() {
        return "symbol=" + symbol +
                ", target=" + targets.toString();
    }

}
