import java.util.Stack;

public class Matrix {
	public static void main(String[] args) 
			throws MismatchingMatrices, BadMatrixDimensions, LinDepRows {
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
		
		Matrix m6 = new Matrix(3,2);
		
		m6.set(0,1,5);
		m6.set(1,1,6);
		m6.set(2,1,4);
		
		m6.set(0,0,0);
		m6.set(1,0,3);
		m6.set(2,0,1);
		
		System.out.println("FINAL SOLVE\nVariables\n" + m6.solve());
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
	
	public Matrix elim() throws BadMatrixDimensions, LinDepRows {
		Matrix x = new Matrix(getCols(), getRows());
		x = this;
		// Matrix must have one more column than it has rows
		if (x.getCols() - 1 != x.getRows()) {
			throw new BadMatrixDimensions();
		}
		
		if (x.getRows() == 1) {
			/*If there is only one row, we can solve for a variable by 
			 * dividing the right side by the left side
			 * Return the variable value in the form of a 1 x 1 matrix 
			 */
			Matrix m = new Matrix(1, 1);
			if (x.get(0,0) == 0) {
				throw new LinDepRows();
			}
			m.set(0, 0, x.get(1, 0) / x.get(0,0));
			System.out.println("Variable: " + m + "\n");
			return m;
		}
		
		// Find the largest value in the first column
		double max = x.get(0, 0);
		int pos = 0;
		for (int i = 1; i < x.getRows(); i++) {
			if (max < x.get(0,i)) {
				max = x.get(0,1);
				pos = i;
			}
		}
		
		/* Eliminate the first column in each row by subtracting the 
		 * first column of the row 
		 * times the entry in the same column in the row with the largest leftmost entry
		 * divided by the largest leftmost entry
		 * from each entry in the matrix 
		 */
		for (int j = 0; j < x.getRows(); j++) {
			if (j == pos) {
				continue;
			}
			double temp = x.get(0,j);
			for (int i = 0; i < x.getCols(); i++) {
				x.set(i, j, x.get(i, j) - temp * x.get(i, pos) / max );
			}
		}
		/* Set the row with the largest leftmost entry entry equal to zero
		 * Don't want to do this during the loop since it will mess with the other calcs
		 */
		for (int i = 0; i < x.getCols(); i++) {
			x.set(i, pos, 0.0);
		}
		
		// Shift all rows up to fill in the "pos" row
		for (int j = pos; j < x.getRows() - 1; j++) {
			for (int i = 0; i < x.getCols(); i++) {
				x.set(i, j, x.get(i, j+1));
			}
		}
		
		// Create a new, smaller matrix
		Matrix m = new Matrix(x.getCols()-1, x.getRows()-1);
		// Fill it with the entries that have not been eliminated
		for (int j = 0; j < m.getRows(); j++) {
			for (int i = 0; i < m.getCols(); i++) {
				m.set(i, j, x.get(i + 1, j));
			}
		}
		// System.out.println("Shrunken Matrix\n" + m + "\n");
		return m.elim();
	}
	
	public Matrix solve() throws BadMatrixDimensions, LinDepRows {
		System.out.println("Matrix inputted\n" + this + "\n");
		Matrix s;
		//Top left cannot be zero. Switch rows if this is the case.
			/*First, check to make sure the first column isn't all zeros. 
			/Shift matrix left & omit last row if this is the case*/
		
		boolean zeroCol = true;
		for (int j = 0; j < getRows(); j++) {
			if (get(0,j) != 0) {
				zeroCol = false;
				System.out.println("Leftmost column is NOT all zeros\n");
				break;
			}
		}
		
		if (get(0,0) != 0) {
			s = this;
		} else if (zeroCol) {
			s = new Matrix(getCols()-1, getRows()-1);
			for (int j = 0; j < s.getRows(); j++) {
				for (int i = 0; i < s.getCols(); i++) {
					s.set(i, j, get(i+1,j));
				}
			}
		} else {
			System.out.println("Top left is zero. Rows will be shifted\n");
			s = new Matrix(getCols(), getRows());
			for (int x = 1; x < getRows(); x++) {
				if (get(0,x) != 0) {
					for (int i = 0; i < getCols(); i++) {
						s.set(i, 0, get(i,x));
						s.set(i, x, get(i,0));
						
						for (int j = 1; j < x; j++) {
							s.set(i, j, get(i,j));
						}
						for (int j = x + 1; j < getRows(); j++) {
							s.set(i, j, get(i,j));
						}
					}
					break;
				}
			}
			
		}
		
		// # rows = # variables
		int numVars = getRows();
		Stack<Matrix> vars = new Stack<Matrix>();
		
		for (int count = 0; count < numVars; count++) {
			/* Create a new, smaller matrix with all columns except the one at the right
			 * identical to the previous 
			 */
			Matrix t = new Matrix(s.getCols()-1, s.getRows()-1);
			for (int j = 0; j < t.getRows(); j++) {
				for (int i = 0; i < t.getCols()-1; i++) {
					// All columns except the last in the new matrix are the same as the old
					t.set(i, j, s.get(i,j));
				}
			}
			// Create a temporary 2-column matrix to save the two right columns
			Matrix temp = new Matrix(2, t.getRows());
			for (int j = 0; j < t.getRows(); j++) {
				temp.set(0, j, s.get(s.getCols()-2, j));
				temp.set(1, j, s.get(s.getCols()-1, j));
			}
			
			System.out.println("Matrix To Shrink\n" + s + "\n");
			System.out.println("Matrix t\n" + t + "\n");
			System.out.println("Matrix temp\n" + temp + "\n");
			
			vars.add(s.elim());
			
			for (int j = 0; j < t.getRows(); j++) {
				/* The last column of the new matrix
				 *  = Last column of old matrix - 
				 *  (second to last column of old matrix * most recent variable)
				 */
				t.set(t.getCols()-1, j, temp.get(1, j) - (temp.get(0, j) * vars.peek().get(0,0)));
			}
			// Set s = t so the new matrix will be used during the next iteration
			s = t;
		}
		
		// Create a matrix to represent solved variable values. 
		Matrix m = new Matrix(numVars, 1);
		// Fill in the matrix with the solved variable values
		for (int i = 0; i < numVars; i++) {
			m.set(i, 0, vars.pop().get(0,0));
		}
		return m;
	}
}

class MismatchingMatrices extends Exception {
	
}

class BadMatrixDimensions extends Exception {
	
}

class LinDepRows extends Exception {

}
