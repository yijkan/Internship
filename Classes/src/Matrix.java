import java.util.Stack;

public class Matrix {
	public static void main(String[] args) 
			throws MismatchingMatrices, BadMatrixDimensions, BadVectorDimensions, LinDepRows {
		/*
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
		*/
		
		/*
		Matrix m5 = new Matrix (4, 3);
		m5.set(0, 0, 3);
		m5.set(1, 0, 4);
		m5.set(2, 0, -1);
		m5.set(3, 0, 1);
		
		m5.set(0, 1, 1);
		m5.set(1, 1, 2);
		m5.set(2, 1, 1);
		m5.set(3, 1, 17);
		
		m5.set(0, 2, -1);
		m5.set(1, 2, 1);
		m5.set(2, 2, 3);
		m5.set(3, 2, 4);
		
		System.out.println("FINAL SOLVE\nVariables\n" + m5.solve());
		*/
		
		Matrix m6 = new Matrix(3,3);
		
		m6.set(0,0,5);
		m6.set(1,0,6);
		m6.set(2,0,3);
		
		m6.set(0,1,6);
		m6.set(1,1,3);
		m6.set(2,1,2);
		
		m6.set(0,2,1);
		m6.set(1,2,2);
		m6.set(2,2,1);
		
		Matrix I6 = m6.LU();
		Matrix a = new Matrix(1,3);
		
		a.set(0,0,4);
		a.set(0,1,1);
		a.set(0,2,1);
		
		m6.solve(I6, a);
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
	//get the value stored in column x, row y
	public double get(int x, int y) { return m[y * cols + x]; }
	//set the value stored in any position in the matrix by its array position
	public void set (int pos, double a) { m[pos] = a; }
	//set the value stored in column x, row y
	public void set (int x, int y, double a) { m[y * cols + x] = a; }
	
	public Matrix opp() {
		Matrix a = new Matrix(cols, rows);
		for (int x=0; x<m.length; x++) {
			a.set(x, -m[x]);
		}
		return a;
	}
	public static Matrix add(Matrix m, Matrix n) throws MismatchingMatrices {
		if (m.getRows() != n.getRows() || m.getCols() != n.getRows()) {
			throw new MismatchingMatrices();
		}
		Matrix a = new Matrix(m.getCols(), m.getRows());
		for (int x = 0; x < m.getRows() * m.getCols(); x++) {
			a.set(x, m.get(x) + n.get(x));
		}
		return a;
	}
	public static Matrix sub(Matrix m, Matrix n) throws MismatchingMatrices {
		return add(m, n.opp());
	}
	public static Matrix mult(Matrix m, Matrix n) throws MismatchingMatrices {
		if (m.getCols() != n.getRows()) {
			throw new MismatchingMatrices();
		}
		Matrix a = new Matrix(n.getCols(), m.getRows());
		for (int y=0; y < m.getRows(); y++) {
			for (int x=0; x < n.getCols(); x++) {
				double temp = 0;
				for (int z=0; z < n.getRows(); z++) {
					temp += m.get(z,y) * n.get(x,z);
				}
				a.set(x, y, temp);
			}
		}
		return a;
	}
	
	public Matrix LU() throws BadMatrixDimensions {		
		//Matrix must be square
		if (getCols() != getRows()) {
			throw new BadMatrixDimensions();
		}
		
		/* Create an identity Matrix of the same size
		 */
		Matrix I = new Matrix(getCols(), getRows());
		for (int x = 0; x < I.getCols(); x++) {
			I.set(x, x, 1.0);
		}
		
		System.out.println("Original Matrix\n" + this + "\n");
		
		//Row reduction
		for (int x = 0; x < getCols()-1; x++) {
			
		/* Partial Pivoting...not yet
			// Find the largest value in column x.
			// max = largest value in column x
			// pos = row containing largest value 
			double max = get(x, x);
			int pos = x;
			for (int y = x+1; y < getRows(); y++) {
				if (max < Math.abs(get(x,y))) {
					max = get(x,y);
					pos = y;
				}
			}
			// Swap rows of this & p so pos is now in row x
			for (int i = x; i < getCols(); i++) {
				double temp1 = get(i, x);
				set(i, x, get(i, pos));
				set(i, pos, temp1);
				
				double temp2 = p.get(i, x);
				p.set(i, x, p.get(i, pos));
				p.set(i, pos, temp2);
			}
			System.out.println("Swapped Matrix\n" + this);
			System.out.println(p + "\n");
		*/
			
			/* Eliminate column x in each row by subtracting
			 * column x of the row of "this"
			 * times the entry in the same column from row x
			 * divided by column x in row x of "this"
			 * from each entry in the matrix 
			 */
			//Start in row x+1, column x
			for (int j = x+1; j < getRows(); j++) {
				for (int i = x; i < getCols(); i++) {
					I.set(i, j, I.get(i, j) - get(x,j) * I.get(i, x) / I.get(x,x) );
					if (i==x) {
						continue; // don't touch column x of this yet
					} 
					set(i, j, get(i, j) - get(x,j) * get(i, x) / get(x,x) );
				}
				// Manually set column x in each row after row x of this to 0
				set(x, j, 0);
			}
			System.out.println("Eliminated Matrix\n" + this);
			System.out.println(I + "\n");
		}
		return I;
	}
	public Matrix solve(Matrix lu, Matrix a) throws BadVectorDimensions, MismatchingMatrices {
		System.out.println("SOLVE");
		// Answer "vector" must be 1 x Cols
		if (getCols() != a.getRows() || a.getCols() != 1) {
			throw new BadVectorDimensions();
		}
		
		// Answer is lu * a
		System.out.println(lu + "\n*\n" + a + "\n=\n" + mult(lu, a) + "\n");
		return mult(lu, a);
	}
}

class MismatchingMatrices extends Exception {
	
}

class BadMatrixDimensions extends Exception {
	
}

class BadVectorDimensions extends Exception {
	
}

class LinDepRows extends Exception {

}
