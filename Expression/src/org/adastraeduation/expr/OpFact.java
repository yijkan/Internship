package org.adastraeduation.expr;

/**
 * Base class for objects that will make the corresponding operator Expr
 * @author yijinkang
 *
 */
public abstract class OpFact {
	private Expression e;
	
	public OpFact(Expression e) {
		this.e = e;
	}

	public abstract Expr make(Expression e) throws NegRoot, DivByZero;
	public abstract int getPrec();
}
