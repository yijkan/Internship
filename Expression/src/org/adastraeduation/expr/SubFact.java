package org.adastraeduation.expr;

public class SubFact extends OpFact {
	public Expr make(Expr a) {
		return null;
	}
	
	public Expr make(Expr a, Expr b) {
		return new Sub(a,b);
	}
	
	
	public int getPrec() {
		return 1;
	}
	public boolean isUnary() {
		return false;
	}
}
