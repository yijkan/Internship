package org.adastraeduation.expr;

public class DivFact extends OpFact {
	public DivFact(Expression e) {
		super(e);
	}
	
	public Expr make(Expression e) throws DivByZero {
		/* Switch the two because the one on top of the stack is the dividend;
		 * the one below it is the divisor
		 */
		Expr temp = e.operands.pop();
		return new Div(e.operands.pop(), temp);
	}
	
	public int getPrec() {
		return 2;
	}
}
