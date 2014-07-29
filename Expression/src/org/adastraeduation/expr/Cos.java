package org.adastraeduation.expr;
/**
 * Unary operator cosine
 * @author yijinkang
 *
 */

public class Cos extends Expr {
	private Expr angle;
	
	public Cos(Expr a) {
		angle = a;
	}
	
	public double eval() {
		return Math.cos(angle.eval());
	}
	
	public void infix(StringBuilder sb) {
		sb.append("cos(");
		angle.infix(sb);
		sb.append(")");
	}

}
