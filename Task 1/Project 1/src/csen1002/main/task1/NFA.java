package csen1002.main.task1;

import java.util.ArrayList;
import java.util.Hashtable;

public class NFA {
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
