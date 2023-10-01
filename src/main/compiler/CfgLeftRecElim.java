package main.compiler;

import java.util.ArrayList;
import java.util.List;


public class CfgLeftRecElim {

	List<LeftRecVariable> leftRecVariables;
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
		this.leftRecVariables = new ArrayList<>();
		String[] variables_arr = part.split(";");
		for(int i=0;i<variables_arr.length;i++)
		{
			Character name = variables_arr[i].charAt(0);
			LeftRecVariable leftRecVariable = new LeftRecVariable(this, name);
			this.leftRecVariables.add(leftRecVariable);
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
			LeftRecVariable leftRecVariable = getVariableByName(variable_name);
			String[] rightHandSide = rulesForAVariable[1].split(",");
			for(int j=0; j< rightHandSide.length;j++)
			{
				String rule = rightHandSide[j];
				leftRecVariable.addRule(rule);
			}
		}
	}

	/**
	 * Eliminates Left Recursion from the grammar
	 */
	public void eliminateLeftRecursion() {
		ArrayList<Character> eliminatedProductionVariables = new ArrayList<>();
		int variablesSize = leftRecVariables.size();
		for(int i=0;i<variablesSize;i++)
		{
			LeftRecVariable leftRecVariable = leftRecVariables.get(i);
			leftRecVariable.eliminateLeftRecursion(eliminatedProductionVariables);
			eliminatedProductionVariables.add(leftRecVariable.getName());
		}
	}

	public LeftRecVariable getVariableByName(Character character)
	{
		for(int i = 0; i< leftRecVariables.size(); i++)
		{
			if(leftRecVariables.get(i).getName().equals(character))
			{
				return leftRecVariables.get(i);
			}
		}

		return null;
	}

	public void addVaribale(LeftRecVariable leftRecVariable)
	{
		leftRecVariables.add(leftRecVariable);
	}

	@Override
	public String toString() {
		return variablesToString() + "#" + terminalsToString() + "#" + rulesToString();
	}

	private String variablesToString() {
		String output = "";
		for(int i = 0; i< leftRecVariables.size(); i++)
		{
			LeftRecVariable leftRecVariable = leftRecVariables.get(i);
			output+= leftRecVariable.getName();
			if(leftRecVariable.isName_bar())
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
		for(int i = 0; i< leftRecVariables.size(); i++)
		{
			LeftRecVariable value = leftRecVariables.get(i);
			output+= value.toString();
			output += ";";
		}
		return output.substring(0, output.length() - 1);
	}

}

class LeftRecVariable {
	private CfgLeftRecElim cfgLeftRecElim;
	private Character name;
	private boolean name_bar;
	private ArrayList<String> rules;
	private ArrayList<String> alphas;
	private ArrayList<String> betas;
	public LeftRecVariable(CfgLeftRecElim cfgLeftRecElim, Character name)
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
		LeftRecVariable productionLeftRecVariable = cfgLeftRecElim.getVariableByName(element.charAt(0));
		ArrayList<String> productionVariableRules = productionLeftRecVariable.getRules();
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
		LeftRecVariable newLeftRecVariable = new LeftRecVariable(this.cfgLeftRecElim, this.name);
		newLeftRecVariable.setName_bar(true);
		for (String alpha : alphas) {
			String newRule = alpha;
			newRule += name;
			newRule += "'";
			newLeftRecVariable.addRule(newRule);
		}
		newLeftRecVariable.addRule("e");
		this.cfgLeftRecElim.addVaribale(newLeftRecVariable);
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

