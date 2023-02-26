package csen1002.main.task1;

import java.util.ArrayList;

public class Node {


    private int nodeNumber;
    private ArrayList<TransitionPair> transitionPairs;

    public Node(int nodeNumber)
    {
        this.nodeNumber = nodeNumber;
        transitionPairs = new ArrayList<>();
    }

    public void createATransition(char transition, Node target)
    {
        TransitionPair transitionPair = new TransitionPair(transition, target);
        transitionPairs.add(transitionPair);
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public ArrayList<TransitionPair> getTransitionPairs() {
        return transitionPairs;
    }

    @Override
    public String toString() {
        return "transitionPairs=" + transitionPairs ;
    }
}
