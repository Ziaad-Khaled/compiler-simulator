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

class Node {


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

class NFA {
	Node start;
	Node end;
	private Hashtable<Integer, Node> nodes;
	public NFA(Node start, Node end)
	{
		this.start = start;
		this.end = end;
		nodes = new Hashtable<>();
	}

	public void addNodeToNodesList(Node node)
	{
		this.nodes.put(node.getNodeNumber(), node);
	}

	public static NFA constructAnNFA(int nodeNumber, char transition)
	{
		Node start = new Node(nodeNumber);
		Node end = new Node(nodeNumber+1);
		start.createATransition(transition, end);
		NFA nfa = new NFA(start, end);
		nfa.addNodeToNodesList(start);
		nfa.addNodeToNodesList(end);
		return nfa;
	}

	public static NFA performAConcatenation(int i, NFA nfa1, NFA nfa2) {
		Node newStart = nfa1.start;
		Node newEnd = nfa2.end;
		NFA concat = new NFA(newStart, newEnd);

		Node nfa1end = nfa1.end;
		Node nfa2Start = nfa2.start;
		ArrayList<TransitionPair> newTransitions = nfa2Start.getTransitionPairs();
		nfa1end.getTransitionPairs().addAll(newTransitions);
		Integer nfa2StartNum = nfa2Start.getNodeNumber();

		Hashtable<Integer, Node> nfa1Nodes = nfa1.getNodes();
		for (Integer key : nfa1Nodes.keySet()) {
			concat.getNodes().put(key, nfa1Nodes.get(key));
		}

		Hashtable<Integer, Node> nfa2Nodes = nfa2.getNodes();
		for (Integer key : nfa2Nodes.keySet()) {
			if(key == nfa2StartNum)
				continue;
			concat.getNodes().put(key, nfa2Nodes.get(key));
		}

		return concat;
	}

	public void performAStar(int nodeNumber)
	{
		Node newStart = new Node(nodeNumber);
		Node newEnd = new Node(nodeNumber+1);
		newStart.createATransition('e', newEnd);
		newStart.createATransition('e', this.start);
		end.createATransition('e', start);
		end.createATransition('e', newEnd);
		nodes.put(newStart.getNodeNumber(), newStart);
		nodes.put(newEnd.getNodeNumber(), newEnd);
		this.start = newStart;
		this.end = newEnd;

	}

	public static NFA performAUnion(int nodeNumber, NFA nfa1, NFA nfa2)
	{
		Node newStart = new Node(nodeNumber);
		Node newEnd = new Node(nodeNumber + 1);
		NFA union = new NFA(newStart, newEnd);
		union.addNodeToNodesList(newStart);
		union.addNodeToNodesList(newEnd);

		newStart.createATransition('e', nfa1.start);
		newStart.createATransition('e', nfa2.start);
		nfa1.end.createATransition('e', newEnd);
		nfa2.end.createATransition('e', newEnd);

		Hashtable<Integer, Node> nfa1Nodes = nfa1.getNodes();
		for (Integer key : nfa1Nodes.keySet()) {
			union.getNodes().put(key, nfa1Nodes.get(key));
		}

		Hashtable<Integer, Node> nfa2Nodes = nfa2.getNodes();
		for (Integer key : nfa2Nodes.keySet()) {
			union.getNodes().put(key, nfa2Nodes.get(key));
		}

		return union;
	}

	public Hashtable<Integer, Node> getNodes() {
		return nodes;
	}

	@Override
	public String toString() {
		return "NFA{" +
				"start=" + start.getNodeNumber() +
				", end=" + end.getNodeNumber() +
				", nodes=" + nodes;
	}
}

class TransitionPair implements Comparable<TransitionPair>{
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


