package org.adastraeduation.expr;
/**
 * Binary operator power
 * @author yijinkang
 *
 */
public class Power extends Expr {
	private Expr b, e;
	/**
	 * Base b will be raised to the power of exponent e
	 */
	
	public Power(Expr x, Expr y) {
		b = x;
		e = y;
	}
	
	public double eval() {
		return Math.pow(b.eval(), e.eval());
	}
	
	public void infix(StringBuilder sb) {
		if (b instanceof Const) {
			b.infix(sb);
		} else {
			sb.append("(");
			b.infix(sb);
			sb.append(")");
		}
		sb.append(" ^ ");
		if (e instanceof Const) {
			e.infix(sb);
		} else {
			sb.append("(");
			e.infix(sb);
			sb.append(")");
		}
	}
	
	public void RPN(StringBuilder sb) {
		b.RPN(sb);
		e.RPN(sb);
		sb.append("^ ");
	}
	
	public void LaTeX(StringBuilder sb) {
		sb.append("{");
		b.LaTeX(sb);
		sb.append("}^{");
		e.LaTeX(sb);
		sb.append("}");
	}
	
	public boolean contains(String var) {
		return b.contains(var) || e.contains(var);
	}

	public Expr diff(String var) throws DivByZero, NegRoot {
		if(b.contains(var)) {
			if(e.contains(var)) {
				// return new Mult(new Power(new Mult(e.diff(var), b), e), new Log(var));
				return null;
			} else {
				return new Mult(new Mult(e, new Power(b, new Const(e.eval() - 1))), b.diff(var));
			}
		} else {
			if(e.contains(var)) {
				return null; 
				// return new Mult(new Mult(this, new Log(b)), e.diff(var)); <- nope
			} else {
				return new Const (0);
			}
		}
	}

}
