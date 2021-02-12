package processor;

public class Matrix {

    private final double[][] matrix;
    private final int rows;
    private final int columns;

    public Matrix(int rows, int columns) {
        matrix = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double[] getRow(int x) {
        return matrix[x];
    }

    public void setRow(int x, double[] row) {
        matrix[x] = row;
    }

    public double getValue(int x, int y) {
        return matrix[x][y];
    }

    public void setValue(int x, int y, double value) {
        matrix[x][y] = value;
    }
}
