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
		if (a instanceof Const) {
			a.infix(sb);
		} else {
			sb.append("(");
			a.infix(sb);
			sb.append(")");
		}
		sb.append(" - ");
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
		sb.append("- ");
	}
	
	public void LaTeX(StringBuilder sb) {
		if (a instanceof Const) {
			a.infix(sb);
		} else {
			sb.append("(");
			a.infix(sb);
			sb.append(")");
		}
		sb.append(" - ");
		if (b instanceof Const) {
			b.infix(sb);
		} else {
			sb.append("(");
			b.infix(sb);
			sb.append(")");
		}
	}
	
	public boolean contains(String var) {
		return a.contains(var) || b.contains(var);
	}

	public Expr diff(String var) throws DivByZero, NegRoot {
		return new Sub(a.diff(var), b.diff(var));
	}
}
