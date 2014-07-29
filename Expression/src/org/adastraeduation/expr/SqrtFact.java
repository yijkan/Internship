package org.adastraeduation.expr;

public class SqrtFact extends OpFact {
	public SqrtFact(Expression e) {
		super(e);
	}
	
	public Expr make(Expression e) throws NegRoot {
		return new Sqrt(e.operands.pop());
	}

	public int getPrec() {
		return 4;
	}
}
