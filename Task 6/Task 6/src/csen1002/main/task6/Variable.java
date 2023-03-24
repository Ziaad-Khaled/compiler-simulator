package csen1002.main.task6;

import java.util.ArrayList;

public class Variable {

    CfgFirstFollow cfgFirstFollow;
    Character name;
    private ArrayList<String> rules;

    public Variable(CfgFirstFollow cfgFirstFollow, Character name)
    {
        this.cfgFirstFollow = cfgFirstFollow;
        this.name = name;
        this.rules = new ArrayList<>();
    }

    public void addRule(String rule)
    {
        rules.add(rule);
    }

    public Character getName() {
        return name;
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
