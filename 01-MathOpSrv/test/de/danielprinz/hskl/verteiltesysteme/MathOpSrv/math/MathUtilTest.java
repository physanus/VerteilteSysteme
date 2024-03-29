package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.math;

import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException;
import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathResult;
import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathUtil;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class MathUtilTest {

    private HashMap<String, Object> testCases;

    @BeforeEach
    void setUp() {

        testCases = new HashMap<String, Object>() {{
            put("", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");

            put("+", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("+(1,3)", 4);
            put("+(1, 3)", 4);
            put("+(01, 03)", 4);
            put("+(1, A)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("+(A, 1)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("+(100, 1)", 101);
            put("+(1, 100)", 101);
            put("+(5)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("+()", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("+(-5, 3)", -2);
            put("+(5, -3)", 2);
            put("+(-5, -3)", -8);
            put("+(0, 0)", 0);
            put("+(2147483647, 5)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException"); // overlow

            put("-", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("-()", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("-(3,1)", 2);
            put("-(3, 1)", 2);
            put("-(03, 01)", 2);
            put("-(1, 3)", -2);
            put("-(-3, 1)", -4);
            put("-(3, -1)", 4);
            put("-(-3, -1)", -2);
            put("-(3)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("-(3, A)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("-(A, 3)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("-(A, A)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("-(1)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("-(0, 0)", 0);
            put("-(-5, 2147483647)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException"); // underflow

            put("*", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("*()", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("*(3,1)", 3);
            put("*(3, 1)", 3);
            put("*(03, 01)", 3);
            put("*(1, 3)", 3);
            put("*(3, 2)", 6);
            put("*(2, 3)", 6);
            put("*(3, 4)", 12);
            put("*(-3, 4)", -12);
            put("*(3, -4)", -12);
            put("*(-3, -4)", 12);
            put("*(0, -4)", 0);
            put("*(-3, 0)", 0);
            put("*(0, 0)", 0);
            put("*(0)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("*(A, 3)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("*(3, A)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("*(A, A)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("*(2147483647, 2)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException"); // overflow

            put("ggt", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("ggt()", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("ggt(12,18)", 6);
            put("ggt(12, 18)", 6);
            put("ggt(0012, 0018)", 6);
            put("ggt(18,12)", 6);
            put("ggt(-18, 12)", 6);
            put("ggt(18, -12)", 6);
            put("ggt(-18, -12)", 6);
            put("ggt(3528, 3780)", 252);
            put("ggt(A, 3780)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("ggt(3528, A)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("ggt(A, A)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("ggt(12)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");

            put("isprime", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("isprime()", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("isprime(5)", true);
            put("isprime(005)", true);
            put("isprime(A)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");
            put("isprime(5, 5)", "de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException");

        }};


        // check the first 100 numbers
        ArrayList<Integer> primeNumbers = new ArrayList<Integer>() {{
           add(2);
           add(3);
           add(5);
           add(7);
           add(11);
           add(13);
           add(17);
           add(19);
           add(23);
           add(29);
           add(31);
           add(37);
           add(41);
           add(43);
           add(47);
           add(53);
           add(59);
           add(61);
           add(67);
           add(71);
           add(73);
           add(79);
           add(83);
           add(89);
           add(97);
        }};

        for(int i = -5; i <= 100; i++) {
            if(primeNumbers.contains(i)) {
                testCases.put("isprime(" + i + ")", true);
            } else {
                testCases.put("isprime(" + i + ")", false);
            }
        }

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void calculateEquation() {


        for(Map.Entry<String, Object> e : testCases.entrySet()) {
            String equation = e.getKey();
            Object result = e.getValue();

            if(result instanceof Integer) {

                try {
                    MathResult mathResult = MathUtil.calculateEquation(equation);
                    Assert.assertEquals(result, mathResult.getResult());
                } catch (MathException ex) {
                    ex.printStackTrace();
                    Assert.fail(equation);
                }

            } else if (result instanceof Boolean) {

                try {
                    MathResult mathResult = MathUtil.calculateEquation(equation);
                    Assert.assertEquals(result, mathResult.getResult());
                } catch (MathException ex) {
                    ex.printStackTrace();
                    Assert.fail(equation);
                }

            } else {

                // error expected
                try {
                    Class<Throwable> exceptionClass = (Class<Throwable>) Class.forName((String) result);
                    Assertions.assertThrows(exceptionClass, () -> MathUtil.calculateEquation(equation).getResult());
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    Assert.fail("Class not found: " + result);
                }

            }

        }


    }
}