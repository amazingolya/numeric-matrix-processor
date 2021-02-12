package processor;

import java.util.Scanner;

public class MatrixTools {
    public static void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRows(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            double[] row = matrix.getRow(i);
            for (int j = 0; j < row.length; j++) {
                stringBuilder.append(String.format("%.2f", row[j]));
                if (j != row.length - 1) {
                    stringBuilder.append(" ");
                }
            }
            System.out.println(stringBuilder);
        }
        System.out.println();
    }
    public static Matrix readMatrix(int n, int m, Scanner scanner) {
        Matrix matrix = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix.setValue(i, j, scanner.nextDouble());
            }
        }
        return matrix;
    }
}
