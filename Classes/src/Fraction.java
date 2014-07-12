public class Fraction {
	private int num, den;
	public static int gcf(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcf(b, a%b);
	}
	public Fraction() {
		num = 0;
		den = 1;
	}
	public Fraction(int num, int den) throws DivByZero {
		int div = gcf(num,den);
		if (den == 0) {
			throw new DivByZero();
		}
		//Using this method would require that the user use try/catch in the main, unless the main throws the error
		this.num = num/div;
		this.den = den/div;
		if (this.den < 0) {
			this.num = -this.num;
			this.den = -this.den;
		}
	}
	
	public String toString() {
		if (den == 1) {
			//If denominator is 1, just print the numerator
			return Integer.toString(num);
		}
		return num + "/" + den;
	}
	
	public int getNum() {
		return num;
	}
	public int getDen() {
		return den;
	}
	
	public Fraction opp() throws DivByZero {
		//Find the opposite Fraction
		return new Fraction(-num,den);
	}
	public void neg() {
		//Negates the fraction
		num = -num;
	}
	public double dec() {
		//Find the decimal value of the Fraction
		return (double) num / den;
	}
	public static Fraction add(Fraction f1, Fraction f2) throws DivByZero {
		//Add Fractions
		return new Fraction(f1.getNum()*f2.getDen() + f2.getNum()*f1.getDen(), f1.getDen()*f2.getDen());
	}
	public static Fraction sub(Fraction f1, Fraction f2) throws DivByZero {
		//Subtract Fractions
		return add(f1, f2.opp());
	}
	public static Fraction mult(Fraction f1, Fraction f2) throws DivByZero {
		//Multiply Fractions
		return new Fraction(f1.getNum() * f2.getNum(), f1.getDen() * f2.getDen());
	}
	
	public static void main(String[] args) throws DivByZero {
		Fraction f1 = new Fraction(2,4);
		Fraction f2 = new Fraction(3,2);
		System.out.println("-" + f1 + " = " + f1.opp());
		System.out.println("The decimal value of " + f1 + " is " + f1.dec());
		System.out.println(f1 + " + " + f2 + " = " + Fraction.add(f1, f2));
		System.out.println(f1 + " - " + f2 + " = " + Fraction.sub(f1,f2));
		System.out.println(f1 + " * " + f2 + " = " + Fraction.mult(f1,f2));
		Fraction ew = new Fraction(1,0);
	}
}

class DivByZero extends Exception {
	
}