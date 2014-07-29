package org.adastraeduation.expr;
/**
 * Unary operator square root
 * @author yijinkang
 *
 */
public class Sqrt extends Expr {
	private Expr a;
	
	public Sqrt(Expr a) throws NegRoot {
		if (a.eval() < 0) {
			throw new NegRoot();
		}
		this.a = a;
	}
	
	public double eval() {
		return Math.sqrt(a.eval());
	}
	
	public void infix(StringBuilder sb) {
		sb.append("sqrt(");
		a.infix(sb);
		sb.append(")");
	}
}

class NegRoot extends Exception {
	
}