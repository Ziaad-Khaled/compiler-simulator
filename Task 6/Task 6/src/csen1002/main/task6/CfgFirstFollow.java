package csen1002.main.task6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
		boolean change = true;
		while(change)
		{
			change = false;
			for(int i=0;i< variables.size();i++)
			{
				Variable variable = variables.get(i);
				boolean ruleAdded = variable.assignFirst();
				if(ruleAdded)
					change = true;
			}
		}

		return firstToString();
	}


	/**
	 * Calculates the Follow Set of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		first();
		boolean change = true;
		while(change)
		{
			change = false;
			for(int i=0;i< variables.size();i++)
			{
				Variable variable = variables.get(i);
				boolean ruleAdded = variable.assignFollow();
				if(ruleAdded)
					change = true;
			}
		}

		return followToString();
	}


	private String firstToString() {
		String output = "";
		for(int i=0;i<variables.size();i++)
		{
			Variable variable = variables.get(i);
			output += variable.getName();
			output += "/";
			output += variable.firstToString();
			output += ";";
		}

		if(output.charAt(output.length()-1) == ';')
			output = output.substring(0, output.length() - 1);

		return output;
	}

	private String followToString() {
		String output = "";
		for(int i=0;i<variables.size();i++)
		{
			Variable variable = variables.get(i);
			output += variable.getName();
			output += "/";
			output += variable.followToString();
			output += ";";
		}

		if(output.charAt(output.length()-1) == ';')
			output = output.substring(0, output.length() - 1);

		return output;
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

class Variable {

	private CfgFirstFollow cfgFirstFollow;
	private Character name;
	private ArrayList<String> rules;
	private HashSet<Character> first;
	private HashSet<Character> follow;

	public Variable(CfgFirstFollow cfgFirstFollow, Character name)
	{
		this.cfgFirstFollow = cfgFirstFollow;
		this.name = name;
		this.rules = new ArrayList<>();
		this.first = new HashSet<>();
		this.follow = new HashSet<>();

		if(name == 'S')
		{
			follow.add('$');
		}

	}

	public void addRule(String rule)
	{
		rules.add(rule);
	}

	public boolean assignFirst()
	{
		int oldSize = first.size();
		for(int i=0;i<rules.size();i++)
		{
			String rule = rules.get(i);
			boolean allEpsilon = true;
			for(int j=0; j< rule.length();j++)
			{
				Character character = rule.charAt(j);
				ArrayList<Character> targetFirst = firstHelper(character);
				if(!targetFirst.contains('e'))
				{
					allEpsilon = false;
					break;
				}
			}
			if(allEpsilon)
				first.add('e');
		}

		for(int i=0;i<rules.size();i++)
		{
			String rule = rules.get(i);
			ArrayList<Character> targetFirst = new ArrayList<>();
			for(int j=0; j< rule.length();j++)
			{
				Character character = rule.charAt(j);
				targetFirst = firstHelper(character);
				boolean hadEpsilon = targetFirst.contains(Character.valueOf('e'));
				targetFirst.remove(Character.valueOf('e'));
				first.addAll(targetFirst);
				if(!hadEpsilon)
					break;
			}
		}

		return oldSize != first.size();
	}


	public boolean assignFollow()
	{
		boolean change = false;
		for(int i=0;i<rules.size();i++) {
			String rule = rules.get(i);
			for(int j=0; j < rule.length() - 1;j++)
			{
				Character character = rule.charAt(j);
				String followingCharacters = rule.substring(j+1);
				if(Character.isUpperCase(character))
				{
					boolean changed = assignFollowHelper(character, followingCharacters);
					if(changed)
						change = true;
				}
			}
		}

		for(int i=0;i<rules.size();i++) {
			String rule = rules.get(i);
			boolean changed = assignFollowHelperSecondCase(rule);
			if(changed)
				change = true;
		}
		return change;
	}



	private boolean assignFollowHelper(Character character, String followingCharacters) {
		boolean change = false;
		Variable variable = cfgFirstFollow.getVariableByName(character);
		Character followingCharacter = followingCharacters.charAt(0);
		boolean changed = false;
		if(Character.isLowerCase(followingCharacter))
		{
			change = variable.addFollowCharacter(followingCharacter);
		}
		else
		{
			Variable followingVariable = cfgFirstFollow.getVariableByName(followingCharacter);
			HashSet<Character> followingVariableFirst = followingVariable.getFirst();
			HashSet<Character> followingVariableFirst_copy = new HashSet<>(followingVariableFirst);

			if(followingVariableFirst.contains('e') && followingCharacters.length() > 1)
			{
				String subString = followingCharacters.substring(1);
				changed = assignFollowHelper(character, subString);
			}

			if(changed)
				change = true;
			followingVariableFirst_copy.remove(Character.valueOf('e'));
			changed = variable.addFollowCharacter(followingVariableFirst_copy);
			if(changed)
				change = true;
		}

		return change;
	}

	private boolean assignFollowHelperSecondCase(String rule) {
		boolean change = true;
		Character lastCharacter = rule.charAt(rule.length()-1);
		if(Character.isLowerCase(lastCharacter))
			return false;
		Variable variable = cfgFirstFollow.getVariableByName(lastCharacter);
		return variable.addFollowCharacter(this.follow);
	}

	public ArrayList<Character> firstHelper(Character character)
	{
		ArrayList<Character> first = new ArrayList<>();
		if(Character.isLowerCase(character))
			first.add(character);
		else
		{
			Variable variable = cfgFirstFollow.getVariableByName(character);
			first.addAll(variable.getFirst());
		}
		return first;
	}

	public boolean addFollowCharacter(Character character)
	{
		int oldSize = follow.size();
		follow.add(character);
		return oldSize != follow.size();
	}

	public boolean addFollowCharacter(HashSet<Character> characters)
	{
		int oldSize = follow.size();
		follow.addAll(characters);
		return oldSize != follow.size();
	}



	public HashSet<Character> getFirst()
	{
		return this.first;
	}


	public Character getName() {
		return name;
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
