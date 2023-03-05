package csen1002.main.task3;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

/**
 * Write your info here
 *
 * @name Ziad Khaled
 * @id 46-20280
 * @labNumber 23
 */

public class FallbackDfa {

	Hashtable<Integer, State> states;
	ArrayList<Character> inputAlhpabet;
	State startState;
	ArrayList<Integer> acceptStates;
	ArrayList<OutputPair> outputPairs;

	/**
	 * Constructs a Fallback DFA
	 *
	 * @param fdfa A formatted string representation of the Fallback DFA. The string
	 *             representation follows the one in the task description
	 */
	public FallbackDfa(String fdfa) {

		// TODO Auto-generated constructor stub
		parse(fdfa);
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
		this.states = new Hashtable<>();
		String[] nodes_arr = nodes.split(";");
		for(int i=0; i< nodes_arr.length;i++) {
			int nodeNumber = Integer.parseInt(nodes_arr[i]);
			State state = new State(nodeNumber);
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
			this.acceptStates.add(acceptStateNumber);
		}

	}

	private void addATransition(String[] transition) {
		int stateNumber = Integer.parseInt(transition[0]);
		State source = states.get(stateNumber);
		char symbol = transition[1].charAt(0);
		State target = states.get(Integer.parseInt(transition[2]));
		source.createATransition(symbol, target);
	}

	/**
	 * @param input The string to simulate by the FDFA.
	 *
	 * @return Returns a formatted string representation of the list of tokens. The
	 *         string representation follows the one in the task description
	 */
	public String run(String input) {
		outputPairs = new ArrayList<>();
		int length = input.length();
		int l = 0;
		int r = 0;
		Stack<Integer> stack = new Stack<>();

		while (r < length)
		{
			stack = fillStack(stack, input, l);
			l = input.length();
			input = emptyStack(stack, input, l);
			r = 0;
			l=0;
			length = input.length();
			stack.clear();
		}
		return toString();
	}

	private String emptyStack(Stack<Integer> stack, String input, int l) {
		boolean accepted = false;
		int state = -1;
		int startingState = -1;
		while(!accepted)
		{
			try
			{
				state = stack.pop();
			}
			catch (Exception e)
			{
				accepted = true;
				state = startingState;
				l = input.length();
			}
			if(startingState == -1)
			{
				startingState = state;
			}
			if(isAccepted(state))
				accepted = true;
			l--;
		}

		String output = input.substring(0, l+1);
		OutputPair outputPair = new OutputPair(state, output);
		outputPairs.add(outputPair);

		if(l+1 == input.length())
			return "";

		input = input.substring(l+1, input.length());
		return input;
	}

	private boolean isAccepted(int state)
	{
		if(acceptStates.contains(state))
			return true;
		else
			return false;
	}

	private Stack<Integer> fillStack(Stack<Integer> stack, String input, int l) {
		State currentState = startState;
		while(l < input.length())
		{
			char transition = input.charAt(l);
			int target = currentState.getTarget(transition);
			currentState = states.get(target);
			stack.push(target);
			l++;
		}
		return stack;
	}

	@Override
	public String toString() {
		String output = "";
		for (int i=0;i<outputPairs.size();i++)
		{
			output += outputPairs.get(i).toString();
			output += ";";
		}
		return output.substring(0, output.length()-1);
	}
}
