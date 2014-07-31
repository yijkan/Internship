package org.adastraeduation.expr;

public class LogFact extends OpFact {
	
	public LogFact(Expression e) {
		super(e);
	}

	public Expr make(Expression e) throws NegRoot, DivByZero {
		return new Log(e.operands.pop());
	}

	public int getPrec() {
		return 4;
	}
	
}
