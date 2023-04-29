package csen1002.main.task7;

import java.util.ArrayList;
import java.util.List;

public class ParsingTable {
    CfgLl1Parser cfgLl1Parser;
    List<Variable> variables;
    List<Character> terminals;
    int[][] parsingTable;

    public ParsingTable(CfgLl1Parser cfgLl1Parser, List<Variable> variables, List<Character> terminals) {
        this.cfgLl1Parser = cfgLl1Parser;
        this.variables = variables;
        this.terminals = terminals;
        this.terminals.add('$');
        int numberOfVariables = this.variables.size();
        int numberOfTerminals = this.terminals.size();
        this.parsingTable = new int[numberOfVariables][numberOfTerminals];
        initializeParsingTableValues();
        assignValuesToParsingTable();
    }

    private void initializeParsingTableValues() {
        for(int i=0;i<parsingTable.length;i++)
        {
            for(int j=0;j<parsingTable[0].length;j++)
            {
                parsingTable[i][j] = -1;
            }
        }
    }


    private void assignValuesToParsingTable() {
        for(int i=0;i<parsingTable.length;i++)
        {
            Variable variable = this.variables.get(i);
            ArrayList<String> rules = variable.getRules();
            if(rules.contains("e"))
                assignValuesToParsingTableUsingFollow(variable, rules.indexOf("e"));
            List<Character> first = variable.getFirst();
            assignRulesForFirst(variable, first);
        }
    }

    private void assignRulesForFirst(Variable variable, List<Character> first) {
        int variableIndex = variables.indexOf(variable);

        for(int i=0;i<first.size();i++)
        {
            Character terminal = first.get(i);
            int terminalIndex = terminals.indexOf(terminal);
            int rule = getTheRuleForTheTerminal(variable, terminal);
            if(terminal != 'e')
                parsingTable[variableIndex][terminalIndex] = rule;
        }
    }


    private int getTheRuleForTheTerminal(Variable variable, Character terminal) {
        ArrayList<String> rules = variable.getRules();
        for (int i=0;i<rules.size();i++)
        {
            String rule = rules.get(i);
            List<Character> firstOfTheRule = computerFirstOfTheRule(rule);
            if(firstOfTheRule.contains(terminal))
                return i;
        }
        return -1;
    }

    private List<Character> computerFirstOfTheRule(String rule) {
        List<Character> first = new ArrayList<>();
        for(int i=0;i<rule.length();i++)
        {
            Character character = rule.charAt(i);
            if(Character.isLowerCase(character))
            {
                first.add(character);
                break;
            }
            else
            {
                Variable variable = cfgLl1Parser.getVariableByName(character);
                List<Character> variableFirst = variable.getFirst();
                first.addAll(variableFirst);
                List<String> variableRules = variable.getRules();
                if(!variableRules.contains("e"))
                    break;
            }
        }
        return first;
    }

    private void assignValuesToParsingTableUsingFollow(Variable variable, int epsilonIndex) {
        int variableIndex = variables.indexOf(variable);
        List<Character> follow = variable.getFollow();
        for(int j=0;j<this.terminals.size();j++)
        {
            Character terminal = this.terminals.get(j);

            if(follow.contains(terminal))
            {
                this.parsingTable[variableIndex][j] = epsilonIndex;
            }
        }
    }


    public void printParsingTable()
    {
        System.out.print(" ");
        for (int i=0;i<terminals.size();i++)
        {
            System.out.print(" | ");
            System.out.print(terminals.get(i));
        }
        System.out.println();
        for(int i=0;i<parsingTable.length;i++)
        {
            Character variableName = this.variables.get(i).getName();
            System.out.print(variableName);
            for (int j=0;j<parsingTable[i].length;j++)
            {
                if(parsingTable[i][j] < 0)
                    System.out.print(" |");
                else
                    System.out.print(" | ");
                System.out.print(parsingTable[i][j]);
            }
            System.out.println();
        }
    }

    public String getRule(Variable variable, Character terminal) {
        int variableIndex = this.variables.indexOf(variable);
        int terminalIndex = this.terminals.indexOf(terminal);
        int ruleIndex = this.parsingTable[variableIndex][terminalIndex];
        ArrayList<String> rules = variable.getRules();
        if(ruleIndex < 0)
            return null;
        String rule = rules.get(ruleIndex);
        return rule;
    }
}
