package org.adastraeduation.expr;
/**
 * Base class for all nodes in the expression tree
 * There are Binary Operators, Unary Operators
 * Constants, and Variables
 * 
 * @author dkruger
 *
 */
public abstract class Expr {
	public abstract double eval();
	// public abstract void simplify();
}
