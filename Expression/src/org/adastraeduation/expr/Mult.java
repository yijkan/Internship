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
		sb.append(")(");
		b.infix(sb);
		sb.append(")");
	}
	
	public void RPN(StringBuilder sb) {
		a.RPN(sb);
		b.RPN(sb);
		sb.append("* ");
	}
	
	public void LaTeX(StringBuilder sb) {
		sb.append("(");
		a.LaTeX(sb);
		sb.append(")(");
		b.LaTeX(sb);
		sb.append(")");
	}
	
	public boolean contains(String var) {
		return a.contains(var) || b.contains(var);
	}

	public Expr diff(String var) throws DivByZero, NegRoot {
		return new Add(new Mult(a, b.diff(var)), new Mult(b, a.diff(var)));
	}
}
