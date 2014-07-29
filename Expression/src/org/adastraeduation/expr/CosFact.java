package org.adastraeduation.expr;

public class CosFact extends OpFact {
	public CosFact(Expression e) {
		super(e);
	}
	
	public Expr make(Expression e) {
		return new Cos(e.operands.pop());
	}
	
	public int getPrec() {
		return 4;
	}
}
