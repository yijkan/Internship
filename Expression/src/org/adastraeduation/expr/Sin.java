package org.adastraeduation.expr;
/**
 * Unary operator sine
 * @author yijinkang
 *
 */
public class Sin extends Expr {
	private Expr angle;
	
	public Sin(Expr a) {
		angle = a;
	}
	
	public double eval() {
		return Math.sin(angle.eval());
	}
	
	public void infix(StringBuilder sb) {
		sb.append("sin(");
		angle.infix(sb);
		sb.append(")");
	}

}
