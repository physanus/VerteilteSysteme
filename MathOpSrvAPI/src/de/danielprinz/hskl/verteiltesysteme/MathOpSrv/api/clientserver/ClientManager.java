package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.clientserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ClientManager {

    private final String hostname;
    private final int port;

    private Socket socket;
    private BufferedReader bufferedReader;
    private DataOutputStream dataOutputStream;


    public ClientManager(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }


    /**
     * Connects and initializes the socket
     * @throws IOException on failure
     */
    public void connect() throws IOException {
        if(socket != null && !socket.isClosed()) {
            socket.close();
        }

        this.socket = new Socket(this.hostname, this.port);
        this.bufferedReader = ClientServerUtil.getInputStream(socket);
        this.dataOutputStream = ClientServerUtil.getOutputStream(socket);
    }


    /**
     * Destroys the current connection
     * @throws IOException on failure
     */
    public void destroy() throws IOException {
        if(socket != null && !socket.isClosed()) {
            socket.close();
            socket = null;
        }
    }



    /**
     * Waits for the transmission of a new line and returns the string
     * @return The transmitted string
     * @throws IOException on failure
     */
    public String recvLine() throws IOException {
        if(socket == null)
            throw new IllegalStateException("Socket not set up");
        if(socket.isClosed())
            throw new IllegalStateException("Socket has already been closed");
        if(bufferedReader == null)
            throw new IllegalStateException("BufferedReader not set up");

        return ClientServerUtil.receiveLine(this.bufferedReader);
    }


    /**
     * Waits for the transmission of a new line and prints and returns the string
     * @param printStream The PrintStream to be printed to
     * @return The transmitted string
     * @throws IOException on failure
     */
    public String recvLine(PrintStream printStream) throws IOException {
        String line = recvLine();
        printStream.println("Server> " + line);
        return line;
    }


    /**
     * Sends a line though the socket
     * @param line The string to be transmitted
     * @throws IOException on failure
     */
    public void sendLine(String line) throws IOException {
        if(socket == null)
            throw new IllegalStateException("Socket not set up");
        if(socket.isClosed())
            throw new IllegalStateException("Socket has already been closed");
        if(dataOutputStream == null)
            throw new IllegalStateException("DataOutputStream not set up");

        ClientServerUtil.sendLine(this.dataOutputStream, line);
    }


    /**
     * Sends a line though the socket and prints it to the PrintStream
     * @param printStream The PrintStream to be printed to
     * @return The string to be transmitted
     * @throws IOException on failure
     */
    public String sendLine(String line, PrintStream printStream) throws IOException {
        sendLine(line);
        printStream.println("> " + line);
        return line;
    }

}
