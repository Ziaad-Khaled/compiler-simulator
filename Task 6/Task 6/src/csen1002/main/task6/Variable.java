package csen1002.main.task6;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Variable {

    private CfgFirstFollow cfgFirstFollow;
    private Character name;
    private ArrayList<String> rules;
    private HashSet<Character> first;

    public Variable(CfgFirstFollow cfgFirstFollow, Character name)
    {
        this.cfgFirstFollow = cfgFirstFollow;
        this.name = name;
        this.rules = new ArrayList<>();
        this.first = new HashSet<>();
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
                    allEpsilon = false;
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
                first.addAll(targetFirst);
                if(!targetFirst.contains('e'))
                    break;
            }
        }

        if(oldSize != first.size())
            return true;
        return false;
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



    public HashSet<Character> getFirst()
    {
        return this.first;
    }

    public boolean doesFirstHaveEpsilon() {
        return first.contains('e');
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
