package org.adastraeduation.expr;

/**
 * Binary Operator multiplication
 * @author yijinkang
 *
 */

public class Mult extends Expr {
	private Expr a, b;
	/**
	 * The two expressions to be multiplied
	 */
	
	public Mult(Expr a, Expr b) { 
		this.a = a;
		this.b = b;
	}
	
	public double eval() {
		return a.eval() * b.eval();
	}
	
	public void infix(StringBuilder sb) {
		sb.append("(");
		a.infix(sb);
		sb.append(") * (");
		b.infix(sb);
		sb.append(")");
	}
}
