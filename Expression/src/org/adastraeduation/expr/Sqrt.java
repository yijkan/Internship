package org.adastraeduation.expr;
/**
 * Unary operator square root
 * @author yijinkang
 *
 */
public class Sqrt extends Expr {
	private Expr a;
	
	public Sqrt(Expr a) {
		this.a = a;
	}
	
	public double eval() {
		return Math.sqrt(a.eval());
	}
}
