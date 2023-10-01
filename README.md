# Compiler Simulator

## Project Overview

The Compiler Simulator project consists of several components, each addressing specific tasks related to compiler construction. This README provides an overview of each component and their requirements.

### Component 1: RegExToNfa

#### Description

The RegExToNfa component focuses on implementing Thompson's construction to convert a regular expression into an equivalent NFA (Non-Deterministic Finite Automaton). This process involves transforming a postfix regular expression into an NFA using various rules and operations.

#### Requirements

- The alphabet Σ of the regular expression is a subset of the Latin alphabet, excluding 'e'.
- Regular expressions do not include the empty set ∅.
- The empty string ε is represented by 'e'.
- ◦ is represented by '.', and ∪ by '|'.
- Regular expressions are given in postfix notation.
- States of the resulting NFA are represented as numbers.
- For postfix regular expression R, states introduced by NFA equivalent to a prefix of R are smaller in number than states introduced by NFA equivalent to longer prefixes of R.
- Implement a class constructor RegExToNfa and a method toString.
- RegExToNfa takes one parameter in the form of a string A#R, where A is a string representation of an alphabet Σ, and R is a postfix regular expression over Σ. It constructs the NFA according to Thompson's construction.
- toString returns a string describing the resulting NFA in the format Q#A#T#I#F.

#### Example

For example, invoking toString on a RegExToNfa object representing the regular expression 'a;b#ab|' should return the string:
> 0;1;2;3;4;5#a;b#0,a,1;1,e,5;2,b,3;3,e,5;4,e,0;4,e,2#4#5

**Testing**: There are 10 tests available for this component.
### Component 2: NfaToDfa

#### Description

The NfaToDfa component focuses on constructing a deterministic finite automaton (DFA) equivalent to a given non-deterministic finite automaton (NFA). This process involves transforming an NFA into a DFA using standard algorithms.

#### Requirements

- The alphabet Σ is a subset of the Latin alphabet, excluding 'e'.
- The letter 'e' represents ε.
- The set of NFA states Q is of the form {0, ..., n}, for some n ∈ N.
- Implement a class constructor NfaToDfa and a method toString.
- NfaToDfa takes one parameter in the form of a string description of an NFA and constructs an equivalent DFA.
- toString returns a string representation of the constructed DFA.

#### Example

For example, invoking toString on a NfaToDfa object representing the NFA with the state diagram shown below:
> 0 b 1 a, ε 2 b 3
> 
> a, b a, b

should return the string:
> 0;0/1/2;0/1/2/3;0/2;0/2/3;0/3#a;b#0,a,0;0,b,0/1/2;0/1/2,a,0/2;0/1/2,b,0/1/2/3;0/1/2/3,a,0/2/3;0/1/2/3,b,0/1/2/3;0/2,a,0;0/2,b,0/1/2/3;0/2/3,a,0/3;0/2/3,b,0/1/2/3;0/3,a,0/3;0/3,b,0/1/2/3#0#0/1/2/3;0/2/3;0/3


**Testing**: There are 10 tests available for this component.

### Component 3: FallbackDfa

#### Description

The FallbackDfa component focuses on implementing a fallback deterministic finite automaton with actions (FDFA). This ADT includes states, transitions, and actions for specific states.

#### Requirements

- The set of states Q is of the form {0, ..., n}, for some n ∈ N.
- The alphabet Σ is a subset of the Latin alphabet, excluding 'e'.
- The start state q0 is not in the set of accept states F.
- Implement a class constructor FallbackDfa and a method run.
- FallbackDfa takes one parameter in the form of a string description of an FDFA and constructs an FDFA instance.
- run simulates the operation of the FDFA on a given binary string and returns a semicolon-separated sequence of tokens.

#### Example

For example, running the FDFA on the string 'baababb' should produce the output: `baaba, 2; bb, 1`

**Testing**: There are 10 tests available for this component.

### Component 4: CfgEpsUnitElim

#### Description

The CfgEpsUnitElim component focuses on eliminating epsilon (ε) and unit rules from a given context-free grammar (CFG). This process ensures that the grammar is free from these types of rules.

#### Requirements

- Variables consist of upper-case English letters.
- The start variable is 'S'.
- Terminals consist of lower-case English letters (excluding 'e').
- The letter 'e' represents ε.
- Implement a class constructor CfgEpsUnitElim and three methods: toString, eliminateEpsilonRules, and eliminateUnitRules.
- CfgEpsUnitElim takes one parameter, a string description of a CFG, and constructs a CFG instance.
- toString returns a string representation of the CFG after epsilon and unit rule elimination.
- eliminateEpsilonRules eliminates epsilon rules from the CFG.
- eliminateUnitRules eliminates unit rules from the CFG.

#### Example

For example, invoking toString on a CfgEpsUnitElim object representing the CFG with the rules:

> S → a A b | x B
> A → B c | C | c | d
> B → C A C A | ε
> C → A | b | ε

should return the string:
> S;A;B;C#a;b;c;d;x#S/aAb, xB; A/Bc, C, c, d; B/CACA, e; C/A, b, e

**Testing**: There are 10 tests available for this component.

### Component 5: CfgLeftRecElim

#### Description

The CfgLeftRecElim component focuses on eliminating left recursion from a given context-free grammar (CFG). This process ensures that the grammar is free from immediate left recursion.

#### Requirements

- Variables consist of upper-case English letters.
- The start variable is 'S'.
- Terminals consist of lower-case English letters (excluding 'e').
- The letter 'e' represents ε.
- The CFG has no cycles and no ε-rules.
- Implement a class constructor CfgLeftRecElim and a method eliminateLeftRecursion.
- CfgLeftRecElim takes one parameter, a string description of a CFG, and constructs a CFG instance.
- eliminateLeftRecursion eliminates left recursion from the CFG.

#### Example

For example, invoking eliminateLeftRecursion on a CfgLeftRecElim object representing the CFG with the rules:

> S → Sa | Aa | b | c
> A → Ab | c

should modify the CFG to remove left recursion:

> S → AbS' | cS' | b | c
> S' → aS' | ε
> A → bS' | cS' | b | c

**Testing**: There are 10 tests available for this component.

### Component 6: CfgFirstFollow

#### Description

The CfgFirstFollow component deals with computing the First and Follow functions for the variables in a given context-free grammar (CFG). The First function represents the set of terminals that can appear as the first symbol of a string derived from a variable. The Follow function represents the set of terminals that can appear immediately following a variable in any valid derivation.

#### Requirements

- Implement a class constructor CfgFirstFollow.
- CfgFirstFollow takes one parameter, a string description of a CFG, and constructs a CfgFirstFollow instance.
- Implement two methods: `first` and `follow`.
- The `first` method computes and returns the First sets for the variables in the CFG.
- The `follow` method computes and returns the Follow sets for the variables in the CFG.
- The output of each method is a semicolon-separated sequence of items. Each item consists of a variable of the grammar and a string representation of the First or Follow set of that variable. The symbols in these strings should appear in alphabetical order, with '$' (representing ε) always appearing first. The items themselves should appear in the order in which their respective variables appear in the input CFG.

#### Example

For example, given a CFG G1 = ({S, T, L}, {a, b, c, d, i}, R, S), where R is given by the following productions:

> S → S c T | T
> T → a S b | i a L b | ε
> L → S d L | S

The string encoding of this CFG is as follows:
> S;T;L#a;b;c;d;i#S/ScT, T;T/aSb, iaLb, e;L/SdL, S

The result of calling `first` on G1 may have the following form:
> S/acei; T/aei; L/acdei

Similarly, the result of calling `follow` on G1 may be as follows:
> S/$bcd; T/$bcd; L/b

**Testing**: There are 10 tests available for this component.

### Component 7: CfgLl1Parser

#### Objective

Implement an LL(1) parser for a given context-free grammar (CFG) using pushdown automata (PDA) and predictive parsing tables. The parser should:

- Detect errors if the input string doesn't belong to the CFG's language.
- Provide a derivation of the input string from the start symbol if it belongs to the language.

#### Requirements

- CFG with variables (uppercase English letters), terminals (lowercase letters excluding 'e'), and 'S' as the start symbol.
- Implement the class constructor `CfgLl1Parser` and the method `parse`.
- The CFG is described using a string encoding with V, T, R, I, and O.
- The result is a semicolon-separated sequence of items, starting with 'S' and ending with the input string or "ERROR" if it doesn't belong to the language.

#### Example

For input "iiac" and CFG G1, `parse` returns:
> S; iST; iiSTT; iiTT; iiaT; iiacS; iiac


**Testing**: There are 10 tests available for this component.


## Conclusion

This project is a comprehensive exploration of compiler construction, covering essential components and algorithms crucial for language processing tasks. From transforming regular expressions into finite automata, handling context-free grammars, eliminating ambiguities, to building LL(1) parsers, each component plays a vital role in the compiler's journey.

By delving into these core concepts and their practical implementations, you'll gain valuable insights into the world of compiler development. Whether you're a seasoned developer or just starting in the field, this project equips you with the knowledge and tools needed to tackle complex language processing challenges.

Feel free to explore each component, run the provided tests, and expand your understanding of compiler construction. Your journey into the world of compilers begins here.



