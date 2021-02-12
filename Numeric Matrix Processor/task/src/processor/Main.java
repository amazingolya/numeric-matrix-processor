package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MatrixCalculator calculator = new MatrixCalculator();
        boolean reading = true;

        while (reading) {
            printMainMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: {
                    addMatrices(scanner, calculator);
                    break;
                }
                case 2: {
                    multiplyMatrixByConstant(scanner, calculator);
                    break;
                }
                case 3: {
                    multiplyMatrices(scanner, calculator);
                    break;
                }
                case 4: {
                    transposeMatrix(scanner, calculator);
                    break;
                }
                case 5: {
                    findDeterminant(scanner, calculator);
                    break;
                }
                case 6: {
                    inverseMatrix(scanner, calculator);
                    break;
                }
                case 0: {
                    reading = false;
                    break;
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate determinant");
        System.out.println("0. Exit");
        System.out.print("Your choice: > ");
    }

    private static void addMatrices(Scanner scanner, MatrixCalculator calculator) {
        System.out.print("Enter size of first matrix: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println("Enter first matrix:");
        Matrix a = MatrixTools.readMatrix(n, m, scanner);

        System.out.print("Enter size of second matrix: > ");
        int k = scanner.nextInt();
        int l = scanner.nextInt();

        System.out.println("Enter second matrix:");
        Matrix b = MatrixTools.readMatrix(k, l, scanner);

        Matrix result = calculator.sum(a, b);

        if (result == null) {
            System.out.println("The operation cannot be performed.");
        } else {
            System.out.println("The result is:");
            MatrixTools.printMatrix(result);
        }
    }

    private static void multiplyMatrixByConstant(Scanner scanner, MatrixCalculator calculator) {
        System.out.print("Enter size of matrix: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println("Enter matrix:");
        Matrix a = MatrixTools.readMatrix(n, m, scanner);

        System.out.print("Enter constant: > ");
        double k = scanner.nextInt();

        System.out.println("The result is:");
        MatrixTools.printMatrix(calculator.multiplyByNumber(a, k));
    }

    private static void multiplyMatrices(Scanner scanner, MatrixCalculator calculator) {
        System.out.print("Enter size of first matrix: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println("Enter first matrix:");
        Matrix a = MatrixTools.readMatrix(n, m, scanner);

        System.out.print("Enter size of second matrix: > ");
        int k = scanner.nextInt();
        int l = scanner.nextInt();

        System.out.println("Enter second matrix:");
        Matrix b = MatrixTools.readMatrix(k, l, scanner);

        Matrix result = calculator.multiply(a, b);

        if (result == null) {
            System.out.println("The operation cannot be performed.");
        } else {
            System.out.println("The result is:");
            MatrixTools.printMatrix(result);
        }
    }

    private static void transposeMatrix(Scanner scanner, MatrixCalculator calculator) {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");

        System.out.println("Your choice: > ");
        int choice = scanner.nextInt();

        System.out.print("Enter matrix size: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println("Enter matrix:");
        Matrix a = MatrixTools.readMatrix(n, m, scanner);

        System.out.println("The result is:");
        switch (choice) {
            case 1: {
                MatrixTools.printMatrix(calculator.transposeMain(a));
                break;
            }
            case 2: {
                MatrixTools.printMatrix(calculator.transposeSide(a));
                break;
            }
            case 3: {
                MatrixTools.printMatrix(calculator.transposeVertical(a));
                break;
            }
            case 4: {
                MatrixTools.printMatrix(calculator.transposeHorizontal(a));
                break;
            }
        }
    }

    private static void findDeterminant(Scanner scanner, MatrixCalculator calculator) {
        System.out.print("Enter size of matrix: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println("Enter matrix:");
        Matrix a = MatrixTools.readMatrix(n, m, scanner);

        System.out.println("The result is:");
        System.out.println(calculator.findDeterminant(a));
    }

    private static void inverseMatrix(Scanner scanner, MatrixCalculator calculator) {
        System.out.print("Enter size of matrix: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        System.out.println("Enter matrix:");
        Matrix a = MatrixTools.readMatrix(n, m, scanner);
        Matrix inverseMatrix = calculator.inverseMatrix(a);
        if (inverseMatrix == null) {
            System.out.println("This matrix doesn't have an inverse.");
        } else {
            System.out.println("The result is:");
            MatrixTools.printMatrix(inverseMatrix);
        }
    }
}
