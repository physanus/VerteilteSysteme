package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api.clientserver;

import java.io.*;
import java.net.Socket;

public class ClientServerUtil {

    public static BufferedReader getInputStream(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static ObjectInputStream getObjectInputStream(Socket socket) throws IOException {
        return new ObjectInputStream(socket.getInputStream());
    }



    public static DataOutputStream getOutputStream(Socket socket) throws IOException {
        return new DataOutputStream(socket.getOutputStream());
    }

    public static ObjectOutputStream getObjectOutputStream(Socket socket) throws IOException {
        return new ObjectOutputStream(socket.getOutputStream());
    }



    public static String receiveLine(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.readLine();
    }

    public static void sendLine(DataOutputStream dataOutputStream, String line) throws IOException {
        dataOutputStream.writeBytes(line + "\n");
    }



    public static <T> T receiveObject(ObjectInputStream objectInputStream, Class<T> clazz) throws IOException, ClassNotFoundException {
        return clazz.cast(objectInputStream.readObject());
    }

    public static <T> void sendObject(ObjectOutputStream objectOutputStream, T obj) throws IOException {
        objectOutputStream.writeObject(obj);
    }

}
