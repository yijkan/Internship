package org.adastraeduation.expr;

public class Sec extends Expr {
	private Expr angle;
	
	public Sec(Expr a) {
		angle = a;
	}

	public double eval() {
		return 1.0 / new Cos(angle).eval();
	}

	public void infix(StringBuilder sb) {
		if (angle instanceof Const) {
			sb.append("sec");
			angle.infix(sb);
		} else {
			sb.append("sec(");
			angle.infix(sb);
			sb.append(")");
		}
	}
	
	public void RPN(StringBuilder sb) {
		angle.RPN(sb);
		sb.append("sec ");
	}
	
	public void LaTeX(StringBuilder sb) {
		if (angle instanceof Const) {
			sb.append("\\sec");
			angle.infix(sb);
		} else {
			sb.append("\\sec(");
			angle.infix(sb);
			sb.append(")");
		}
	}
	
	public boolean contains(String var) {
		return angle.contains(var);
	}

	public Expr diff(String var) throws DivByZero, NegRoot {
		return new Mult(new Mult(new Sec(angle), new Tan(angle)), angle.diff(var));
	}
}
