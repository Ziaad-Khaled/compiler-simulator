package csen1002.main.task2;

public class TransitionPair implements Comparable<TransitionPair>{
    char transition;
    State target;

    public TransitionPair(char transition, State target)
    {
        this.transition = transition;
        this.target = target;
    }

    public char getTransition() {
        return transition;
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
        return "transition=" + transition +
                ", target=" + target.getStateNumber();
    }

    @Override
    public int compareTo(TransitionPair other) {
        return Integer.compare(this.target.getStateNumber(), other.target.getStateNumber());
    }
}
