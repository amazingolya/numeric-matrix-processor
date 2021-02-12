import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        if (n == 0) {
            return BigInteger.ONE;
        }
        BigInteger doubleFactorial = BigInteger.valueOf(n);
        int end = n % 2 == 0 ? 2 : 1;
        for (int i = n - 2; i >= end; i -= 2) {
            doubleFactorial = doubleFactorial.multiply(BigInteger.valueOf(i));
        }
        return doubleFactorial;
    }
}