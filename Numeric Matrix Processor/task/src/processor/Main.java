package processor;

import java.util.Scanner;

public class Main {
    private static final int ROW = 0;
    private static final int COLUMN = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            switch (getUserChoice(scanner)) {
                case 1:
                    printResult(sum(readMatrix(scanner, "first"),
                            readMatrix(scanner, "second")));
                    break;
                case 2:
                    printResult(multiply(readMatrix(scanner, ""),
                            readConstant(scanner)));
                    break;
                case 3:
                    printResult(multiply(readMatrix(scanner, "first"),
                            readMatrix(scanner, "second")));
                    break;
                case 4:
                    runTransposeMenu(scanner);
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    public static double[][] readMatrix(Scanner scanner, int[] size) {
        double[][] matrix = new double[size[ROW]][size[COLUMN]];
        for (int i = 0; i < size[ROW]; i++) {
            for (int j = 0; j < size[COLUMN]; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    public static double[][] readMatrix(Scanner scanner, int[] size, String sequenceNumber) {
        System.out.println(String.format("Enter%s%s matrix:", sequenceNumber.isEmpty() ? "" : " ", sequenceNumber));
        double[][] matrix = new double[size[ROW]][size[COLUMN]];
        for (int i = 0; i < size[ROW]; i++) {
            for (int j = 0; j < size[COLUMN]; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    public static double[][] sum(double[][] matrixA, double[][] matrixB) {
        if (!areValidMatricesForSum(matrixA, matrixB)) {
            return null;
        }
        double[][] matrix = new double[matrixA.length][matrixA[ROW].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[ROW].length; j++) {
                matrix[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return matrix;
    }

    public static void print(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[ROW].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean areValidMatricesForSum(double[][] matrixA, double[][] matrixB) {
        return matrixA.length == matrixB.length
                && matrixA[ROW].length == matrixB[ROW].length;
    }

    public static boolean areValidMatricesForMultiplication(double[][] matrixA, double[][] matrixB) {
        return matrixA[ROW].length == matrixB.length;
    }

    public static void sumMatrixTest(Scanner scanner) {
        double[][] matrixA = readMatrix(scanner, getMatrixSize(scanner));
        double[][] matrixB = readMatrix(scanner, getMatrixSize(scanner));
        if (areValidMatricesForSum(matrixA, matrixB)) {
            print(sum(matrixA, matrixB));
        } else {
            System.out.println("ERROR");
        }
    }

    public static double[][] multiply(double[][] matrixA, double constant) {
        double[][] matrix = new double[matrixA.length][matrixA[ROW].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[ROW].length; j++) {
                matrix[i][j] = constant * matrixA[i][j];
            }
        }
        return matrix;
    }

    public static double[][] multiply(double[][] matrixA, double[][] matrixB) {
        if (!areValidMatricesForMultiplication(matrixA, matrixB)) {
            return null;
        }
        double[][] matrix = new double[matrixA.length][matrixB[ROW].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[ROW].length; j++) {
                matrix[i][j] = multiply(matrixA[i], getColumn(matrixB, j));
            }
        }
        return matrix;
    }

    public static double[] getColumn(double[][] matrix, int columnNumber) {
        double[] row = new double[matrix.length];
        for (int i = 0; i < row.length; i++) {
            row[i] = matrix[i][columnNumber];
        }
        return row;
    }

    public static double multiply(double[] row, double[] column) {
        double cell = 0;
        for (int i = 0; i < row.length; i++) {
            cell += row[i] * column[i];
        }
        return cell;
    }

    public static void multiplyConstantTest(Scanner scanner) {
        double[][] matrixA = readMatrix(scanner, getMatrixSize(scanner));
        double constant = scanner.nextDouble();
        print(multiply(matrixA, constant));
    }

    public static void printMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("0. Exit");
    }

    public static int getUserChoice(Scanner scanner) {
        System.out.print("Your choice: ");
        return scanner.nextInt();
    }

    public static int[] enterMatrixSize(Scanner scanner, String sequenceNumber) {
        System.out.print(String.format("Enter size of%s%s matrix: ", sequenceNumber.isEmpty() ? "" : " ", sequenceNumber));
        return getMatrixSize(scanner);
    }

    public static int[] getMatrixSize(Scanner scanner) {
        return new int[] {scanner.nextInt(), scanner.nextInt()};
    }

    public static void printResult(double[][] matrix) {
        if (matrix == null) {
            System.out.println("The operation cannot be performed.");
            return;
        }
        System.out.println("The result is:");
        print(matrix);
        System.out.println();
    }

    public static double[][] readMatrix(Scanner scanner, String sequenceNumber) {
        return readMatrix(scanner, enterMatrixSize(scanner, sequenceNumber), sequenceNumber);
    }

    public static double readConstant(Scanner scanner) {
        System.out.print("Enter constant: ");
        return scanner.nextDouble();
    }

    public static void printTransposeMenu() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
    }

    public static void runTransposeMenu(Scanner scanner) {
        printTransposeMenu();
        switch (getUserChoice(scanner)) {
            case 1:
                printResult(transposeMain(readMatrix(scanner, "")));
                break;
            case 2:
                printResult(transposeSide(readMatrix(scanner, "")));
                break;
            case 3:
                printResult(transposeVertical(readMatrix(scanner, "")));
                break;
            case 4:
                printResult(transposeHorizontal(readMatrix(scanner, "")));
                break;
        }
    }

    public static double[][] transposeMain(double[][] matrix) {
        double[][] result = new double[matrix[ROW].length][matrix.length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[ROW].length; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }

    public static double[][] transposeSide(double[][] matrix) {
        double[][] result = new double[matrix[ROW].length][matrix.length];
        return result;
    }

    public static double[][] transposeVertical(double[][] matrix) {
        double[][] result = new double[matrix[ROW].length][matrix.length];
        return result;
    }

    public static double[][] transposeHorizontal(double[][] matrix) {
        double[][] result = new double[matrix[ROW].length][matrix.length];
        return result;
    }
}
