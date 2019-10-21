package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.client;

import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.clientserver.ClientManager;
import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException;
import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathResult;

import java.io.IOException;

public class Main {

    private static final String HOSTNAME = "127.0.0.1";
    private static final int PORT = 7331;


    public static void main(String[] args) {

        final ClientManager clientManager = new ClientManager(HOSTNAME, PORT);

        try {
            MathResult mathResult = calculateEquation(clientManager, "Hello from Client");
            mathResult.printResult(System.out);
        } catch (MathException e) {
            e.printStackTrace();
        }


        try {
            MathResult mathResult = calculateEquation(clientManager, "+(3, 4)");
            mathResult.printResult(System.out);
        } catch (MathException e) {
            e.printStackTrace();
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

                System.out.println("Connecting to the server " + HOSTNAME + ":" + PORT + "... ");
                clientManager.connect();
                System.out.println("Successfully connected to the server");

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
