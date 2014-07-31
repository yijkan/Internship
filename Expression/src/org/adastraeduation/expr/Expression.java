package org.adastraeduation.expr;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Expression {
	private HashMap<String, Var> vars;
	Stack<Expr> operands = new Stack<Expr>();
	Stack<OpFact> operators = new Stack<OpFact>();
	
	
	public class Var extends Expr {
		private double val;
		private String name;
		public Var(String name, double initialValue) {
			val = initialValue;
			this.name = name;
			vars.put(name, this);
		}
		public double eval() {
			return val;
		}
		
		public void set(double v) {
			val = v;
		}
		
		public void infix(StringBuilder sb) { sb.append(name); }
		public void RPN(StringBuilder sb) { sb.append(name + " "); }
		public void LaTeX(StringBuilder sb) { sb.append(name); }
		
		public boolean contains(String var) {
			return vars.containsKey(var);
		}

		public Expr diff(String var) throws DivByZero {
			Var v = vars.get(var);
			if(this == v) {
				return new Const(1);
			} else {
				return new Const(0);
			}
		}
	}
	
	public Expression() {
		vars = new HashMap<String, Var>();
	}
	
	public Expr parseRPN(String in) throws DivByZero, NegRoot, OpMismatch {
		Scanner s = new Scanner(in);
		while (s.hasNext()) {
			String n = s.next();
			if (n.charAt(0) >= '0' && n.charAt(0) <= '9') {
				operands.push(new Const(Double.parseDouble(n)));
			}
			// Operator if-else statements - All need to have EmptyStack error if empty
			else if (n.equals("^")) {
				Expr temp = operands.pop();
				operands.push(new Power(operands.pop(), temp));
			} else if (n.equals("+")) {
				operands.push(new Add(operands.pop(), operands.pop()));
			} else if (n.equals("-")) {
				Expr temp = operands.pop();
				operands.push(new Sub(operands.pop(), temp));
			} else if (n.equals("*")) {
				operands.push(new Mult(operands.pop(), operands.pop()));
			} else if (n.equals("/")) {
				Expr temp = operands.pop();
				operands.push(new Div(operands.pop(), temp));
			} else if (n.equals("sin")) {
				operands.push(new Sin(operands.pop()));
			} else if (n.equals("cos")) {
				operands.push(new Cos(operands.pop()));
			} else if (n.equals("tan")) {
				operands.push(new Tan(operands.pop()));
			} else if (n.equals("sec")) {
				operands.push(new Sec(operands.pop()));
			} else if (n.equals("log")) {
				operands.push(new Log(operands.pop()));
			} else if (n.equals("sqrt")) {
				operands.push(new Sqrt(operands.pop()));
			}
			// Otherwise it's a variable
			else {
				operands.push(vars.get(n));
			}
		}
		s.close();
		if (operands.size() == 1) {
			return operands.pop();
		} else {
			throw new OpMismatch();
		}
	}
	
	public Expr parse(String in) throws DivByZero, NegRoot, OpMismatch {
		/* For when parenthesis are added
		if (in.contains("(")) {
			String[] par1 = in.split("(");
			String[] par2 = par1[1].split(")");
		} */
		
		Scanner s = new Scanner(in);
		while (s.hasNext()) {
			String n = s.next();
			if(n.charAt(0) >= '0' && n.charAt(0) <= '9') {
				operands.push(new Const(Double.parseDouble(n)));
			} 
			// Operator if-else statements
			else if (n.equals("+")) {
				OpFact op = new AddFact(this);
				if (!operators.empty() && operators.peek().getPrec() >= op.getPrec()) {
					operands.push(operators.pop().make(this));
				}
				operators.push(op);
			} else if (n.equals("-")) {
				OpFact op = new SubFact(this);
				if (!operators.empty() && operators.peek().getPrec() >= op.getPrec()) {
					operands.push(operators.pop().make(this));
				}
				operators.push(op);
			} else if (n.equals("*")) {
				OpFact op = new MultFact(this);
				if (!operators.empty() && operators.peek().getPrec() >= op.getPrec()) {
					operands.push(operators.pop().make(this));
				}
				operators.push(op);
			} else if (n.equals("/")) {
				OpFact op = new DivFact(this);
				if (!operators.empty() && operators.peek().getPrec() >= op.getPrec()) {
					operands.push(operators.pop().make(this));
				}
				operators.push(op);
			} else if (n.equals("^")) {
				OpFact op = new PowFact(this);
				if (!operators.empty() && operators.peek().getPrec() >= op.getPrec()) {
					operands.push(operators.pop().make(this));
				}
				operators.push(op);
			} 
			/* Unary operators do not affect preceeding operands
			 * They are of highest precedence, so just add the OpFact to the operators
			 */
			else if (n.equals("sqrt")) {
				operators.push(new SqrtFact(this));
			} else if (n.equals("sin")) {
				operators.push(new SinFact(this));
			} else if (n.equals("cos")) {
				operators.push(new CosFact(this));
			} else if (n.equals("tan")) {
				operators.push(new TanFact(this));
			} else if (n.equals("sec")) {
				operators.push(new TanFact(this));
			} else if (n.equals("log")) {
				operators.push(new TanFact(this));
			}
			// Otherwise it's a variable
			else {
				operands.push(vars.get(n.toString()));
			}
		}
		s.close();
		
		while(!operators.empty()) {
			operands.push(operators.pop().make(this));
		}
		
		if (operands.size() == 1) {
			return operands.pop();
		} else {
			throw new OpMismatch();
		}
	}

	public void test1() {
		Var x = new Var("x", 3);
		Expr e = new Mult(new Const(6), x);
		System.out.println(e.eval());
		x.set(4);
		System.out.println(e.eval());
	}
	public void test2() {
		Var x = new Var("x", 3);
		Expr e = new Mult(new Add(new Const(6), new Const(3)), x);
		System.out.println(e.eval());
	}
	// xsin(6x)
	public void test3() {
		Var x = new Var("x", 0);
		Expr e = new Mult(
				new Sin(new Mult(new Const(Math.PI), x)), x);
		System.out.println(e.eval());
	}
	
	// x^2 + sqrt(y)
	
	public void test4() throws NegRoot {
		Var x = new Var("x", 2);
		Var y = new Var("y", 9);
		// 2^2 + sqrt(9) = 7
		Expr e = new Add(new Power(x, new Const(2)), new Sqrt(y));
		System.out.println(e.eval());
		// 5^2 + sqrt(100) = 35
		x.set(5);
		y.set(100);
		System.out.println(e.eval());
	}
	
	public void test5() throws DivByZero, NegRoot, OpMismatch {
		new Var("x", 3);
		new Var("y", 4);
		Expr e1 = this.parse("x + y / 2");
		Expr e2 = this.parse("2 * x / y"); 
		Expr e3 = this.parse("x * y + 2");
		Expr e4 = this.parse("x + y + 2");
		Expr e5 = this.parse("2 ^ 3 - 2");
		System.out.println(e1.eval() + " = 5?");
		System.out.println(e2.eval() + " = 1.5?"); 
		System.out.println(e3.eval() + " = 14?");
		System.out.println(e4.eval() + " = 9?"); 
		System.out.println(e5.eval() + " = 6?");
		
		Expr e11 = this.parseRPN("x y + 2 /"); // (x + y) / 2
		System.out.println(e11.eval() + " = 3.5?"); 
		Expr e12 = this.parseRPN("y 2 / x ^"); // (y / 2) ^ x
		System.out.println(e12.eval() + " = 8");
		Expr e13 = this.parseRPN("0 cos 4 *"); // cos(0) * 4
		System.out.println(e13.eval() + " = 4"); 
	}
	/*
	 * Things to look up:
	 * class Stack<>
	 * class ArrayList<>
	 * class HashMap<key,value>
	 * 
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
	
	public void test6() throws DivByZero, NegRoot {
		Var x = new Var("x", 1.0);
		
		System.out.println("(2 + x)^2");
		Expr e1 = new Power(new Add(new Const(2), x), new Const(2));
		StringBuilder infix1 = new StringBuilder();
		e1.infix(infix1);
		System.out.println("  " + infix1.toString());
		StringBuilder RPN1 = new StringBuilder();
		e1.RPN(RPN1);
		System.out.println("  " + RPN1.toString());
		StringBuilder LaTeX1 = new StringBuilder();
		e1.LaTeX(LaTeX1);
		System.out.println("  " + LaTeX1.toString());
		
		System.out.println("sqrt (x*cos(0))");
		Expr e2 = new Sqrt(new Mult(x, new Cos(new Const(0))));
		StringBuilder infix2 = new StringBuilder();
		e2.infix(infix2);
		System.out.println("  " + infix2.toString());
		StringBuilder RPN2 = new StringBuilder();
		e2.RPN(RPN2);
		System.out.println("  " + RPN2.toString());
		StringBuilder LaTeX2 = new StringBuilder();
		e2.LaTeX(LaTeX2);
		System.out.println("  " + LaTeX2.toString());
		
	}
	
	public static void main(String[] args) throws DivByZero, NegRoot, OpMismatch {
		Expression e = new Expression();
		
		/*System.out.println("Test 1:");
		e.test1();
		System.out.println("Test 2:");
		e.test2();
		System.out.println("Test 3:");
		e.test3();
		System.out.println("Test 4:");
		e.test4();
		System.out.println("Test 5:");
		e.test5();
		*/
		e.test6();
	}
}

class OpMismatch extends Exception {
	// Number of operands and operators don't match
}
