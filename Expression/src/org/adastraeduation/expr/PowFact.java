package org.adastraeduation.expr;

public class PowFact extends OpFact{
	public Expr make(Expr a) {
		return null;
	}
	
	public Expr make(Expr a, Expr b) {
		/* Switch the two because a will be the one on top of the stack,
		 * meaning it comes up later in the Expression 
		 */
		return new Power(b,a);
	}
	
	
	public int getPrec() {
		return 3;
	}
	public boolean isUnary() {
		return false;
	}
}
