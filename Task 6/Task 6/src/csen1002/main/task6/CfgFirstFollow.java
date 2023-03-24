package csen1002.main.task6;

import java.util.ArrayList;
import java.util.List;

/**
 * Write your info here
 * 
 * @name Jane Smith
 * @id 46-0234
 * @labNumber 07
 */

public class CfgFirstFollow {

	List<Variable> variables;
	List<Character> terminals;

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgFirstFollow(String cfg) {
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
	 * Calculates the First Set of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String first() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Calculates the Follow Set of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		// TODO Auto-generated method stub
		return null;
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

}
