package unit11;

import unit10.Recur;
import java.util.ArrayList;

public class EulerFriday {

    public static void one() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        System.out.println(sum);
    }

    public static void two() {
        int a = 1;
        int b = 1;
        int c = 0;
        int sum = 0;
        for (; b < 4000000;) {
            if (b % 2 == 0) {
                sum += b;
            }
            c = a + b;
            a = b;
            b = c;
        }
        System.out.println(sum);
    }

    public static void three() {
        long a = 600851475143l;
        long out = 0l;
        for (long i = 2l; i < a; i++) {
            for (; a % i == 0;) {
                a /= i;
                out = a;
            }
        }
        System.out.println(out);
    }

    public static void four() {
        int out = 0;
        for (int i = 100; i <= 999; i++) {
            for (int k = 100; k <= i; k++) {
                if (Recur.pot("" + i * k) && i * k > out) {
                    out = i * k;
                }
            }
        }
        System.out.println(out);
    }

    /*
     * finds and outprints the lcm of all the numbers from 1 to n inclusive
     * typing out 2*2*2*2*3*3*5*7*11*13*17*19 seemed like cheating
     */
    public static void five(int n) {
        if (n == 1) {
            System.out.println("1");
            return;
        }
        if (n == 2) {
            System.out.println("2");
            return;
        }
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        primes.add(3);
        for (int i = 3; i <= n; i += 2) {
            for (int k = 3; k < i; k++) {
                if (i % k == 0) {
                    break;
                }
                if (k == i - 1) {
                    primes.add(i);
                }
            }
        }
        long out = 1l;
        for (int prime : primes) {
            int k = 1;
            for (; k <= n; k *= prime) {
            }
            out *= k / prime;
        }
        System.out.println(out);
    }

    public static void six() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = i + 1;
        }
        long sumOfSquares = 0l;
        long squareOfSum = 0l;
        for (int i : nums) {
            sumOfSquares += i * i;
            squareOfSum += i;
        }
        squareOfSum *= squareOfSum;
        System.out.println(squareOfSum - sumOfSquares);
    }

    public static void seven() {
        // similar code from five()
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        primes.add(3);
        for (int i = 3; primes.size() < 10001; i += 2) {
            for (int k = 3; k < i; k++) {
                if (i % k == 0) {
                    break;
                }
                if (k == i - 1) {
                    primes.add(i);
                }
            }
        }
        System.out.println(primes.get(10000));
    }

    public static void eight() { // this is wrong, but I am not seeing it.
        // thanks for the code!
        int[] nums = eightScaffold();
        long out = 0l;
        long test = 1l;
        for (int k = 0; k < nums.length - 13; k++) {
            test = 1l;
            for (int i = k; i < k + 13; i++) {
                test *= nums[i];
            }
            if (test > out) {
                out = test;
            }
        }
        System.out.println(out);
    }

    public static int[] eightScaffold() {
        // woo java 13 supports multi-line strings!
        String lines = """
                73167176531330624919225119674426574742355349194934
                96983520312774506326239578318016984801869478851843
                85861560789112949495459501737958331952853208805511
                12540698747158523863050715693290963295227443043557
                66896648950445244523161731856403098711121722383113
                62229893423380308135336276614282806444486645238749
                30358907296290491560440772390713810515859307960866
                70172427121883998797908792274921901699720888093776
                65727333001053367881220235421809751254540594752243
                52584907711670556013604839586446706324415722155397
                53697817977846174064955149290862569321978468622482
                83972241375657056057490261407972968652414535100474
                82166370484403199890008895243450658541227588666881
                16427171479924442928230863465674813919123162824586
                17866458359124566529476545682848912883142607690042
                24219022671055626321111109370544217506941658960408
                07198403850962455444362981230987879927244284909188
                84580156166097919133875499200524063689912560717606
                05886116467109405077541002256983155200055935729725
                71636269561882670428252483600823257530420752963450""";
        // remove all whitespace
        lines = lines.replaceAll("\\s", "");
        // split into digits and parse into int array
        String[] digitArr = lines.trim().split("");
        int[] nums = new int[digitArr.length];
        for (int i = 0; i < digitArr.length; i++) {
            nums[i] = Integer.parseInt(digitArr[i]);
        }
        return nums;
    }

    public static void main(String[] args) {
        eight();

    }
}
