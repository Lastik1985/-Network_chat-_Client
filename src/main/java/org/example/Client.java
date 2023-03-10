package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() {

        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                Scanner scanner = new Scanner(System.in)) {

            System.out.println("Введите свой nickname:");
            String msg = scanner.nextLine();
            out.println(msg);
            Logger.log("nickname - " + msg);

            while (true) {
                msg = scanner.nextLine();
                out.println(msg);
                Logger.log(msg);
                if ("exit".equals(msg)) break;

                Thread readMsg = new Thread(new Runnable() {

                    public void run() {
                        while (!socket.isClosed()) {
                            try {
                                String msg = in.readLine();
                                if (msg == null) break;
                                System.out.println(msg);
                                Logger.log(msg);

                            } catch (IOException e) {
                                break;
                            }
                        }
                    }
                });
                readMsg.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}