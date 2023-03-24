package csen1002.main.task5;

import java.util.ArrayList;
import java.util.List;

/**
 * Write your info here
 * 
 * @name Ziad Khaled Naif
 * @id 46-20280
 * @labNumber 23
 */

public class CfgLeftRecElim {

	List<Variable> variables;
	List<Character> terminals;

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgLeftRecElim(String cfg) {
		String[] parts = cfg.split("#");
		parseVariables(parts[0]);
		parseTerminals(parts[1]);
		parseRules(parts[2]);
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

	/**
	 * Eliminates Left Recursion from the grammar
	 */
	public void eliminateLeftRecursion() {
		ArrayList<Character> eliminatedProductionVariables = new ArrayList<>();
		int variablesSize = variables.size();
		for(int i=0;i<variablesSize;i++)
		{
			Variable variable = variables.get(i);
			variable.eliminateLeftRecursion(eliminatedProductionVariables);
			eliminatedProductionVariables.add(variable.getName());
		}
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

	public void addVaribale(Variable variable)
	{
		variables.add(variable);
	}

	@Override
	public String toString() {
		return variablesToString() + "#" + terminalsToString() + "#" + rulesToString();
	}

	private String variablesToString() {
		String output = "";
		for(int i=0;i<variables.size();i++)
		{
			Variable variable = variables.get(i);
			output+=variable.getName();
			if(variable.isName_bar())
				output+= "'";
			output += ";";
		}
		return output.substring(0, output.length() - 1);
	}

	private String terminalsToString() {
		String output = "";
		for(int i=0;i<terminals.size();i++)
		{
			output+=terminals.get(i)+ ";";
		}
		return output.substring(0, output.length() - 1);
	}

	private String rulesToString()
	{
		String output = "";
		for(int i=0;i< variables.size();i++)
		{
			Variable value = variables.get(i);
			output+= value.toString();
			output += ";";
		}
		return output.substring(0, output.length() - 1);
	}





}
