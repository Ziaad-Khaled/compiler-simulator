package csen1002.main.task7;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Ziad Khaled Naif
 * @id 46-20280
 * @labNumber 22
 */

public class CfgLl1Parser {

	List<Variable> variables;
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
		this.variables = new ArrayList<>();
		String[] variables_arr = part.split(";");
		for(int i=0;i<variables_arr.length;i++)
		{
			Character name = variables_arr[i].charAt(0);
			Variable variable = new Variable(this, name);
			this.variables.add(variable);
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
			Variable variable = getVariableByName(variable_name);
			String[] rightHandSide = rulesForAVariable[1].split(",");
			for(int j=0; j< rightHandSide.length;j++)
			{
				String rule = rightHandSide[j];
				variable.addRule(rule);
			}
		}
	}

	private void parseFirst(String part) {
		String[] VariablesToFirst = part.split(";");
		for(int i=0;i<VariablesToFirst.length;i++)
		{
			String[] variableToFirst = VariablesToFirst[i].split("/");
			Character variable_name = variableToFirst[0].charAt(0);
			Variable variable = getVariableByName(variable_name);
			String[] firsts = variableToFirst[1].split(",");
			for(int j=0; j< firsts.length;j++)
			{
				Character first = firsts[j].charAt(0);
				variable.addFirst(first);
			}
		}
	}

	private void parseFollow(String part) {
		String[] VariablesToFollow = part.split(";");
		for(int i=0;i<VariablesToFollow.length;i++)
		{
			String[] variableToFollow = VariablesToFollow[i].split("/");
			Character variable_name = variableToFollow[0].charAt(0);
			Variable variable = getVariableByName(variable_name);
			String follows = variableToFollow[1];
			for(int j=0; j< follows.length();j++)
			{
				Character follow = follows.charAt(j);
				variable.addFollow(follow);
			}
		}
	}

	private void createParsingTable()
	{
		this.parsingTable = new ParsingTable(this, this.variables, this.terminals);
	}

	public Variable getVariableByName(Character character)
	{
		for(int i=0;i<variables.size();i++)
		{
			if(variables.get(i).getName().equals(character))
			{
				return variables.get(i);
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
				Variable variable = getVariableByName(pdaPeek);
				ArrayList<String> rules = variable.getRules();
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
		Variable variable = getVariableByName(pdaPeek);
		String rule = parsingTable.getRule(variable, inputPeek);
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
