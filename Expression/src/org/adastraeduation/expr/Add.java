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
}
