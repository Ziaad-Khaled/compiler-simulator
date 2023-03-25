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

class Variable {
	private CfgLeftRecElim cfgLeftRecElim;
	private Character name;
	private boolean name_bar;
	private ArrayList<String> rules;
	private ArrayList<String> alphas;
	private ArrayList<String> betas;
	public Variable(CfgLeftRecElim cfgLeftRecElim, Character name)
	{
		this.cfgLeftRecElim = cfgLeftRecElim;
		this.name = name;
		name_bar = false;
		this.rules = new ArrayList<>();
		this.alphas = new ArrayList<>();
		this.betas = new ArrayList<>();

	}

	public void addRule(String rule)
	{
		rules.add(rule);
	}

	public void eliminateLeftRecursion(ArrayList<Character> eliminatedProductionVariables) {
		eliminateProductionRules(eliminatedProductionVariables);
		assignAlphasAndBetas();
		if(!alphas.isEmpty())
		{
			createNewRules();
			createNewVariable();
		}
	}

	public void eliminateProductionRules(ArrayList<Character> productionVariables)
	{
		List<String> rules_arr = new ArrayList<>(rules);
		for(Character productionVariable : productionVariables)
		{
			for (String element : rules_arr) {
				if(productionVariable == element.charAt(0))
				{
					eliminateProductionRule(element);
				}
			}
			rules_arr = new ArrayList<>(rules);
		}
	}
	private void eliminateProductionRule(String element) {
		int index = rules.indexOf(element);
		rules.remove(element);
		Variable productionVariable = cfgLeftRecElim.getVariableByName(element.charAt(0));
		ArrayList<String> productionVariableRules = productionVariable.getRules();
		String restOfTheString = element.substring(1);
		for (String rule : productionVariableRules) {
			String newRule = rule + restOfTheString;
			rules.add(index, newRule);
			index++;
		}
	}

	public void assignAlphasAndBetas()
	{
		for (String element : rules) {
			if(element.charAt(0) == name)
			{
				String alpha = element.substring(1);
				alphas.add(alpha);
			}
			else
			{
				betas.add(element);
			}
		}
	}

	private void createNewRules() {
		rules.clear();
		for (String beta : betas) {
			String newRule = beta;
			newRule += name;
			newRule += "'";
			rules.add(newRule);
		}
	}

	private void createNewVariable() {
		Variable newVariable = new Variable(this.cfgLeftRecElim, this.name);
		newVariable.setName_bar(true);
		for (String alpha : alphas) {
			String newRule = alpha;
			newRule += name;
			newRule += "'";
			newVariable.addRule(newRule);
		}
		newVariable.addRule("e");
		this.cfgLeftRecElim.addVaribale(newVariable);
	}


	public Character getName() {
		return name;
	}

	public ArrayList<String> getRules() {
		return rules;
	}

	public boolean isName_bar() {
		return name_bar;
	}

	public void setName_bar(boolean name_bar) {
		this.name_bar = name_bar;
	}

	@Override
	public String toString() {
		String output = name + "";
		if(name_bar)
			output += "'";
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
