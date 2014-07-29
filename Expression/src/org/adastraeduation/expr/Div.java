package org.adastraeduation.expr;
/**
 * Binary operator division
 * @author yijinkang
 *
 */
public class Div extends Expr {
	private Expr a, b;
	
	public Div(Expr a, Expr b) throws DivByZero {
		if (b.eval() == 0) {
			throw new DivByZero();
		}
		this.a = a;
		this.b = b;
	}
	
	public double eval() {
		return a.eval() / b.eval();
	}
	
	public void infix(StringBuilder sb) {
		sb.append("(");
		a.infix(sb);
		sb.append(") / (");
		b.infix(sb);
		sb.append(")");
	}
}

class DivByZero extends Exception {
	
}