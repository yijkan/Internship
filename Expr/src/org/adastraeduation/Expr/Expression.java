package org.adastraeduation.expr;

import java.util.HashMap;

public class Expression {
	private HashMap<String, Var> vars;
	
	
	public class Var extends Expr {
		private double val;
		public Var(String name, double initialValue) {
			val = initialValue;
			vars.put(name, this);
		}
		public double eval() {
			return val;
		}
		
		public void set(double v) {
			val = v;
		}
	}
	public Expression() {
		vars = new HashMap<String, Var>();
	}

	public void test1() {
		Var x = new Var("x", 3);
		Expr e = new Mult(new Const(6), x);
		System.out.println(e.eval());
		x.set(4);
		System.out.println(e.eval());
	}
	public void test2() {
		Expr e = new Mult(new Add(new Const(6), new Const(3)), x);
	}
	// xsin(6x)
	public void test3() {
		Var x = new Var("x", 0);
		Expr e = new Mult(
				new Sin(new Mult(new Const(6), x)), x);
	}
	
	/*
	 * Things to look up:
	 * class Stack<>
	 * class ArrayList<>
	 * class HashMap<key,value>
	 * sin, cos, tan,
	 * sqrt
	 * power (^)
	 * +, -, *, /
	 * 
	 * Eventually, parse:
	 * "6 + sin(x)"  (infix is hard)
	 * 
	 * in RPN:
	 * "6 4 + 3 x cos * +"
	 * (4 + 6) + (3 * cos(x))
	 */
	public static void main(String[] args) {
		Expression e = new Expression();
		e.test1();
		e.test2();
	}
}
