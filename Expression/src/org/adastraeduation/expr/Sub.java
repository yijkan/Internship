package org.adastraeduation.expr;

/**
 * Binary operator subtraction
 * @author yijinkang
 *
 */

public class Sub extends Expr {
	private Expr a, b;
	/**
	 * b will be subtracted from a
	 */
	
	public Sub(Expr a, Expr b) {
		this.a = a;
		this.b = b;
	}
	
	public double eval() {
		return a.eval() - b.eval();
	}
	
	public void infix(StringBuilder sb) {
		sb.append("(");
		a.infix(sb);
		sb.append(") - (");
		b.infix(sb);
		sb.append(")");
	}
}
