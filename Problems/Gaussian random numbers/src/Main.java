import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long k = scanner.nextLong();
        double n = scanner.nextDouble();
        double m = scanner.nextDouble();
        System.out.println(findSeed(k, n, m));
    }

    public static long findSeed(long k, double n, double m) {
        long seed = k;
        while (true) {
            if (isSeed(seed, n, m)) {
                return seed;
            } else {
                seed++;
            }
        }
    }

    private static boolean isSeed(long seed, double n, double m) {
        Random random = new Random(seed);
        for (int i = 0; i < n; i++) {
            if (random.nextGaussian() > m) {
                return false;
            }
        }
        return true;
    }
}