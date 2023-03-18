package csen1002.main.task4;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Ziad Khaled
 * @id 46-20280
 * @labNumber 23
 */

public class CfgEpsUnitElim {

	List<Variable> variables;
	List<Character> terminals;

	/**
	 * Constructs a Context Free Grammar
	 *
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgEpsUnitElim(String cfg) {
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
	 * Eliminates Epsilon Rules from the grammar
	 */
	public void eliminateEpsilonRules() {
		Boolean changed = true;
		while(changed)
			changed = eliminateEpsilonRulesHelper();
	}

	private Boolean eliminateEpsilonRulesHelper() {
		boolean changed = false;
		for(int i= variables.size() - 1; i>= 0; i--)
		{
			Variable variable = variables.get(i);
			if(variable.hasEpsilon())
			{
				removeEpsilon(variable);
				changed = true;
			}

		}
		return changed;
	}

	private void removeEpsilon(Variable variable) {
		variable.removeEpsilon();
		Character variableName = variable.getName();
		for(int i=0; i<variables.size();i++)
		{
			Variable targetVariable = variables.get(i);
			targetVariable.substituteEpsilon(variableName);
		}
	}

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public void eliminateUnitRules() {
		// TODO Auto-generated method stub
		for(int i= variables.size() - 1; i>= 0; i--)
		{
			Variable variable = variables.get(i);
			variable.eliminateUnitRules();
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

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		return variablesToString() + "#" + terminalsToString() + "#" + rulesToString();
	}

	private String variablesToString() {
		String output = "";
		for(int i=0;i<variables.size();i++)
		{
			output+=variables.get(i).getName()+ ";";
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

class Variable {
	private CfgEpsUnitElim cfgEpsUnitElim;
	private Character name;
	private HashSet<String> rules;
	private boolean hasEpsilon;
	private boolean hadEpsilon;
	private HashSet<Character> eliminatedUnitRules;
	public Variable(CfgEpsUnitElim cfgEpsUnitElim, Character name)
	{
		this.cfgEpsUnitElim = cfgEpsUnitElim;
		this.name = name;
		this.rules = new HashSet<>();
		hasEpsilon = false;
		hadEpsilon = false;
		eliminatedUnitRules = new HashSet<>();
	}

	public void addRule(String rule)
	{
		rules.add(rule);
		if(rule.charAt(0) == 'e')
			addEpsilon();
	}

	public void substituteEpsilon(Character variable)
	{
		String[] rules_arr = new String[rules.size()];
		rules.toArray(rules_arr);

		for (String element : rules_arr) {
			substituteEpsilonHelper(element, variable);
		}
	}

	private void substituteEpsilonHelper(String str, Character variable)
	{
		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i) == variable)
			{
				String newString = str.substring(0, i) + str.substring(i + 1);
				if(!newString.equals(""))
				{
					rules.add(newString);
					substituteEpsilonHelper(newString, variable);
				}
				else
				{
					addEpsilon();
				}
			}
		}
	}

	public void eliminateUnitRules()
	{
		boolean changed = true;
		while (changed)
		{
			changed = eliminateUnitRulesHelper();
		}
	}

	private boolean eliminateUnitRulesHelper()
	{
		boolean changed = false;
		String[] rules_arr = new String[rules.size()];
		rules.toArray(rules_arr);

		for (String variableName : rules_arr) {
			if(isUnit(variableName))
			{
				changed = true;
				eliminateOneUnitRule(variableName);
			}
		}

		return changed;
	}

	private void eliminateOneUnitRule(String variableName)
	{
		rules.remove(variableName);
		eliminatedUnitRules.add(variableName.charAt(0));
		Variable variable = cfgEpsUnitElim.getVariableByName(variableName.charAt(0));
		HashSet<String> newRules = variable.getRules();
		rules.addAll(newRules);
		checkIfContainsElimintatedUnitRule();
	}

	private void checkIfContainsElimintatedUnitRule() {
		for(Character unitRule: eliminatedUnitRules)
		{
			if(rules.contains(unitRule.toString()))
			{
				rules.remove(unitRule.toString());
			}
		}
	}

	public void removeEpsilon()
	{
		rules.remove("e");
		hasEpsilon = false;
	}

	private void addEpsilon() {
		if(!hadEpsilon)
		{
			hasEpsilon = true;
			hadEpsilon = true;
			rules.add("e");
		}
	}

	private boolean isUnit(String str)
	{
		return str.matches("^[A-Z]$");
	}

	public Character getName() {
		return name;
	}

	public HashSet<String> getRules() {
		return rules;
	}

	public boolean hasEpsilon() {
		return hasEpsilon;
	}

	@Override
	public String toString() {
		String output = name + "/";
		List<String> rules_arr = new ArrayList<>(rules);
		Collections.sort(rules_arr);
		for(int i=0;i<rules_arr.size();i++)
		{
			output+= rules_arr.get(i);
			output += ",";
		}

		if(output.charAt(output.length()-1) == '/')
			output += "/";

		return  output.substring(0, output.length()-1);
	}
}
