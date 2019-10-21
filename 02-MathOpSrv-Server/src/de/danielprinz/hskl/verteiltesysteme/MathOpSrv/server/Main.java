package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.server;

import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.clientserver.ServerManager;

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


            serverManager.awaitNewConnection();
            serverManager.recvLine(System.out);

            serverManager.sendLine("Hello from Server");














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
