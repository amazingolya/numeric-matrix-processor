package processor;

public class Calculator {
    public double[][] sum(double[][] a, double[][] b) {
        int n = a.length;
        int m = a[0].length;
        if (n != b.length || m != b[0].length) {
            return null;
        }
        double[][] c = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    public double[][] multiplyByNumber(double[][] matrix, double n) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] *= n;
            }
        }
        return matrix;
    }

    public double[][] multiply(double[][] a, double[][] b) {
        int n = a.length;
        int k = a[0].length;
        int m = b[0].length;
        if (k != b.length) {
            return null;
        }
        double[][] c = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double temp = 0;
                for (int o = 0; o < k; o++) {
                    temp += a[i][o] * b[o][j];
                }
                c[i][j] = temp;
            }
        }
        return c;
    }

    public double[][] transposeMain(double[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        double[][] result = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    public double[][] transposeSide(double[][] matrix) {
        int n = matrix.length - 1;
        int m = matrix[0].length - 1;
        double[][] result = new double[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                result[i][j] = matrix[n - j][m - i];
            }
        }
        return result;
    }

    public double[][] transposeVertical(double[][] matrix) {
        int n = matrix.length - 1;
        int m = matrix[0].length - 1;
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= m / 2; y++) {
                swap(x, y, x, m - y, matrix);
            }
        }
        return matrix;
    }

    public double[][] transposeHorizontal(double[][] matrix) {
        int n = matrix.length - 1;
        for (int x = 0; x <= n / 2; x++) {
            double[] temp = matrix[x];
            matrix[x] = matrix[n - x];
            matrix[n - x] = temp;
        }
        return matrix;
    }

    private void swap(int x1, int y1, int x2, int y2, double[][] matrix) {
        double temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

    public double findDeterminant(double[][] matrix) {
        int m = matrix.length;
        if (m == 1) {
            return matrix[0][0];
        }
        if (m == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        double determinant = 0;
        for (int i = 0; i < m; i++) {
            int sign = (i) % 2 == 0 ? 1 : -1;
            determinant += matrix[0][i] * sign * findDeterminant(getMatrix(0, i, matrix));
        }
        return determinant;
    }

    private double[][] getMatrix(int x, int y, double[][] matrix) {
        double[][] newMatrix = new double[matrix.length - 1][matrix.length - 1];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = y + 1; j < matrix.length; j++) {
                newMatrix[i][j - 1] = matrix[i][j];
            }
        }
        for (int i = x + 1; i < matrix.length; i++) {
            for (int k = 0; k < y; k++) {
                newMatrix[i - 1][k] = matrix[i][k];
            }
        }
        for (int i = x + 1; i < matrix.length; i++) {
            for (int k = y + 1; k < matrix.length; k++) {
                newMatrix[i - 1][k - 1] = matrix[i][k];
            }
        }
        return newMatrix;
    }

    public double[][] inverseMatrix(double[][] matrix) {
        if (matrix.length == 1) {
            return null;
        }
        double determinant = findDeterminant(matrix);
        if (determinant == 0) {
            return null;
        }
        matrix = transposeMain(matrix);
        double[][] inverse = new double[matrix.length][matrix.length];
        for (int i = 0; i < inverse.length; i++) {
            for (int j = 0; j < inverse.length; j++) {
                int sign = (int) Math.pow(-1, i + j);
                inverse[i][j] = sign * findDeterminant(getMatrix(i, j, matrix));
            }
        }
        return multiplyByNumber(inverse, 1 / determinant);
    }
}
