package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math;

public class ArithmetischeOperationen {


    /**
     * Adds two integers
     * @param a The first summand
     * @param b The second summand
     * @return The sum
     * @throws MathException on failure
     */
    public static int add(int a, int b) {
        return Math.addExact(a, b);
    }


    /**
     * Subtracts two integers
     * @param a The minuend
     * @param b The subtrahend
     * @return The difference
     * @throws MathException on failure
     */
    public static int sub(int a, int b)  {
        return Math.subtractExact(a, b);
    }


    /**
     * Multiplies two integers
     * @param a The multiplier
     * @param b The multiplicand
     * @return The product
     * @throws MathException on failure
     */
    public static int mul(int a, int b) {
        return Math.multiplyExact(a, b);
    }


}
