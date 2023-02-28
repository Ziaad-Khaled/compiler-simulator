package csen1002.main.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class State {


    private NfaToDfa nfaToDfa;
    private int stateNumber;
    private ArrayList<TransitionPair> transitionPairs;
    ArrayList<Integer> epsilonTransitions;

    public State(int stateNumber, NfaToDfa nfaToDfa)
    {
        this.stateNumber = stateNumber;
        this.nfaToDfa = nfaToDfa;
        transitionPairs = new ArrayList<>();
        epsilonTransitions = new ArrayList<>();
        epsilonTransitions.add(this.stateNumber);
    }

    public void createATransition(char symbol, State target)
    {
        TransitionPair transitionPair = new TransitionPair(symbol, target);
        transitionPairs.add(transitionPair);
        if(symbol == 'e')
        {
            epsilonTransitions.add(target.getStateNumber());
        }
    }

    public void fillEpsilonTable()
    {
        for(int i=0;i<epsilonTransitions.size();i++)
        {
            int targetNumber = epsilonTransitions.get(i);
            State targetState = nfaToDfa.getStateByNumber(targetNumber);
            ArrayList<Integer> targetEpsilonTransitions = targetState.getEpsilonTransitions();
            for(int j=0 ; j< targetEpsilonTransitions.size(); j++)
            {
                int targetEpsilonTransition = targetEpsilonTransitions.get(j);
                if(!epsilonTransitions.contains(targetEpsilonTransition))
                {
                    epsilonTransitions.add(targetEpsilonTransition);
                }
            }
        }
        Collections.sort(epsilonTransitions);
    }

    public int getStateNumber() {
        return stateNumber;
    }

    public ArrayList<TransitionPair> getTransitionPairs() {
        return transitionPairs;
    }

    public ArrayList<Integer> getEpsilonTransitions() {
        return epsilonTransitions;
    }

    public HashSet<State> getTransitionTargets(char symbol)
    {
        HashSet<State> targets = new HashSet<>();
        for(int i=0;i<transitionPairs.size();i++)
        {
            if(transitionPairs.get(i).symbol == symbol)
            {
                targets.add(transitionPairs.get(i).target);
            }
        }
        return targets;
    }

    @Override
    public String toString() {
        return "transitionPairs=" + transitionPairs +
        epsilonTransitions.toString();
    }
}
