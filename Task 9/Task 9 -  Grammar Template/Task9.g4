/**
 * Write your info here
 *
 * @name Jane Smith
 * @id 46-0234
 * @labNumber 07
 */

grammar Task9;

@members {
	/**
	 * Compares two integer numbers
	 *
	 * @param x the first number to compare
	 * @param y the second number to compare
	 * @return 1 if x is equal to y, and 0 otherwise
	 */
	public static int equals(int x, int y) {
	    return x == y ? 1 : 0;
	}
}

s returns [int check]:
 // Write the definition of parser rule "s" here
 ;

// Write additional lexer and parser rules here