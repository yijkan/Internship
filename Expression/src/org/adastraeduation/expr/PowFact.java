package org.adastraeduation.expr;

public class PowFact extends OpFact{
	public PowFact(Expression e) {
		super(e);
	}
	
	public Expr make(Expression e) {
		/* Switch the two because the one on top of the stack is the exponent;
		 * the one below it is the base
		 */
		Expr temp = e.operands.pop();
		return new Power(e.operands.pop(),temp);
	}
	
	
	public int getPrec() {
		return 3;
	}
}
