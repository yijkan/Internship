package org.adastraeduation.expr;

public class MultFact extends OpFact {	
	public MultFact(Expression e) {
		super(e);
	}
	
	public Expr make(Expression e) {
		return new Mult(e.operands.pop(), e.operands.pop());
	}
	
	public int getPrec() {
		return 2;
	}
}
