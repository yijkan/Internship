package org.adastraeduation.expr;

public class TanFact extends OpFact {
	public TanFact(Expression e) {
		super(e);
	}
	
	public Expr make(Expression e) {
		return new Tan(e.operands.pop());
	}
	
	public int getPrec() {
		return 4;
	}
}
