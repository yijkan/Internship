package org.adastraeduation.expr;
/**
 * Binary operator power
 * @author yijinkang
 *
 */
public class Power extends Expr {
	private Expr b, e;
	/**
	 * Base b will be raised to the power of exponent e
	 */
	
	public Power(Expr x, Expr y) {
		b = x;
		e = y;
	}
	
	public double eval() {
		return Math.pow(b.eval(), e.eval());
	}
}
