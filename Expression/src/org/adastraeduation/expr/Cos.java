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
		if (angle instanceof Const) {
			sb.append("cos");
			angle.infix(sb);
		} else {
			sb.append("cos(");
			angle.infix(sb);
			sb.append(")");
		}
	}
	
	public void RPN(StringBuilder sb) {
		angle.RPN(sb);
		sb.append("cos ");
	}
	
	public void LaTeX(StringBuilder sb) {
		if(angle instanceof Const) {
			sb.append("\\cos");
			angle.infix(sb);
		} else {
			sb.append("\\cos(");
			angle.infix(sb);
			sb.append(")");
		}
	}
	
	public boolean contains(String var) {
		return angle.contains(var);
	}

	public Expr diff(String var) throws DivByZero, NegRoot {
		return new Mult(new Mult(new Sin(angle), new Const(-1)), angle.diff(var));
	}

}
