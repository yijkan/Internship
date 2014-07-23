package org.adastraeduation.expr;

public class DivFact extends OpFact {
	public Expr make(Expr a) throws NegRoot {
		return null;
	}
	
	public Expr make(Expr a, Expr b) throws DivByZero {
		/* Switch the two because a will be the one on top of the stack,
		 * meaning it comes up later in the Expression 
		 */
		return new Div(b,a);
	}
	
	public int getPrec() {
		return 2;
	}
	public boolean isUnary() {
		return false;
	}
}
