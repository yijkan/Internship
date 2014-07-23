package org.adastraeduation.expr;

public class SqrtFact extends OpFact {
	final private int precedence = 4;
	final private boolean isUnary = true;
	
	public Expr make(Expr a) throws NegRoot {
		return new Sqrt(a);
	}

	public Expr make(Expr a, Expr b){
		return null;
	}
	
	
	public int getPrec() {
		return 4;
	}
	public boolean isUnary() {
		return true;
	}
}
