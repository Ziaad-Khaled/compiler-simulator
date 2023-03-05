package csen1002.main.task3;

public class TransitionPair implements Comparable<TransitionPair>{
    char symbol;
    State target;

    public TransitionPair(char transition, State target)
    {
        this.symbol = transition;
        this.target = target;
    }

    public char getSymbol() {
        return symbol;
    }

    public State getTarget() {
        return target;
    }

    public int getTargetNumber()
    {
        return target.getStateNumber();
    }

    @Override
    public String toString() {
        return "transition=" + symbol +
                ", target=" + target.getStateNumber();
    }

    @Override
    public int compareTo(TransitionPair other) {
        return Integer.compare(this.target.getStateNumber(), other.target.getStateNumber());
    }
}
