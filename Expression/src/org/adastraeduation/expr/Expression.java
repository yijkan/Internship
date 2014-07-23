package org.adastraeduation.expr;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

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
	
	public Expr parseRPN(String in) throws DivByZero, NegRoot, OpMismatch {
		Stack<Expr> operands = new Stack<Expr>();

		Scanner s = new Scanner(in);
		while (s.hasNext()) {
			String n = s.next();
			if (n.charAt(0) >= '0' && n.charAt(0) <= '9') {
				operands.add(new Const(Double.parseDouble(n)));
			}
			// Operator if-else statements - All need to have EmptyStack error if empty
			else if (n.equals("^")) {
				double e = operands.pop().eval();
				operands.add(new Power(operands.pop(), new Const(e)));
			} else if (n.equals("+")) {
				operands.add(new Add(operands.pop(), operands.pop()));
			} else if (n.equals("-")) {
				operands.add(new Sub(operands.pop(), operands.pop()));
			} else if (n.equals("*")) {
				operands.add(new Mult(operands.pop(), operands.pop()));
			} else if (n.equals("/")) {
				double den = operands.pop().eval();
				operands.add(new Div(operands.pop(), new Const(den)));
			} else if (n.equals("sin")) {
				operands.add(new Sin(operands.pop()));
			} else if (n.equals("cos")) {
				operands.add(new Cos(operands.pop()));
			} else if (n.equals("tan")) {
				operands.add(new Tan(operands.pop()));
			} else if (n.equals("sqrt")) {
				operands.add(new Sqrt(operands.pop()));
			}
			// Otherwise it's a variable
			else {
				operands.add(vars.get(n));
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
		
		Stack<Expr> operands = new Stack<Expr>();
		Stack<OpFact> operators = new Stack<OpFact>();
		
		Scanner s = new Scanner(in);
		while (s.hasNext()) {
			String n = s.next();
			if(n.charAt(0) >= '0' && n.charAt(0) <= '9') {
				operands.push(new Const(Double.parseDouble(n)));
			} 
			// Operator if-else statements
			else if (n.equals("+")) {
				if (!operators.empty() && operators.peek().getPrec() >= new AddFact().getPrec()) {
					if (operators.peek().isUnary()) {
						operands.push(operators.pop().make(operands.pop()));
					} else {
						operands.push(operators.pop().make(operands.pop(), operands.pop()));
					}
				}
				operators.push(new AddFact());
			} else if (n.equals("-")) {
				if (!operators.empty() && operators.peek().getPrec() >= new SubFact().getPrec()) {
					if (operators.peek().isUnary()) {
						operands.push(operators.pop().make(operands.pop()));
					} else {
						operands.push(operators.pop().make(operands.pop(), operands.pop()));
					}
				}
				operators.push(new SubFact());
			} else if (n.equals("*")) {
				if (!operators.empty() && operators.peek().getPrec() >= new MultFact().getPrec()) {
					if (operators.peek().isUnary()) {
						operands.push(operators.pop().make(operands.pop()));
					} else {
						operands.push(operators.pop().make(operands.pop(), operands.pop()));
					}
				}
				operators.push(new MultFact());
			} else if (n.equals("/")) {
				if (!operators.empty() && operators.peek().getPrec() >= new DivFact().getPrec()) {
					if (operators.peek().isUnary()) {
						operands.push(operators.pop().make(operands.pop()));
					} else {
						operands.push(operators.pop().make(operands.pop(), operands.pop()));
					}
				}
				operators.push(new DivFact());
			} else if (n.equals("^")) {
				if (!operators.empty() && operators.peek().getPrec() >= new PowFact().getPrec()) {
					if (operators.peek().isUnary()) {
						operands.push(operators.pop().make(operands.pop()));
					} else {
						operands.push(operators.pop().make(operands.pop(), operands.pop()));
					}
				}
				operators.push(new PowFact());
			} 
			/* Unary operators do not affect preceeding operands
			 * They are of highest precedence, so just add the OpFact to the operators
			 */
			else if (n.equals("sqrt")) {
				operators.push(new SqrtFact());
			} else if (n.equals("sin")) {
				operators.push(new SinFact());
			} else if (n.equals("cos")) {
				operators.push(new CosFact());
			} else if (n.equals("tan")) {
				operators.push(new TanFact());
			}
			// Otherwise it's a variable
			else {
				operands.push(vars.get(n.toString()));
			}
		}
		s.close();
		
		while(!operators.empty()) {
			if (operators.peek().isUnary()) {
				operands.push(operators.pop().make(operands.pop()));
			} else {
				operands.push(operators.pop().make(operands.pop(), operands.pop()));
			}
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
		System.out.println(e1.eval() + " = 5?");
		System.out.println(e2.eval() + " = 1.5?"); 
		System.out.println(e3.eval() + " = 9?");
		System.out.println(e4.eval() + " = 9?"); 
		
		Expr e5 = this.parseRPN("x y + 2 /");
		System.out.println(e5.eval() + " = 3.5?");
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
		*/
		System.out.println("Test 5:");
		e.test5();
	}
}

class OpMismatch extends Exception {
	// Number of operands and operators don't match
}
