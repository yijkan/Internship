package org.adastraeduation.expr;
/**
 * Unary operator tangent
 * @author yijinkang
 *
 */
public class Tan extends Expr {
	private Expr angle;
	
	public Tan(Expr a) {
		angle = a;
	}
	
	public double eval() {
		return Math.tan(angle.eval());
	}
	
	public void infix(StringBuilder sb) {
		if (angle instanceof Const) {
			sb.append("tan");
			angle.infix(sb);
		} else {
			sb.append("tan(");
			angle.infix(sb);
			sb.append(")");
		}
	}
	
	public void RPN(StringBuilder sb) {
		angle.RPN(sb);
		sb.append("tan ");
	}
	
	public void LaTeX(StringBuilder sb) {
		if (angle instanceof Const) {
			sb.append("\\tan");
			angle.infix(sb);
		} else {
			sb.append("\\tan(");
			angle.infix(sb);
			sb.append(")");
		}
	}
	
	public boolean contains(String var) {
		return angle.contains(var);
	}

	public Expr diff(String var) throws DivByZero, NegRoot {
		return new Mult(new Mult(new Sec(angle), new Sec(angle)), angle.diff(var));
	}

}
