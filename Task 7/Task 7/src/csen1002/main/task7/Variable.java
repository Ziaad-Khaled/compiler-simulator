package csen1002.main.task7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Variable {

    private CfgLl1Parser cfgLl1Parser;
    private Character name;
    private ArrayList<String> rules;
    private List<Character> first;
    private List<Character> follow;

    public Variable(CfgLl1Parser cfgLl1Parser, Character name)
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
