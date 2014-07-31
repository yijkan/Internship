package org.adastraeduation.expr;

public class SecFact extends OpFact {

	public SecFact(Expression e) {
		super(e);
	}

	public Expr make(Expression e) throws NegRoot, DivByZero {
		return new Sec(e.operands.pop());
	}

	public int getPrec() {
		return 4;
	}
	
}
