package org.adastraeduation.expr;

public class SinFact extends OpFact {
	public SinFact(Expression e) {
		super(e);
	}
	
	public Expr make(Expression e) {
		return new Sin(e.operands.pop());
	}
	
	public int getPrec() {
		return 4;
	}
}
