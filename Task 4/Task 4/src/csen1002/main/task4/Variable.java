package csen1002.main.task4;

import java.util.*;

public class Variable {
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
        return  output.substring(0, output.length()-1);
    }
}
