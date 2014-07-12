public class Matrix {
	public static void main(String[] args) throws UnmatchingMatrices {
		Matrix m1 = new Matrix(2,3);
		m1.set(0, 0, 2);
		m1.set(1, 0, 1);
		m1.set(0, 1, 3);
		m1.set(1, 1, 4);
		m1.set(0, 2, 1);
		m1.set(1, 2, 3);
		System.out.println(m1 + "\n");
		
		Matrix m2 = new Matrix(3,2);
		m2.set(0,0,1);
		m2.set(1,0,2);
		m2.set(2,0,3);
		m2.set(0,1,4);
		m2.set(1,1,4);
		m2.set(2,1,1);
		System.out.println(m2+"\n");
		
		System.out.println("Product:\n" + mult(m1,m2) + "\n");
		
		for (int x=1; x<=80; x++) {
			System.out.print("-");
		}
		System.out.println("\n");
		
		Matrix m3 = new Matrix(2,2);
		m3.set(0,0,1);
		m3.set(1,0,1);
		m3.set(0,1,1);
		m3.set(1,1,1);
		System.out.println(m3 + "\n");
		
		Matrix m4 = new Matrix(2,2);
		m4.set(0, 0,2);
		m4.set(1,0,3);
		m4.set(0,1,4);
		m4.set(1,1,5);
		System.out.println(m4 + "\n");
		
		System.out.println("Sum:\n" + add(m3,m4) + "\n");
		System.out.println("Diff:\n" + sub(m3,m4) + "\n");
		
	}
	
	private double[] m;
	private int rows;
	private int cols;
	
	//constructors
	public Matrix() {
		m = new double[1];
		rows = 1;
		cols = 1;
	}
	public Matrix(int cols, int rows) {
		m = new double[rows*cols];
		this.rows = rows;
		this.cols = cols;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (int y=0; y < rows; y++) {
			for (int x=0; x < cols; x++) {
				sb.append(get(x,y));
				if (x < cols-1) {
					sb.append(", ");
				} else {
					sb.append(" ");
				}
			}
			if (y < rows-1) {
				sb.append("\n  ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	//get the number of rows / cols of a matrix
	public int getRows() { return rows; }
	public int getCols() { return cols; }
	//get the value stored in any position in the matrix by its array position
	public double get(int pos) { return m[pos]; }
	//get the value stored in any position in the matrix by its matrix position
	public double get(int x, int y) { return m[y * cols + x]; }
	//set the value stored in any position in the matrix by its array position
	public void set (int pos, double a) { m[pos] = a; }
	//set the value stored in any position in the matrix by its matrix position
	public void set (int x, int y, double a) { m[y * cols + x] = a; }
	
	public Matrix opp() {
		Matrix a = new Matrix(cols, rows);
		for (int x=0; x<m.length; x++) {
			a.set(x, -m[x]);
		}
		return a;
	}
	public static Matrix add(Matrix m, Matrix n) throws UnmatchingMatrices {
		if (m.getRows() != n.getRows() || m.getCols() != n.getRows()) {
			throw new UnmatchingMatrices();
		}
		Matrix a = new Matrix(m.getCols(), m.getRows());
		for (int x = 0; x < m.getRows() * m.getCols(); x++) {
			a.set(x, m.get(x) + n.get(x));
		}
		return a;
	}
	public static Matrix sub(Matrix m, Matrix n) throws UnmatchingMatrices {
		return add(m, n.opp());
	}
	public static Matrix mult(Matrix m, Matrix n) throws UnmatchingMatrices {
		if (m.getCols() != n.getRows()) {
			throw new UnmatchingMatrices();
		}
		Matrix a = new Matrix(n.getCols(), m.getRows());
		for (int y=0; y < m.getRows(); y++) {
			for (int x=0; x < n.getCols(); x++) {
				double temp = 0;
				for (int z=0; z < n.getRows(); z++) {
					temp += m.get(z,y) * n.get(x,z);
					//System.out.println(m.get(z,y) + " * "+n.get(x,z));
				}
				//System.out.println("temp = " + temp);
				a.set(x, y, temp);
			}
		}
		return a;
	}
}

class UnmatchingMatrices extends Exception {
	
}
