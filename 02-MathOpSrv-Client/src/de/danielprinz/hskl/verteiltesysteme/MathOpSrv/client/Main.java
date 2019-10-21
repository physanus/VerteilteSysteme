package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.client;

import de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.clientserver.ClientManager;

import java.io.IOException;

public class Main {

    private static final String HOSTNAME = "127.0.0.1";
    private static final int PORT = 7331;


    public static void main(String[] args) {

        ClientManager clientManager = new ClientManager(HOSTNAME, PORT);

        while(true) {
            try {

                System.out.println("Starting client...");
                System.out.println("Connecting to the server " + HOSTNAME + ":" + PORT + "... ");

                clientManager.connect();

                System.out.println("Successfully connected to the server");
                System.out.println();


                clientManager.sendLine("Hello from Client", System.out);
                clientManager.recvLine(System.out);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {
                System.err.println("Client could not connect to the server. Retying..."); // TODO actually retry
                e.printStackTrace();
            } finally {
                try {
                    clientManager.destroy();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
