package main.shared;

import main.compiler.NfaToDfa;

import java.util.*;

public class NewState implements Comparable{
    private ArrayList<Integer> stateNumbers;
    private Hashtable<Character, HashSet<Integer>> transitionPairs;
    private Hashtable<Character, NewState> transitionPairs_state;

    NfaToDfa nfaToDfa;

    public NewState(ArrayList<Integer>  stateNumbers , NfaToDfa nfaToDfa)
    {
        this.stateNumbers = stateNumbers;
        this.transitionPairs_state = new Hashtable<>();
        if(stateNumbers.isEmpty())
        {
            stateNumbers.add(-1);
        }

        this.nfaToDfa = nfaToDfa;
        initializeTransitionPairs();
        fillTransitionPairs();
    }

    private void initializeTransitionPairs() {
        transitionPairs = new Hashtable<>();
        ArrayList<Character> alphabet = nfaToDfa.getInputAlhpabet();
        for(int i=0;i<alphabet.size();i++)
        {
            char symbol = alphabet.get(i);
            transitionPairs.put(symbol, new HashSet<>());
        }
    }

    private void fillTransitionPairs() {
        ArrayList<TransitionPair> stateTransitionPairs = new ArrayList<>();
        for(int i=0;i<stateNumbers.size();i++)
        {
            if(stateNumbers.get(i) == -1)
                continue;
            State state = nfaToDfa.getStateByNumber(stateNumbers.get(i));
            stateTransitionPairs.addAll(state.getTransitionPairs());
        }
        for(int j=0;j<stateTransitionPairs.size();j++)
        {
            TransitionPair transitionPair = stateTransitionPairs.get(j);
            char symbol = transitionPair.getSymbol();
            if(symbol == 'e')
                continue;
            State targetState = transitionPair.getTarget();
            ArrayList<Integer> epsilonTransitions = targetState.getEpsilonTransitions();
            HashSet<Integer> targets = this.transitionPairs.get(symbol);
            targets.addAll(epsilonTransitions);
//            System.out.println(symbol);
//            System.out.println(transitionPair);
//            System.out.println(this.transitionPairs.toString());
        }
    }

    public void addStateTransitionPair(char symbol, NewState newState)
    {
        transitionPairs_state.put(symbol, newState);
    }

    public String stateNumbersToString()
    {
        String output = "";
        for(int i=0; i<stateNumbers.size();i++)
        {
            output+= stateNumbers.get(i) + "/";
        }
        return output.substring(0, output.length() - 1);
    }

    public String stateTransitionsToString() {
        String output = "";
        ArrayList<Character> keys = new ArrayList<Character>(transitionPairs.keySet());
        Collections.sort(keys);
        for (Character key : keys) {
            output+= this.stateNumbersToString() + ",";
            output+= key;
            output += ",";
            HashSet<Integer> value = transitionPairs.get(key);
            output+= targetStateToString(value) + ";";
        }
        return output.substring(0, output.length() - 1);
    }

    private String targetStateToString(HashSet<Integer> value) {
        if(value.size() == 0)
        {
            value.add(-1);
        }
        String output = "";
        for (Integer element : value) {
            output += element.toString() + "/";
        }
        return output.substring(0, output.length() - 1);
    }

    public ArrayList<Integer> getStateNumbers() {
        return stateNumbers;
    }

    public Hashtable<Character, HashSet<Integer>> getTransitionPairs() {
        return transitionPairs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewState newState = (NewState) o;
        return stateNumbers.equals(newState.stateNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateNumbers, transitionPairs, nfaToDfa);
    }



    @Override
    public String toString() {
        String output ="state numbers= " + stateNumbers.toString() + "transitionPairs=[";
        ArrayList<Character> keys = new ArrayList<Character>(transitionPairs.keySet());
        Collections.sort(keys);
        for (Character key : keys) {
            HashSet<Integer> value = transitionPairs.get(key);
            output+= key.toString() + value.toString() + ",";
        }
        return output + "]";
    }


    @Override
    public int compareTo(Object o) {
        int sizeDiff = this.getStateNumbers().size() - ((NewState) o).getStateNumbers().size();
        int size;
        if(sizeDiff < 0)
        {
            size = this.getStateNumbers().size();
        }
        else
        {
            size = ((NewState) o).getStateNumbers().size();
        }
        for (int i = 0; i < size; i++) {
            int thisElement = this.getStateNumbers().get(i);
            int otherElement = ((NewState) o).getStateNumbers().get(i);
            if (thisElement != otherElement) {
                return thisElement - otherElement;
            }
        }
        if (sizeDiff != 0) {
            return sizeDiff;
        }
        return 0;
    }
}
