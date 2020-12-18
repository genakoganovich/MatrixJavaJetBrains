package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        multiplyConstantTest(new Scanner(System.in));
    }

    public static int[][] readMatrix(Scanner scanner, int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    public static int[][] sum(int[][] matrixA, int[][] matrixB) {
        int[][] matrix = new int[matrixA.length][matrixA[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return matrix;
    }

    public static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean equals(int[][] matrixA, int[][] matrixB) {
        return matrixA.length == matrixB.length
                && matrixA[0].length == matrixB[0].length;
    }

    public static void sumMatrixTest(Scanner scanner) {
        int[][] matrixA = readMatrix(scanner, scanner.nextInt(), scanner.nextInt());
        int[][] matrixB = readMatrix(scanner, scanner.nextInt(), scanner.nextInt());
        if (equals(matrixA, matrixB)) {
            print(sum(matrixA, matrixB));
        } else {
            System.out.println("ERROR");
        }
    }

    public static int[][] multiply(int[][] matrixA, int constant) {
        int[][] matrix = new int[matrixA.length][matrixA[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = constant * matrixA[i][j];
            }
        }
        return matrix;
    }

    public static void multiplyConstantTest(Scanner scanner) {
        int[][] matrixA = readMatrix(scanner, scanner.nextInt(), scanner.nextInt());
        int constant = scanner.nextInt();
        print(multiply(matrixA, constant));
    }
}
