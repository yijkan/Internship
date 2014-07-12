public class Vector {
	//a 3D vector
	private double x,y,z;
	public Vector() {
		x = 0;
		y = 0;
		z = 0;
	}
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public String toString() {
		return "<" + x + ", " + y + ", " + z + ">";
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}
	
	public double mag() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	public Vector opp() {
		return new Vector(-x,-y,-z); 
	}
	public void neg() {
		x = -x;
		y = -y;
		z = -z;
	}
	public static Vector add(Vector a, Vector b) {
		return new Vector(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
	}
	public Vector mult(double a) {
		//Return a vector that is a scalar multiple of the original by a factor of a
		return new Vector(x*a, y*a, z*a);
	}
	public static double dotP(Vector a, Vector b) {
		//Return the dot product of two vectors
		return a.getX()*b.getX() + a.getY()*b.getY() + a.getZ()*b.getZ();
	}
	public static Vector crossP(Vector a, Vector b) {
		//Return the cross product of two vectors
		return new Vector(a.getY()*b.getZ() - a.getZ()*b.getY(), a.getZ()*b.getX() - a.getX()*b.getZ(), a.getX()*b.getY() - a.getY()*b.getX());
	}
	
	public static void main(String[] args) {
		Vector v = new Vector(1,2,3);
		Vector w = new Vector(3,1,2);
		
		System.out.println("The magnitude of " + v + " is " + v.mag());
		System.out.println("-" + v + " = " + v.opp());
		System.out.println(v + " + " + w + " = " + Vector.add(v, w));
		System.out.println("2" + v + " = " + v.mult(2));
		System.out.println(v + " · " + w + " = " + Vector.dotP(v, w));
		System.out.println(v + " x " + w + " = " + Vector.crossP(v, w));
	}
}
