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
		if (a instanceof Const) {
			a.infix(sb);
		} else {
			sb.append("(");
			a.infix(sb);
			sb.append(")");
		}
		sb.append(" / ");
		if (b instanceof Const) {
			b.infix(sb);
		} else {
			sb.append("(");
			b.infix(sb);
			sb.append(")");
		}
	}
	
	public void RPN(StringBuilder sb) {
		a.RPN(sb);
		b.RPN(sb);
		sb.append("/ ");
	}
	
	public void LaTeX(StringBuilder sb) {
		sb.append("\\frac{");
		a.LaTeX(sb);
		sb.append("}{");
		b.LaTeX(sb);
		sb.append("}");
	}
	
	public boolean contains(String var) {
		return a.contains(var) || b.contains(var);
	}

	public Expr diff(String var) throws DivByZero, NegRoot {
		return new Div(new Sub(new Mult(b, a.diff(var)), new Mult(a, b.diff(var))), new Mult(b,b));
	}
}

class DivByZero extends Exception {
	
}