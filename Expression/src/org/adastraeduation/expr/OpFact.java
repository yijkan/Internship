package org.adastraeduation.expr;

/**
 * Base class for objects that will make the corresponding operator Expr
 * @author yijinkang
 *
 */
public abstract class OpFact {
	public abstract Expr make(Expr a) throws NegRoot; // Unary operators?
	public abstract Expr make(Expr a, Expr b) throws DivByZero;
	public abstract int getPrec();
	public abstract boolean isUnary();
}
