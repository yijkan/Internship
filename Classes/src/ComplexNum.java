
public class ComplexNum {
	private double a, b;
	ComplexNum() {
		a = 0;
		b = 0;
	}
	ComplexNum(double a, double b) {
		this.a = a;
		this.b = b;
	}
	public String toString() {
		if (b < 0) {
			return a + "-" + -b + "i";
		} else if (b == 0) {
			return Double.toString(a);
		} else if (b == 1) {
			return a + "+i";
		} else {
			return a + "+" + b + "i";
		}
	}
	
	public double getA() {
		return a;
	}
	public double getB() {
		return b;
	}
	public double mag() {
		//Find the magnitude of the complex number
		return Math.sqrt(a*a + b*b);
	}
	public ComplexNum con() {
		//Find the conjugate of the complex number
		return new ComplexNum(a, -b);
	}
	
	public ComplexNum opp() {
		//Find the opposite complex number
		return new ComplexNum(-a, -b);
	}
	public void neg() {
		//Negates the complex number
		a = -a;
	}
	public static ComplexNum add(ComplexNum x, ComplexNum y) {
		//Add complex numbers
		return new ComplexNum(x.getA() + y.getA(), x.getB() + y.getB());
	}
	public static ComplexNum sub(ComplexNum x, ComplexNum y) {
		//Subtract complex numbers
		return new ComplexNum(x.getA() - y.getA(), x.getB() - y.getB());
	}
	public static ComplexNum mult(ComplexNum x, ComplexNum y) {
		//Multiply complex numbers
		return new ComplexNum(x.getA()*y.getA()-x.getB()*y.getB(), x.getA()*y.getB()+x.getB()*y.getA());
	}
	
	public static void main(String[] args) {
		System.out.println(new ComplexNum(1,0));
		System.out.println(new ComplexNum(1,1));
		
		ComplexNum z = new ComplexNum(3,4);
		ComplexNum w = new ComplexNum(1,2);
		System.out.println("The magnitude of " + z + " is " + z.mag());
		System.out.println("The conjugate of " + z + " is " + z.con());
		System.out.println("-(" + z + ") = " + z.opp());
		System.out.println(z + " + " + w + " = " + ComplexNum.add(z,w));
		System.out.println(z + " - " + w + " = " + ComplexNum.sub(z,w));
		System.out.println(z + " * " + w + " = " + ComplexNum.mult(z,w));
	}
}
