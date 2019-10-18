package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.math;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathUtil {

    private static final Pattern MATH_ADD_REGEX = Pattern.compile("\\+\\((-?[0-9]+),(-?[0-9]+)\\)");
    private static final Pattern MATH_SUB_REGEX = Pattern.compile("-\\((-?[0-9]+),(-?[0-9]+)\\)");
    private static final Pattern MATH_MUL_REGEX = Pattern.compile("\\*\\((-?[0-9]+),(-?[0-9]+)\\)");
    private static final Pattern MATH_GGT_REGEX = Pattern.compile("ggt\\((-?[0-9]+),(-?[0-9]+)\\)");
    private static final Pattern MATH_PRIME_REGEX = Pattern.compile("isprime\\((-?[0-9]+)\\)");


    /**
     * Parses and calculates an equation
     * @param equation The equation
     * @return The {@link MathResult}
     * @throws MathException Parse error or Argument count / type mismatch
     */
    public static MathResult calculateEquation(String equation) throws MathException {
        equation = equation.replaceAll("\\s", "").toLowerCase();

        Matcher matcher;
        if((matcher = MATH_ADD_REGEX.matcher(equation)).find()) {
            ArrayList<Integer> arguments = getArguments(matcher, equation, 2);
            return new MathResult(ArithmetischeOperationen.add(arguments.get(0), arguments.get(1)));

        } else if((matcher = MATH_SUB_REGEX.matcher(equation)).find()) {
            ArrayList<Integer> arguments = getArguments(matcher, equation, 2);
            return new MathResult(ArithmetischeOperationen.sub(arguments.get(0), arguments.get(1)));

        } else if((matcher = MATH_MUL_REGEX.matcher(equation)).find()) {
            ArrayList<Integer> arguments = getArguments(matcher, equation, 2);
            return new MathResult(ArithmetischeOperationen.mul(arguments.get(0), arguments.get(1)));

        } else if((matcher = MATH_GGT_REGEX.matcher(equation)).find()) {
            ArrayList<Integer> arguments = getArguments(matcher, equation, 2);
            return new MathResult(ZahlentheoretischeOperationen.ggt(arguments.get(0), arguments.get(1)));

        } else if((matcher = MATH_PRIME_REGEX.matcher(equation)).find()) {
            ArrayList<Integer> arguments = getArguments(matcher, equation, 1);
            return new MathResult(ZahlentheoretischeOperationen.isprime(arguments.get(0)));

        }


        throw new MathException("Couldn't parse equation: " + equation);
    }


    /**
     * Extracts the integer arguments from equation
     * @param matcher The matcher
     * @param equation The equation
     * @return ArrayList of arguments
     * @throws MathException Argument count / type mismatch
     */
    private static ArrayList<Integer> getArguments(Matcher matcher, String equation, int expectedArgumentCount) throws MathException {
        ArrayList<Integer> result = new ArrayList<>();

        if(matcher.groupCount() != expectedArgumentCount)
            throw new MathException("Argument count does not match: " + matcher.groupCount() + " (expected " + expectedArgumentCount + ") in " + equation);

        for(int i = 1; i <= matcher.groupCount(); i++) {
            try {
                result.add(Integer.valueOf(matcher.group(i)));
            } catch (NumberFormatException e) {
                // should never happen due to regex check
                throw new MathException("Not an int: " + matcher.group(i) + " in " + equation);
            }
        }

        return result;
    }


}
