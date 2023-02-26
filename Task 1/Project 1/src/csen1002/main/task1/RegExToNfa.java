package csen1002.main.task1;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Ziad Khaled Naif
 * @id 46-20280
 * @labNumber 23
 */

public class RegExToNfa {

	/**
	 * Constructs an NFA corresponding to a regular expression based on Thompson's
	 * construction
	 * 
	 * @param input The alphabet and the regular expression in postfix notation for
	 *              which the NFA is to be constructed
	 */

	private int numberOfNodes;
	private Stack<NFA> stack ;
	private String representation ;
	private String postFix;

	public RegExToNfa(String input) {
		numberOfNodes = 0;
		stack = new Stack<>();
		representation = "";
		postFix = "";
		seperateTheInput(input);
		iterateOverPostFix();
	}

	private void seperateTheInput(String input) {
		boolean hash = false;
		for(int i=0;i<input.length();i++)
		{
			if(input.charAt(i) == '#')
			{
				hash = true;
				continue;
			}
			if(!hash)
			{
				representation += input.charAt(i);
			}
			else
			{
				postFix += input.charAt(i);
			}
		}
	}

	private void iterateOverPostFix() {
		for(int i=0; i< postFix.length(); i++)
		{
			performAnOperation(postFix.charAt(i));
		}
	}

	private void performAnOperation(char character) {
		switch (character)
		{
			case '|':
				performAUnion();
				return;
			case '*':
				performAStar();
				return;
			case '.':
				performAConcatenation();
				return;
			default:
				constructAnNFA(character);
		}
	}

	private void performAUnion() {
		NFA nfa2= stack.pop();
		NFA nfa1 = stack.pop();
		NFA union = NFA.performAUnion(numberOfNodes, nfa1, nfa2);
		numberOfNodes += 2;
		stack.push(union);
	}

	private void performAStar() {
		NFA nfa = stack.pop();
		nfa.performAStar(numberOfNodes);
		stack.push(nfa);
		numberOfNodes += 2;
	}

	private void performAConcatenation() {
		NFA nfa2 = stack.pop();
		NFA nfa1 = stack.pop();
		NFA concat = NFA.performAConcatenation(numberOfNodes, nfa1, nfa2);
		stack.push(concat);
	}

	private void constructAnNFA(char character) {
		NFA nfa = NFA.constructAnNFA(numberOfNodes, character);
		stack.push(nfa);
		numberOfNodes += 2;
	}

	/**
	 * @return Returns a formatted string representation of the NFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		NFA output = stack.pop();
		if(!stack.isEmpty())
			return null;

		String nodes = ConvertNodesToString(output);
		String transitions = ConvertTransitionsIntoString(output.getNodes());
		String start = String.valueOf(output.start.getNodeNumber());
		String end  = String.valueOf(output.end.getNodeNumber());

		return nodes + '#' +representation +  '#' + transitions+ '#'+ start + '#' + end;
	}

	private String ConvertTransitionsIntoString(Hashtable<Integer, Node> nodes) {
		TreeSet<Integer> sortedKeys = new TreeSet<>(nodes.keySet());
		String transitions = "";
		// print the sorted keys and values
		for (int key : sortedKeys) {
			Node node = nodes.get(key);
			ArrayList<TransitionPair> transitionPairs = node.getTransitionPairs();
			Collections.sort(transitionPairs);
			for (int i = 0; i < transitionPairs.size(); i++) {
				TransitionPair transitionPair = transitionPairs.get(i);
				transitions += key;
				transitions += ',';
				transitions += transitionPair.getTransition();
				transitions += ',';
				transitions += transitionPair.getTargetNumber();
				transitions += ';';
			}
		}
		return transitions.substring(0, transitions.length() - 1);
	}

	private String ConvertNodesToString(NFA output) {
		TreeSet<Integer> sortedKeys = new TreeSet<>(output.getNodes().keySet());
		String nodes = "";
		// print the sorted keys and values
		for (int key : sortedKeys) {
			nodes += key;
			nodes += ';';
		}

		return nodes.substring(0, nodes.length() - 1);
	}

}
