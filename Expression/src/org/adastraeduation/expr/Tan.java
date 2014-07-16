package org.adastraeduation.expr;
/**
 * Unary operator tangent
 * @author yijinkang
 *
 */
public class Tan extends Expr {
	private Expr angle;
	
	public Tan(Expr a) {
		angle = a;
	}
	
	public double eval() {
		return Math.tan(angle.eval());
	}


}
