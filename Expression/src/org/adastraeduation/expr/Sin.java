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
		if (angle instanceof Const) {
			sb.append("sin");
			angle.infix(sb);
		} else {
			sb.append("sin(");
			angle.infix(sb);
			sb.append(")");
		}
	}
	
	public void RPN(StringBuilder sb) {
		angle.RPN(sb);
		sb.append("sin ");
	}
	
	public void LaTeX(StringBuilder sb) {
		if (angle instanceof Const) {
			sb.append("\\sin");
			angle.infix(sb);
		} else {
			sb.append("\\sin(");
			angle.infix(sb);
			sb.append(")");
		}
	}
	
	public boolean contains(String var) {
		return angle.contains(var);
	}

	public Expr diff(String var) throws DivByZero, NegRoot {
		return new Mult(new Cos(angle), angle.diff(var));
	}

}
