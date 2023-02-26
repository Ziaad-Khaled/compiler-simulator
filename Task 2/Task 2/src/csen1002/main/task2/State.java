package csen1002.main.task2;

import java.util.ArrayList;

public class State {


    private int stateNumber;
    private ArrayList<TransitionPair> transitionPairs;

    public State(int stateNumber)
    {
        this.stateNumber = stateNumber;
        transitionPairs = new ArrayList<>();
    }

    public void createATransition(char transition, State target)
    {
        TransitionPair transitionPair = new TransitionPair(transition, target);
        transitionPairs.add(transitionPair);
    }

    public int getStateNumber() {
        return stateNumber;
    }

    public ArrayList<TransitionPair> getTransitionPairs() {
        return transitionPairs;
    }

    @Override
    public String toString() {
        return "transitionPairs=" + transitionPairs ;
    }
}
