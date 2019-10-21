package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.server;

import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.clientserver.ServerManager;
import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathException;
import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathResult;
import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.math.MathUtil;

import java.io.IOException;

public class Main {

    public static final int SERVER_PORT = 7331;


    public static void main(String[] args) {

        ServerManager serverManager = new ServerManager(SERVER_PORT);


        try {

            System.out.println("Starting server...");

            serverManager.spawn();

            System.out.println("Server is running on port '" + SERVER_PORT + "'");
            System.out.println();



            while(true) {

                try {

                    serverManager.awaitNewConnection();
                    String line = serverManager.recvLine(System.out);

                    try {
                        MathResult mathResult = MathUtil.calculateEquation(line);
                        serverManager.sendObject(mathResult, System.out);
                    } catch (MathException e) {
                        serverManager.sendObject(new MathResult<>(MathResult.Status.ERROR, "Couldn't parse equation: " + line), System.out);
                        e.printStackTrace();
                    }

                    //serverManager.sendLine("Hello from Server", System.out);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        serverManager.destroyConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }











        } catch (IOException e) {
            System.err.println("Server could not be started. Shutting down...");
            e.printStackTrace();

            try {
                serverManager.destroy();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                serverManager.destroy();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
