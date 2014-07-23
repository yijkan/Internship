package org.adastraeduation.expr;

public class SinFact extends OpFact {
	public Expr make(Expr a) {
		return new Sin(a);
	}

	public Expr make(Expr a, Expr b) {
		return null;
	}
	
	
	public int getPrec() {
		return 4;
	}
	public boolean isUnary() {
		return true;
	}
	
}
