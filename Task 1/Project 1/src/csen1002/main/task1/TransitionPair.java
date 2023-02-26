package csen1002.main.task1;

public class TransitionPair implements Comparable<TransitionPair>{
    char transition;
    Node target;

    public TransitionPair(char transition, Node target)
    {
        this.transition = transition;
        this.target = target;
    }

    public char getTransition() {
        return transition;
    }

    public Node getTarget() {
        return target;
    }

    public int getTargetNumber()
    {
        return target.getNodeNumber();
    }

    @Override
    public String toString() {
        return "transition=" + transition +
                ", target=" + target.getNodeNumber();
    }

    @Override
    public int compareTo(TransitionPair other) {
        return Integer.compare(this.target.getNodeNumber(), other.target.getNodeNumber());
    }
}
