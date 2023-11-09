import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть завдання: ");
        int task = scanner.nextInt();
        if (task == 1) {
            System.out.print("Введіть значення x для першої точки: ");
            double x1 = scanner.nextDouble();
            System.out.print("Введіть значення y для першої точки: ");
            double y1 = scanner.nextDouble();

            System.out.print("Введіть значення x для другої точки: ");
            double x2 = scanner.nextDouble();
            System.out.print("Введіть значення y для другої точки: ");
            double y2 = scanner.nextDouble();

            First firstDot = new First(x1, y1);
            First secondDot = new First(x2, y2);

            System.out.printf("Відстань до початку координат для першої точки: %.2f%n", firstDot.toZero());
            System.out.printf("Відстань між двома точками: %.2f%n", firstDot.toFirst(secondDot));

            First.Polar polarCoordinates = firstDot.toPolar();
            System.out.printf("Полярні координати для першої точки (r, a): %.2f, %.2f%n", polarCoordinates.getR(), polarCoordinates.getA());

            System.out.println("Точки рівні? " + firstDot.equals(secondDot));
            System.out.println("Точки не рівні? " + !firstDot.equals(secondDot));

            scanner.close();
        }
        else if(task==2){
            System.out.println("Введіть значення для матриці 2x2:");
            System.out.print("a: ");
            double a = scanner.nextDouble();
            System.out.print("b: ");
            double b = scanner.nextDouble();
            System.out.print("c: ");
            double c = scanner.nextDouble();
            System.out.print("d: ");
            double d = scanner.nextDouble();

            Matrix2x2 matrixA = new Matrix2x2(a, b, c, d);

            System.out.println("Введена матриця:");
            matrixA.printMatrix();

            System.out.println("Детермінант матриці: " + matrixA.determinant());

            System.out.println("Обернена матриця:");
            Matrix2x2 inverseA = matrixA.inverse();
            if (inverseA != null) {
                inverseA.printMatrix();
            }

            System.out.println("Множення матриці на саму себе:");
            Matrix2x2 squareA = matrixA.multiply(matrixA);
            squareA.printMatrix();

            System.out.println("Множення матриці на скаляр (коефіцієнт 2):");
            Matrix2x2 scalarProductA = matrixA.multiplyByScalar(2);
            scalarProductA.printMatrix();

            scanner.close();
        }
    }
    public static class First {
        private double x;
        private double y;

        public First(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public void moveX(double newX) {
            this.x += newX;
        }

        public void moveY(double newY) {
            this.y += newY;
        }

        public double toZero() {
            return Math.sqrt(x * x + y * y);
        }

        public double toFirst(First second) {
            double newX = this.x - second.getX();
            double newY = this.y - second.getY();
            return Math.sqrt(newX * newX + newY * newY);
        }

        public Polar toPolar() {
            double r = Math.sqrt(x * x + y * y);
            double a = Math.atan2(y, x);
            return new Polar(r, a);
        }

        public boolean equals(First second) {
            return this.x == second.getX() && this.y == second.getY();
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        static class Polar {
            private double r;
            private double a;

            public Polar(double r, double a) {
                this.r = r;
                this.a = a;
            }

            public double getR() {
                return r;
            }

            public double getA() {
                return a;
            }
        }
    }


    public static class Matrix2x2 {
        private double[][] matrix;

        public Matrix2x2(double a, double b, double c, double d) {
            matrix = new double[][]{{a, b}, {c, d}};
        }

        public double determinant() {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        public Matrix2x2 inverse() {
            double det = determinant();
            if (det != 0) {
                double invDet = 1.0 / det;
                return new Matrix2x2(matrix[1][1] * invDet, -matrix[0][1] * invDet, -matrix[1][0] * invDet, matrix[0][0] * invDet);
            } else {
                System.out.println("Матриця не має оберненої матриці, оскільки детермінант дорівнює нулю.");
                return null;
            }
        }

        public Matrix2x2 multiply(Matrix2x2 otherMatrix) {
            double a = matrix[0][0] * otherMatrix.matrix[0][0] + matrix[0][1] * otherMatrix.matrix[1][0];
            double b = matrix[0][0] * otherMatrix.matrix[0][1] + matrix[0][1] * otherMatrix.matrix[1][1];
            double c = matrix[1][0] * otherMatrix.matrix[0][0] + matrix[1][1] * otherMatrix.matrix[1][0];
            double d = matrix[1][0] * otherMatrix.matrix[0][1] + matrix[1][1] * otherMatrix.matrix[1][1];

            return new Matrix2x2(a, b, c, d);
        }

        public Matrix2x2 add(Matrix2x2 otherMatrix) {
            double a = matrix[0][0] + otherMatrix.matrix[0][0];
            double b = matrix[0][1] + otherMatrix.matrix[0][1];
            double c = matrix[1][0] + otherMatrix.matrix[1][0];
            double d = matrix[1][1] + otherMatrix.matrix[1][1];

            return new Matrix2x2(a, b, c, d);
        }

        public Matrix2x2 multiplyByScalar(double scalar) {
            double a = matrix[0][0] * scalar;
            double b = matrix[0][1] * scalar;
            double c = matrix[1][0] * scalar;
            double d = matrix[1][1] * scalar;

            return new Matrix2x2(a, b, c, d);
        }

        public void printMatrix() {
            System.out.println("| " + matrix[0][0] + "  " + matrix[0][1] + " |");
            System.out.println("| " + matrix[1][0] + "  " + matrix[1][1] + " |");
        }

    }

}
