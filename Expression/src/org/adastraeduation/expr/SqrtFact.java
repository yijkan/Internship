package org.adastraeduation.expr;

public class SqrtFact extends OpFact {
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
