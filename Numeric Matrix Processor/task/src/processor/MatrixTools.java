package processor;

import java.util.Scanner;

public class MatrixTools {
    public static void printMatrix(double[][] matrix) {
        for (double[] doubles : matrix) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < matrix[0].length; j++) {
                stringBuilder.append(String.format("%.2f", doubles[j]));
                if (j != matrix[0].length - 1) {
                    stringBuilder.append(" ");
                }
            }
            System.out.println(stringBuilder);
        }
        System.out.println();
    }
    public static double[][] readMatrix(int n, int m, Scanner scanner) {
        double[][] a = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = scanner.nextDouble();
            }
        }
        return a;
    }
}
