package org.adastraeduation.expr;

public class TanFact extends OpFact {
	public Expr make(Expr a) {
		return new Tan(a);
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
