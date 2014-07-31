package org.adastraeduation.expr;

public class Log extends Expr {
	private Expr a;
	
	public Log(Expr a) {
		this.a = a;
	}
	
	public double eval() {
		return Math.log(a.eval());
	}

	public void infix(StringBuilder sb) {
		if(a instanceof Const) {
			sb.append("log");
			a.infix(sb);
		} else {
			sb.append("log(");
			a.infix(sb);
			sb.append(")");
		}
	}
	
	public void RPN(StringBuilder sb) {
		a.RPN(sb);
		sb.append("log ");
	}
	
	public void LaTeX(StringBuilder sb) {
		if(a instanceof Const) {
			sb.append("\\log");
			a.infix(sb);
		} else {
			sb.append("\\log(");
			a.infix(sb);
			sb.append(")");
		}
	}

	public boolean contains(String var) {
		return a.contains(var);
	}

	public Expr diff(String var) throws DivByZero, NegRoot {
		return new Mult(new Div(new Const(1), a), a.diff(var));
	}

}
