package processor;

public class MatrixCalculator {
    public Matrix sum(Matrix a, Matrix b) {
        int n = a.getRows();
        int m = a.getColumns();
        if (n != b.getRows() || m != b.getColumns()) {
            return null;
        }
        Matrix c = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c.setValue(i, j, a.getValue(i, j) + b.getValue(i, j));
            }
        }
        return c;
    }

    public Matrix multiplyByNumber(Matrix matrix, double n) {
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                matrix.setValue(i, j, matrix.getValue(i, j) * n);
            }
        }
        return matrix;
    }

    public Matrix multiply(Matrix a, Matrix b) {
        int n = a.getRows();
        int k = a.getColumns();
        int m = b.getColumns();
        if (k != b.getRows()) {
            return null;
        }
        Matrix c = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double temp = 0;
                for (int o = 0; o < k; o++) {
                    temp += a.getValue(i, o) * b.getValue(o, j);
                }
                c.setValue(i, j, temp);
            }
        }
        return c;
    }

    public Matrix transposeMain(Matrix matrix) {
        int n = matrix.getRows();
        int m = matrix.getColumns();
        Matrix result = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result.setValue(i, j, matrix.getValue(j, i));
            }
        }
        return result;
    }

    public Matrix transposeSide(Matrix matrix) {
        int n = matrix.getRows() - 1;
        int m = matrix.getColumns() - 1;
        Matrix result = new Matrix(n + 1, m + 1);
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                result.setValue(i, j, matrix.getValue(n - j, m - i));
            }
        }
        return result;
    }

    public Matrix transposeVertical(Matrix matrix) {
        int n = matrix.getRows() - 1;
        int m = matrix.getColumns() - 1;
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= m / 2; y++) {
                swap(x, y, x, m - y, matrix);
            }
        }
        return matrix;
    }

    private void swap(int x1, int y1, int x2, int y2, Matrix matrix) {
        double temp = matrix.getValue(x1, y1);
        matrix.setValue(x1, y1, matrix.getValue(x2, y2));
        matrix.setValue(x2, y2, temp);
    }

    public Matrix transposeHorizontal(Matrix matrix) {
        int n = matrix.getRows() - 1;
        for (int x = 0; x <= n / 2; x++) {
            double[] temp = matrix.getRow(x);
            matrix.setRow(x, matrix.getRow(n - x));
            matrix.setRow(n - x, temp);
        }
        return matrix;
    }

    public double findDeterminant(Matrix matrix) {
        int m = matrix.getRows();
        if (m == 1) {
            return matrix.getValue(0, 0);
        }
        if (m == 2) {
            return matrix.getValue(0, 0) * matrix.getValue(1, 1) -
                    matrix.getValue(0, 1) * matrix.getValue(1, 0);
        }
        double determinant = 0;
        for (int i = 0; i < m; i++) {
            int sign = (i) % 2 == 0 ? 1 : -1;
            determinant += matrix.getValue(0, i) * sign * findDeterminant(getMatrix(0, i, matrix));
        }
        return determinant;
    }

    private Matrix getMatrix(int x, int y, Matrix matrix) {
        Matrix newMatrix = new Matrix(matrix.getRows() - 1, matrix.getRows() - 1);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                newMatrix.setValue(i, j, matrix.getValue(i, j));
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = y + 1; j < matrix.getRows(); j++) {
                newMatrix.setValue(i, j - 1, matrix.getValue(i, j));
            }
        }
        for (int i = x + 1; i < matrix.getRows(); i++) {
            for (int k = 0; k < y; k++) {
                newMatrix.setValue(i - 1, k, matrix.getValue(i, k));
            }
        }
        for (int i = x + 1; i < matrix.getRows(); i++) {
            for (int k = y + 1; k < matrix.getRows(); k++) {
                newMatrix.setValue(i - 1, k - 1, matrix.getValue(i, k));
            }
        }
        return newMatrix;
    }

    public Matrix inverseMatrix(Matrix matrix) {
        if (matrix.getRows() == 1) {
            return null;
        }
        double determinant = findDeterminant(matrix);
        if (determinant == 0) {
            return null;
        }
        matrix = transposeMain(matrix);
        Matrix inverse = new Matrix(matrix.getRows(), matrix.getRows());
        for (int i = 0; i < inverse.getRows(); i++) {
            for (int j = 0; j < inverse.getRows(); j++) {
                int sign = (int) Math.pow(-1, i + j);
                inverse.setValue(i, j, sign * findDeterminant(getMatrix(i, j, matrix)));
            }
        }
        return multiplyByNumber(inverse, 1 / determinant);
    }
}
