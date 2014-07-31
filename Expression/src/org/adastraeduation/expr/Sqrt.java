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
	
	public void RPN(StringBuilder sb) {
		a.RPN(sb);
		sb.append("sqrt ");
	}
	
	public void LaTeX(StringBuilder sb) {
		sb.append("\\sqrt{");
		a.LaTeX(sb);
		sb.append("}");
	}

	public boolean contains(String var) {
		return a.contains(var);
	}
	
	public Expr diff(String var) throws DivByZero, NegRoot {
		return new Mult(new Mult(new Const(0.5), new Div(new Const(1), new Sqrt(a))), a.diff(var));
	}
}

class NegRoot extends Exception {
	
}