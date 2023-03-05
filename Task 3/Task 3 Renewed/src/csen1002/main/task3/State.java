package csen1002.main.task3;

import java.util.ArrayList;

public class State {


    private int stateNumber;
    private ArrayList<TransitionPair> transitionPairs;

    public State(int stateNumber)
    {
        this.stateNumber = stateNumber;
        transitionPairs = new ArrayList<>();
    }

    public void createATransition(char symbol, State target)
    {
        TransitionPair transitionPair = new TransitionPair(symbol, target);
        transitionPairs.add(transitionPair);
    }

    public int getTarget(char transition)
    {
        for(int i=0; i< transitionPairs.size();i++)
        {
            if(transition == transitionPairs.get(i).getSymbol())
            {
                return transitionPairs.get(i).getTargetNumber();
            }
        }
        return -1;
    }

    public int getStateNumber() {
        return stateNumber;
    }

    public ArrayList<TransitionPair> getTransitionPairs() {
        return transitionPairs;
    }

    @Override
    public String toString() {
        return "transitionPairs=" + transitionPairs;
    }
}
