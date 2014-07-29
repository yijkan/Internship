package org.adastraeduation.expr;

public class SubFact extends OpFact {
	public SubFact(Expression e) {
		super(e);
	}
	
	public Expr make(Expression e) {
		/* Switch the two because the one on top of the stack is subtracted
		 * from the the one below it
		 */
		Expr temp = e.operands.pop();
		return new Sub(e.operands.pop(), temp);
	}
	
	public int getPrec() {
		return 1;
	}
}
