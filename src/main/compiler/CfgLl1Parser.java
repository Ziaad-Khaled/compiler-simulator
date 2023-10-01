package main.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


public class CfgLl1Parser {

	List<ParserVariable> parserVariables;
	List<Character> terminals;
	ParsingTable parsingTable;
	String parsedCharacters;
	Stack<Character> inputStack;
	String parsingInput;
	boolean reduce = false;
	boolean error = false;

	/**
	 * Constructs a Context Free Grammar
	 * 
	 *  formatted string representation of the CFG, the First sets of
	 *            each right-hand side, and the Follow sets of each variable. The
	 *            string representation follows the one in the task description
	 */
	public CfgLl1Parser(String input) {
		parsedCharacters = "";
		String[] parts = input.split("#");
		parseVariables(parts[0]);
		parseTerminals(parts[1]);
		parseRules(parts[2]);
		parseFirst(parts[3]);
		parseFollow(parts[4]);
		createParsingTable();
	}


	private void parseVariables(String part) {
		this.parserVariables = new ArrayList<>();
		String[] variables_arr = part.split(";");
		for(int i=0;i<variables_arr.length;i++)
		{
			Character name = variables_arr[i].charAt(0);
			ParserVariable parserVariable = new ParserVariable(this, name);
			this.parserVariables.add(parserVariable);
		}
	}

	private void parseTerminals(String part) {
		this.terminals = new ArrayList<>();
		String[] terminals_arr = part.split(";");
		for(int i=0;i<terminals_arr.length;i++)
		{
			Character name = terminals_arr[i].charAt(0);
			this.terminals.add(name);
		}
	}

	private void parseRules(String part) {
		String[] rules = part.split(";");
		for(int i=0;i<rules.length;i++)
		{
			String[] rulesForAVariable = rules[i].split("/");
			Character variable_name = rulesForAVariable[0].charAt(0);
			ParserVariable parserVariable = getVariableByName(variable_name);
			String[] rightHandSide = rulesForAVariable[1].split(",");
			for(int j=0; j< rightHandSide.length;j++)
			{
				String rule = rightHandSide[j];
				parserVariable.addRule(rule);
			}
		}
	}

	private void parseFirst(String part) {
		String[] VariablesToFirst = part.split(";");
		for(int i=0;i<VariablesToFirst.length;i++)
		{
			String[] variableToFirst = VariablesToFirst[i].split("/");
			Character variable_name = variableToFirst[0].charAt(0);
			ParserVariable parserVariable = getVariableByName(variable_name);
			String[] firsts = variableToFirst[1].split(",");
			for(int j=0; j< firsts.length;j++)
			{
				for(int k=0;k< firsts[j].length();k++) {
					Character first = firsts[j].charAt(k);
					parserVariable.addFirst(first);
				}
			}
		}
	}

	private void parseFollow(String part) {
		String[] VariablesToFollow = part.split(";");
		for(int i=0;i<VariablesToFollow.length;i++)
		{
			String[] variableToFollow = VariablesToFollow[i].split("/");
			Character variable_name = variableToFollow[0].charAt(0);
			ParserVariable parserVariable = getVariableByName(variable_name);
			String follows = variableToFollow[1];
			for(int j=0; j< follows.length();j++)
			{
				Character follow = follows.charAt(j);
				parserVariable.addFollow(follow);
			}
		}
	}

	private void createParsingTable()
	{
		this.parsingTable = new ParsingTable(this, this.parserVariables, this.terminals);
	}

	public ParserVariable getVariableByName(Character character)
	{
		for(int i = 0; i< parserVariables.size(); i++)
		{
			if(parserVariables.get(i).getName().equals(character))
			{
				return parserVariables.get(i);
			}
		}

		return null;
	}

	/**
	 * @param input The string to be parsed by the LL(1) CFG.
	 * 
	 * @return A string encoding a left-most derivation.
	 */
	public String parse(String input) {
		parsingInput = input;
		String output = "S";
		inputStack = convertInputIntoStack(input);
		Stack<Character> pdaStack = new Stack<>();
		pdaStack.push('S');
		while(!inputStack.isEmpty())
		{
			reduce = false;
			pdaStack = changePdaStack(pdaStack, inputStack.peek());
			if(error)
				break;
			output += convertPdaStackToString(pdaStack);
		}
		if(cannotParse(pdaStack))
			output += ";"+"ERROR";
		else
			output += ";" + parsedCharacters;
		return output;
	}

	private boolean cannotParse(Stack<Character> pdaStack) {

		boolean pdaStackEmpty = isPdaStackEmpty(pdaStack);
		return error || !parsedCharacters.equals(parsingInput) || !pdaStackEmpty;
	}

	private boolean isPdaStackEmpty(Stack<Character> pdaStack) {
		if(error)
			return true;
		while(!pdaStack.isEmpty())
		{
			Character pdaPeek = pdaStack.pop();
			if(Character.isLowerCase(pdaPeek))
				return false;
			else
			{
				ParserVariable parserVariable = getVariableByName(pdaPeek);
				ArrayList<String> rules = parserVariable.getRules();
				if(!rules.contains("e"))
					return false;
			}
		}
		return true;
	}

	private Stack<Character> convertInputIntoStack(String input) {
		Stack<Character> stack = new Stack<>();
		for(int i=input.length()-1;i>=0;i--)
		{
			stack.push(input.charAt(i));
		}
		return stack;
	}

	private Stack<Character> changePdaStack(Stack<Character> pdaStack, Character inputPeek) {
		if(pdaStack.isEmpty())
		{
			error = true;
			 return null;
		}
		Character pdaPeek = pdaStack.peek();
		if(Character.isLowerCase(pdaPeek))
		{
			pdaStack = changePdaStackForLowerCase(pdaStack, inputPeek);
		}
		else
		{
			pdaStack = changePdaStackForUpperCase(pdaStack, inputPeek);
		}

		return pdaStack;
	}

	private Stack<Character> changePdaStackForLowerCase(Stack<Character> pdaStack, Character inputPeek) {
		Character pdaPeek = pdaStack.peek();
		if(pdaPeek == inputPeek)
		{
			pdaStack.pop();
			inputStack.pop();
			reduce = true;
			parsedCharacters += pdaPeek;
		}
		else
		{
			pdaStack = assignErrorToStack();
		}
		return pdaStack;
	}


	private Stack<Character> changePdaStackForUpperCase(Stack<Character> pdaStack, Character inputPeek) {
		Character pdaPeek = pdaStack.pop();
		ParserVariable parserVariable = getVariableByName(pdaPeek);
		String rule = parsingTable.getRule(parserVariable, inputPeek);
		if(rule == null)
		{
			pdaStack = assignErrorToStack();
			return pdaStack;
		}
		pdaStack = assignRuleToStack(pdaStack, rule);
		return pdaStack;
	}

	private Stack<Character> assignRuleToStack(Stack<Character> pdaStack, String rule) {
		if(rule.charAt(0) == 'e')
			return pdaStack;
		for(int i=rule.length()-1;i>=0;i--)
		{
			Character character = rule.charAt(i);
			pdaStack.push(character);
		}
		return pdaStack;
	}


	private Stack<Character> assignErrorToStack() {
		error = true;
		Stack<Character> pdaStack = new Stack<>();
		pdaStack.push('R');
		pdaStack.push('O');
		pdaStack.push('R');
		pdaStack.push('R');
		pdaStack.push('E');
		return pdaStack;
	}

	private String convertPdaStackToString(Stack<Character> pdaStack) {
		if(reduce)
			return "";
		Stack<Character> clonedStack = new Stack<>();
		for (Character i : pdaStack) {
			clonedStack.push(i);
		}
		String output = "";
		output += parsedCharacters;
		while(!clonedStack.isEmpty())
		{
			output += clonedStack.pop();
		}
		if(output.equals(parsingInput))
			return "";
		return ";" + output;
	}


	public void printParsingTable()
	{
		this.parsingTable.printParsingTable();
	}

}

class ParsingTable {
	CfgLl1Parser cfgLl1Parser;
	List<ParserVariable> parserVariables;
	List<Character> terminals;
	int[][] parsingTable;

	public ParsingTable(CfgLl1Parser cfgLl1Parser, List<ParserVariable> parserVariables, List<Character> terminals) {
		this.cfgLl1Parser = cfgLl1Parser;
		this.parserVariables = parserVariables;
		this.terminals = terminals;
		this.terminals.add('$');
		int numberOfVariables = this.parserVariables.size();
		int numberOfTerminals = this.terminals.size();
		this.parsingTable = new int[numberOfVariables][numberOfTerminals];
		initializeParsingTableValues();
		assignValuesToParsingTable();
	}

	private void initializeParsingTableValues() {
		for(int i=0;i<parsingTable.length;i++)
		{
			for(int j=0;j<parsingTable[0].length;j++)
			{
				parsingTable[i][j] = -1;
			}
		}
	}


	private void assignValuesToParsingTable() {
		for(int i=0;i<parsingTable.length;i++)
		{
			ParserVariable parserVariable = this.parserVariables.get(i);
			ArrayList<String> rules = parserVariable.getRules();
			if(rules.contains("e"))
				assignValuesToParsingTableUsingFollow(parserVariable, rules.indexOf("e"));
			List<Character> first = parserVariable.getFirst();
			assignRulesForFirst(parserVariable, first);
		}
	}

	private void assignRulesForFirst(ParserVariable parserVariable, List<Character> first) {
		int variableIndex = parserVariables.indexOf(parserVariable);

		for(int i=0;i<first.size();i++)
		{
			Character terminal = first.get(i);
			int terminalIndex = terminals.indexOf(terminal);
			int rule = getTheRuleForTheTerminal(parserVariable, terminal);
			if(terminal != 'e')
				parsingTable[variableIndex][terminalIndex] = rule;
		}
	}


	private int getTheRuleForTheTerminal(ParserVariable parserVariable, Character terminal) {
		ArrayList<String> rules = parserVariable.getRules();
		for (int i=0;i<rules.size();i++)
		{
			String rule = rules.get(i);
			List<Character> firstOfTheRule = computerFirstOfTheRule(rule);
			if(firstOfTheRule.contains(terminal))
				return i;
		}
		return -1;
	}

	private List<Character> computerFirstOfTheRule(String rule) {
		List<Character> first = new ArrayList<>();
		for(int i=0;i<rule.length();i++)
		{
			Character character = rule.charAt(i);
			if(Character.isLowerCase(character))
			{
				first.add(character);
				break;
			}
			else
			{
				ParserVariable parserVariable = cfgLl1Parser.getVariableByName(character);
				List<Character> variableFirst = parserVariable.getFirst();
				first.addAll(variableFirst);
				List<String> variableRules = parserVariable.getRules();
				if(!variableRules.contains("e"))
					break;
			}
		}
		return first;
	}

	private void assignValuesToParsingTableUsingFollow(ParserVariable parserVariable, int epsilonIndex) {
		int variableIndex = parserVariables.indexOf(parserVariable);
		List<Character> follow = parserVariable.getFollow();
		for(int j=0;j<this.terminals.size();j++)
		{
			Character terminal = this.terminals.get(j);

			if(follow.contains(terminal))
			{
				this.parsingTable[variableIndex][j] = epsilonIndex;
			}
		}
	}


	public void printParsingTable()
	{
		System.out.print(" ");
		for (int i=0;i<terminals.size();i++)
		{
			System.out.print(" | ");
			System.out.print(terminals.get(i));
		}
		System.out.println();
		for(int i=0;i<parsingTable.length;i++)
		{
			Character variableName = this.parserVariables.get(i).getName();
			System.out.print(variableName);
			for (int j=0;j<parsingTable[i].length;j++)
			{
				if(parsingTable[i][j] < 0)
					System.out.print(" |");
				else
					System.out.print(" | ");
				System.out.print(parsingTable[i][j]);
			}
			System.out.println();
		}
	}

	public String getRule(ParserVariable parserVariable, Character terminal) {
		int variableIndex = this.parserVariables.indexOf(parserVariable);
		int terminalIndex = this.terminals.indexOf(terminal);
		int ruleIndex = this.parsingTable[variableIndex][terminalIndex];
		ArrayList<String> rules = parserVariable.getRules();
		if(ruleIndex < 0)
			return null;
		String rule = rules.get(ruleIndex);
		return rule;
	}
}

class ParserVariable {

	private CfgLl1Parser cfgLl1Parser;
	private Character name;
	private ArrayList<String> rules;
	private List<Character> first;
	private List<Character> follow;

	public ParserVariable(CfgLl1Parser cfgLl1Parser, Character name)
	{
		this.cfgLl1Parser = cfgLl1Parser;
		this.name = name;
		this.rules = new ArrayList<>();
		this.first = new ArrayList<>();
		this.follow = new ArrayList<>();
	}

	public void addRule(String rule)
	{
		rules.add(rule);
	}
	public void addFirst(char character)
	{
		first.add(character);
	}

	public void addFollow(char character)
	{
		follow.add(character);
	}

	public List<Character> getFirst()
	{
		return this.first;
	}
	public List<Character> getFollow()
	{
		return this.follow;
	}

	public Character getName() {
		return name;
	}

	public ArrayList<String> getRules() {
		return rules;
	}

	public String firstToString()
	{
		String output = "";
		ArrayList<Character> first_arr = new ArrayList<Character>(first);
		Collections.sort(first_arr);
		for(int i=0;i<first.size();i++)
		{
			output += first_arr.get(i);
		}
		return output;
	}

	public String followToString() {
		String output = "";
		ArrayList<Character> follow_arr = new ArrayList<Character>(follow);
		Collections.sort(follow_arr);
		for(int i=0;i<follow.size();i++)
		{
			output += follow_arr.get(i);
		}
		return output;
	}

	@Override
	public String toString() {
		String output = name + "";
		output += "/";
		for(int i=0;i<rules.size();i++)
		{
			output+= rules.get(i);
			output += ",";
		}
		if(output.charAt(output.length()-1) == '/')
			output += "/";
		return  output.substring(0, output.length()-1);
	}

}

