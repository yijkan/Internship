package org.adastraeduation.expr;

/**
 * Represent a constant in an Expression
 * Integral types are not supported, just round off
 * @author dkruger
 *
 */
public class Const extends Expr {
	/**
	 *  
	 */
	private double val;
	/**
	 * Load the constant value.
	 *   This object is immutable 
	 */
	Const(double v) { val = v; }
	public double eval() { return val; }
}
