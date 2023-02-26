package csen1002.main.task2;

import java.sql.SQLOutput;
import java.util.Hashtable;

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
	String inputAlhpabet;
	State startState;
	State endState;

	public NfaToDfa(String input) {
		states = new Hashtable<>();
		parse(input);
	}

	private void parse(String input) {
		String[] parts = input.split("#");
		parseNodes(parts[0]);
		parseInputAlphabet(parts[1]);
		parseTransitions(parts[2]);
		assignStartState(parts[3]);
		assignEndState(parts[4]);
		System.out.println(states);
	}


	private void parseNodes(String nodes) {
		String[] nodes_arr = nodes.split(";");
		for(int i=0; i< nodes_arr.length;i++) {
			int nodeNumber = Integer.parseInt(nodes_arr[i]);
			State state = new State(nodeNumber);
			this.states.put(nodeNumber, state);

		}
	}

	private void parseInputAlphabet(String inputAlphabet) {
		this.inputAlhpabet = inputAlphabet;
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

	private void assignEndState(String part) {
		int endStateNumber = Integer.parseInt(part);
		this.endState = states.get(endStateNumber);
	}







	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
