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
	public void infix(StringBuilder sb) { sb.append(val); }
	public void RPN(StringBuilder sb) { sb.append(val + " "); }
	public void LaTeX(StringBuilder sb) { sb.append(val); }
	public boolean contains(String var) { return false; }
	public Expr diff(String var) { return new Const (0); }
	// public Expr integ(String var) { val * var }
}
