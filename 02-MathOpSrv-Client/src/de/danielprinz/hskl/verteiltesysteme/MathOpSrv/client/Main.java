package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.client;

import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.clientserver.ClientManager;
import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.logger.LoggerUtil;
import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException;
import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathResult;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final String HOSTNAME = "127.0.0.1";
    private static final int PORT = 7331;


    public static void main(String[] args) {

        LoggerUtil.setPrefix("A2-Client");
        final ClientManager clientManager = new ClientManager(HOSTNAME, PORT);

        System.out.println(2147483647 * 2);

        /*
        // only for testing

        try {
            MathResult mathResult = calculateEquation(clientManager, "Hello from Client");
            mathResult.printResult(System.out, s -> "Server> " + s);
        } catch (MathException e) { e.printStackTrace(); }


        try {
            MathResult mathResult = calculateEquation(clientManager, "+(3, 4)");
            mathResult.printResult(System.out, s -> "Server> " + s);
        } catch (MathException e) { e.printStackTrace(); }


        try {
            MathResult mathResult = calculateEquation(clientManager, "-(3, 4)");
            mathResult.printResult(System.out, s -> "Server> " + s);
        } catch (MathException e) { e.printStackTrace(); }


        try {
            MathResult mathResult = calculateEquation(clientManager, "*(3, 4)");
            mathResult.printResult(System.out, s -> "Server> " + s);
        } catch (MathException e) { e.printStackTrace(); }


        try {
            MathResult mathResult = calculateEquation(clientManager, "ggt(12, 18)");
            mathResult.printResult(System.out, s -> "Server> " + s);
        } catch (MathException e) { e.printStackTrace(); }


        try {
            MathResult mathResult = calculateEquation(clientManager, "isprime(651651665)");
            mathResult.printResult(System.out, s -> "Server> " + s);
        } catch (MathException e) { e.printStackTrace(); }

        */


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
                MathResult mathResult = calculateEquation(clientManager, input);
                mathResult.printResult(System.out, s -> "Server> " + s);
            } catch (MathException e) {
                e.printStackTrace();
                System.out.println("Fehler - Bitte versuchen Sie es erneut!");
            }

        }


    }


    /**
     * Builds a connection to the remote server and returns the result
     * @param clientManager The ClientManager
     * @param equation The equation to be calculated
     * @return The MathResult, containing either the calculated reult or the error
     */
    private static MathResult calculateEquation(ClientManager clientManager, String equation) {
        Exception exception = null;
        for(int i = 3; i > 0; i--) {
            try {
                clientManager.connect();
                clientManager.sendLine(equation, System.out);
                return clientManager.recvObject(MathResult.class);
            } catch (IOException | ClassNotFoundException e) {
                exception = e;
                //e.printStackTrace();
                //return new MathResult<>(MathResult.Status.ERROR, e.getMessage());
            } finally {
                try {
                    clientManager.destroy();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(i == 1) continue;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        exception.printStackTrace();
        return new MathResult<>(MathResult.Status.ERROR, exception.getMessage());
    }


}
