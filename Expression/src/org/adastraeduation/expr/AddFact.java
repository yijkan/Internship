package org.adastraeduation.expr;

public class AddFact extends OpFact {
	public AddFact(Expression e) {
		super(e);
	}
	
	public Expr make(Expression e) {
		return new Add(e.operands.pop(), e.operands.pop());
	}
	
	public int getPrec() {
		return 1;
	}
}
