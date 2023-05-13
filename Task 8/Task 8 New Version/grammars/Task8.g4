/**
 * Write your info here
 *
 * @name Ziad Khaled
 * @id 46-20280
 * @labNumber 23
 */

grammar Task8;

/* Write all the necessary lexer rules and fragment lexer rules here */
IF options { caseInsensitive=true; } : [i][f];
ELSE options { caseInsensitive=true; }: [e][l][s][e];
COMP: '>' | '<' | '>=' | '<=' | '==' | '!=';
ID: [a-zA-Z_][a-zA-Z0-9_]*;
NUM: [0-9]+('.'[0-9]+)?(('E'|'e')('+'|'-')?[0-9]+)?;
LIT: '"' (~('\\'|'"')|'\\'.)* '"';
LP: '(';
RP: ')';
WS: [ \t\r\n]+ -> skip;

/* This rule is to check your grammar using "ANTLR Preview" */
test: /* (Rule1 | Rule2 | ... | RuleN)+ */ EOF; //Replace the non-fragment lexer rules here

// Write all the necessary lexer rules and fragment lexer rules here