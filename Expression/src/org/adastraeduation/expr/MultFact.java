package org.adastraeduation.expr;

public class MultFact extends OpFact {
	final private int precedence = 2;
	final private boolean isUnary = false;
	
	public Expr make(Expr a) {
		return null;
	}
	
	public Expr make(Expr a, Expr b) {
		return new Mult(a,b);
	}
	
	public int getPrec() {
		return 2;
	}
	public boolean isUnary() {
		return false;
	}
}
