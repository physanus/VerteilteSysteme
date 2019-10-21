package de.danielprinz.hskl.verteiltesysteme.MathOpSrv;

import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException;
import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathUtil;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // run forever until user inputs '!q'
        while(true) {

            System.out.print("Enter an equation, !q or !help: \n> ");
            String input = scanner.nextLine();


            if(input.equalsIgnoreCase("!q")) {
                // quit the application
                break;
            }

            if(input.equalsIgnoreCase("!help")) {
                // display help

                String helpMessage = "\n-=-=-= HELP =-=-=-\n" +
                        "!q \t\t\t - Quit\n" +
                        "+(a, b) \t- Add two integers\n" +
                        "-(a, b) \t- Subtract two integers\n" +
                        "*(a, b) \t- Multiply two integers\n" +
                        "ggt(a, b) \t- Find the greatest common divisor of two integers\n" +
                        "isprime(a) \t- Check whether an integer is prime or not\n\n";

                System.out.println(helpMessage);
                continue;
            }


            // calculate result
            try {
                System.out.println(MathUtil.calculateEquation(input).getResult());
                System.out.println();
            } catch (MathException e) {
                e.printStackTrace();
                System.out.println("Fehler - Bitte versuchen Sie es erneut!");
            }

        }


    }


}
