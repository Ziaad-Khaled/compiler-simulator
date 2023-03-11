package csen1002.main.task2;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Write your info here
 * 
 * @name Ziad Khaled Naif
 * @id 46-20280
 * @labNumber 23
 */

public class NfaToDfa {

	/**
	 * Constructs a DFA corresponding to an NFA
	 * 
	 * @param input A formatted string representation of the NFA for which an
	 *              equivalent DFA is to be constructed. The string representation
	 *              follows the one in the task description
	 */
	/*
		0;1;2;3;4;5;6;7;8;9;10#a;b#1,b,2;2,e,3;3,e,4;3,e,9;4,e,5;
		4,e,7;5,a,6;6,e,4;6,e,9;7,b,8;8,e,4;8,e,9;9,a,10#1#10
	 */
	Hashtable<Integer, State> states;
	ArrayList<Character> inputAlhpabet;
	ArrayList<NewState> newStates;
	State startState;
	NewState startNewState;
	ArrayList<State> acceptStates;

	public NfaToDfa(String input) {
		states = new Hashtable<>();
		parse(input);
		fillEpsilonTables();
		createNewStates();
		Collections.sort(newStates);
	}

	private void createNewStates() {
		newStates = new ArrayList<>();
		createNewState(startState);
		boolean numberChanged = true;

		for(int i=0;i<newStates.size();i++)
		{
			NewState newState = newStates.get(i);
			covertTransitionsIntoStates(newState);
		}
	}

	private void covertTransitionsIntoStates(NewState newState) {
		Hashtable<Character, HashSet<Integer>> transitionPairs = newState.getTransitionPairs();
		for (Map.Entry<Character, HashSet<Integer>> entry : transitionPairs.entrySet()) {
			Character key = entry.getKey();
			HashSet<Integer> value = entry.getValue();
			ArrayList<Integer> value_arr = new ArrayList<Integer>(value);
			NewState targetNewState = new NewState(value_arr, this);
			if(!newStates.contains(targetNewState))
			{
				newStates.add(targetNewState);
			}
			else
			{
				// Iterate over the set to find the object
				Iterator<NewState> iterator = newStates.iterator();
				while (iterator.hasNext()) {
					NewState o = iterator.next();
					if (o.equals(newState)) {
						// The set contains the object
						targetNewState = o;
					}
				}
			}
			newState.addStateTransitionPair(key, targetNewState);
		}
	}

	private NewState createNewState(State state) {
		ArrayList<Integer> epsilonTransitions = state.getEpsilonTransitions();
		NewState newState = new NewState(epsilonTransitions, this);
		boolean containsObject = newStates.contains(newState);

		if (containsObject) {
			// Iterate over the set to find the object
			Iterator<NewState> iterator = newStates.iterator();
			while (iterator.hasNext()) {
				NewState o = iterator.next();
				if (o.equals(newState)) {
					// The set contains the object
					return o;
				}
			}
		}
		else
		{
			newStates.add(newState);
			if(startNewState == null)
				startNewState = newState;
		}

		return newState;
	}

	private void fillEpsilonTables() {
		for (Map.Entry<Integer, State> entry : states.entrySet()) {
			State state = entry.getValue();
			state.fillEpsilonTable();
		}
	}

	private void parse(String input) {
		String[] parts = input.split("#");
		parseNodes(parts[0]);
		parseInputAlphabet(parts[1]);
		parseTransitions(parts[2]);
		assignStartState(parts[3]);
		assignacceptStates(parts[4]);

	}


	private void parseNodes(String nodes) {
		String[] nodes_arr = nodes.split(";");
		for(int i=0; i< nodes_arr.length;i++) {
			int nodeNumber = Integer.parseInt(nodes_arr[i]);
			State state = new State(nodeNumber, this);
			this.states.put(nodeNumber, state);

		}
	}

	private void parseInputAlphabet(String alphabet) {
		this.inputAlhpabet = new ArrayList<>();
		String[] alphabet_arr = alphabet.split(";");
		for(int i=0;i<alphabet_arr.length;i++)
		{
			this.inputAlhpabet.add(alphabet_arr[i].charAt(0));
		}
	}

	private void parseTransitions(String transitions) {
		String[] transitions_arr = transitions.split(";");
		for(int i=0; i< transitions_arr.length;i++)
		{
			String[] transition = transitions_arr[i].split(",");
			addATransition(transition);
		}
	}

	private void addATransition(String[] transition) {
		int stateNumber = Integer.parseInt(transition[0]);
		State source = states.get(stateNumber);
		char symbol = transition[1].charAt(0);
		State target = states.get(Integer.parseInt(transition[2]));
		source.createATransition(symbol, target);
	}

	private void assignStartState(String part) {
		int startStateNumber = Integer.parseInt(part);
		this.startState = states.get(startStateNumber);
	}

	private void assignacceptStates(String part) {
		this.acceptStates = new ArrayList<>();
		String[] acceptStates_arr = part.split(";");
		for(int i=0; i< acceptStates_arr.length;i++)
		{
			int acceptStateNumber = Integer.parseInt(acceptStates_arr[i]);
			this.acceptStates.add(states.get(acceptStateNumber));
		}

	}


	public State getStateByNumber(int stateNumber)
	{
		return states.get(stateNumber);
	}


	public ArrayList<Character> getInputAlhpabet() {
		return inputAlhpabet;
	}

	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		String output = "";
		output += newStatesToString() + "#";
		output += inputAlhpabetToString() + "#";
		output += stateTransitionsToString() + "#";
		output += startStateToString() + "#";
		output += acceptStateToString();
		return output;
	}

	private String newStatesToString() {
		String output = "";
		for(int i=0;i<newStates.size();i++)
		{
			NewState newState = newStates.get(i);
			output+=newState.stateNumbersToString() + ";";
		}
		return output.substring(0, output.length() - 1);
	}

	private String inputAlhpabetToString() {
		String output = "";
		for(int i=0;i<inputAlhpabet.size();i++)
		{
			output+=inputAlhpabet.get(i)+ ";";
		}
		return output.substring(0, output.length() - 1);
	}

	private String stateTransitionsToString() {
		String output = "";
		for(int i=0;i<newStates.size();i++)
		{
			NewState newState = newStates.get(i);
			output+=newState.stateTransitionsToString() + ";";
		}
		return output.substring(0, output.length() - 1);
	}

	private String startStateToString() {
		String output = "";
		output += startNewState.stateNumbersToString();
		return output;
	}

	private String acceptStateToString() {
		String output = "";
		HashSet<NewState> newAcceptStates = new HashSet<>();
		for(int i=0;i< acceptStates.size();i++)
		{
			Integer acceptStateNumber = acceptStates.get(i).getStateNumber();
			for(int j =0; j< newStates.size();j++)
			{
				NewState newState = newStates.get(j);
				if(newState.getStateNumbers().contains(acceptStateNumber))
				{
					newAcceptStates.add(newState);
				}
			}
		}
		ArrayList<NewState> newAcceptStates_arr = new ArrayList<NewState>(newAcceptStates);
		Collections.sort(newAcceptStates_arr);
		for (NewState element : newAcceptStates_arr) {
			output += element.stateNumbersToString() + ";";
		}

		return output.substring(0, output.length() - 1);
	}

}
class NewState implements Comparable{
	private ArrayList<Integer>  stateNumbers;
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
			ArrayList<Integer> epsilonTransitions = targetState.epsilonTransitions;
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

class State {


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

class TransitionPair implements Comparable<TransitionPair>{
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




