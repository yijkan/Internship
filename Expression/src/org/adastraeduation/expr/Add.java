package org.adastraeduation.expr;

/**
 * Binary Operator addition
 * @author yijinkang
 *
 */
public class Add extends Expr {
	private Expr a, b;
	/**
	 * The two expressions to be added
	 */
	
	public Add(Expr a, Expr b) { 
		this.a = a; 
		this.b = b; 
	}
	
	public double eval() { 
		return a.eval() + b.eval(); 
	}

	public void infix(StringBuilder sb) {
		if (a instanceof Const) {
			a.infix(sb);
		} else {
			sb.append("(");
			a.infix(sb);
			sb.append(")");
		}
		sb.append(" + ");
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
		sb.append(" + ");
	}
	
	public void LaTeX(StringBuilder sb) {
		if (a instanceof Const) {
			a.infix(sb);
		} else {
			sb.append("(");
			a.infix(sb);
			sb.append(")");
		}
		sb.append(" + ");
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
		return new Add(a.diff(var), b.diff(var));
	}

	/*public Expr integ(String var) {
		return new Add(a.integ(var), b.integ(var));
	}*/


}
