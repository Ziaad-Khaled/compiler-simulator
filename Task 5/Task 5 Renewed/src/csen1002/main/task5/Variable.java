package csen1002.main.task5;

import java.util.*;

public class Variable {
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
