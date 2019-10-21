package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.clientserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager {

    private final int port;
    private ServerSocket serverSocket;

    private Socket socket;
    private BufferedReader bufferedReader;
    private ObjectInputStream objectInputStream;
    private DataOutputStream dataOutputStream;
    private ObjectOutputStream objectOutputStream;


    public ServerManager(int port) {
        this.port = port;
    }


    /**
     * Opens socket to the world
     * @throws IOException on failure
     */
    public void spawn() throws IOException {
        this.serverSocket = new ServerSocket(this.port);
    }


    /**
     * Waits for and initializes a new connection
     * @throws IOException on failure
     */
    public void awaitNewConnection() throws IOException {
        destroyConnection();

        this.socket = serverSocket.accept();

        this.dataOutputStream = ClientServerUtil.getOutputStream(socket);
        this.bufferedReader = ClientServerUtil.getInputStream(socket);
        this.objectOutputStream = ClientServerUtil.getObjectOutputStream(socket);
        this.objectInputStream = ClientServerUtil.getObjectInputStream(socket);
    }


    /**
     * Destroys the current connection
     * @throws IOException on failure
     */
    public void destroyConnection() throws IOException {
        if(socket != null && !socket.isClosed()) {
            socket.close();
            socket = null;
        }
    }


    /**
     * Destroys the current connection and the server
     * @throws IOException on failure
     */
    public void destroy() throws IOException {
        destroyConnection();
        if (serverSocket != null && !serverSocket.isClosed()){
            serverSocket.close();
            serverSocket = null;
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
        printStream.println("Client[@" + getConnectedIPAddress() + ":" + getConnectedPort() + "]> " + line);
        return line;
    }


    /**
     * Waits for the transmission of a new object and returns the object. May throw {@link ClassCastException}
     * @param clazz The class of the object
     * @return The casted object
     * @throws IOException on failure
     * @throws ClassNotFoundException on failure
     * @throws ClassCastException on failure
     */
    public <T> T recvObject(Class<T> clazz) throws IOException, ClassNotFoundException, ClassCastException {
        if(socket == null)
            throw new IllegalStateException("Socket not set up");
        if(socket.isClosed())
            throw new IllegalStateException("Socket has already been closed");
        if(objectInputStream == null)
            throw new IllegalStateException("ObjectInputStream not set up");

        return ClientServerUtil.receiveObject(this.objectInputStream, clazz);
    }


    /**
     * Waits for the transmission of a new object and prints and returns the object. May throw {@link ClassCastException}
     * @param clazz The class of the object
     * @param printStream The PrintStream to be printed to
     * @return The casted object
     * @throws IOException on failure
     * @throws ClassNotFoundException on failure
     * @throws ClassCastException on failure
     */
    public <T> T recvObject(Class<T> clazz, PrintStream printStream) throws IOException, ClassNotFoundException, ClassCastException {
        T object = recvObject(clazz);
        printStream.println("Client[@" + getConnectedIPAddress() + ":" + getConnectedPort() + "]> " + object);
        return object;
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
    public void sendLine(String line, PrintStream printStream) throws IOException {
        printStream.println("> " + line);
        sendLine(line);
    }


    /**
     * Sends an object through the socket
     * @param obj The object to be transmitted
     * @throws IOException on failure
     */
    public <T> void sendObject(T obj) throws IOException {
        if(socket == null)
            throw new IllegalStateException("Socket not set up");
        if(socket.isClosed())
            throw new IllegalStateException("Socket has already been closed");
        if(objectOutputStream == null)
            throw new IllegalStateException("DataOutputStream not set up");

        ClientServerUtil.sendObject(this.objectOutputStream, obj);
    }


    /**
     * Sends an object through the socket
     * @param obj The object to be transmitted
     * @throws IOException on failure
     */
    public <T> void sendObject(T obj, PrintStream printStream) throws IOException {
        printStream.println("> " + obj.toString());
        sendObject(obj);
    }


    /**
     * Get the remote IP address
     * @return The remote IP address
     */
    public String getConnectedIPAddress() {
        return this.socket.getInetAddress().getHostAddress();
    }


    /**
     * Get the remove port
     * @return The remote port
     */
    public int getConnectedPort() {
        return this.socket.getPort();
    }

}
