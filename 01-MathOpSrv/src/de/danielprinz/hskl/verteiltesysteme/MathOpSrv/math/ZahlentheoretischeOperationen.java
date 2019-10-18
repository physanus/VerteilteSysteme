package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.math;

import java.math.BigInteger;

public class ZahlentheoretischeOperationen {


    /**
     * Calculates the greatest common divisor (gcd)
     * @param a The first integer
     * @param b The second integer
     * @return The gcd
     */
    public static int ggt(int a, int b) {
        BigInteger b1 = BigInteger.valueOf(a);
        BigInteger b2 = BigInteger.valueOf(b);
        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }


    /**
     * Checks whether an integer is prime
     * @param a The integer to be checked
     * @return true if the integer is prime, false otherwise
     */
    public static boolean isprime(int a) {
        if(a <= 1) return false;

        for(int i = 2; i < a; i++) {
            if(a % i == 0)
                return false;
        }

        return true;
    }

}
