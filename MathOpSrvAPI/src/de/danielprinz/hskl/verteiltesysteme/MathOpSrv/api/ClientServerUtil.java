package de.danielprinz.hskl.verteiltesysteme.MathOpSrv.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientServerUtil {

    public static BufferedReader getInputStream(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static DataOutputStream getOutputStream(Socket socket) throws IOException {
        return new DataOutputStream(socket.getOutputStream());
    }


    public static String receiveLine(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.readLine();
    }

    public static void sendLine(DataOutputStream dataOutputStream, String line) throws IOException {
        dataOutputStream.writeBytes(line + "\n");
    }

}
