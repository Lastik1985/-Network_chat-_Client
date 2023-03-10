package org.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        String host = parseSettings("host");
        int port = Integer.parseInt(parseSettings("port"));
        Logger.log("получили port - " + port + " " + "host - " + host);

        Socket socket = new Socket(host, port);
        Client client = new Client(socket);
        client.start();

    }


    public static String parseSettings(String str) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader("settings.json"));
            jsonObject = (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return (String) jsonObject.get(str);
    }
}

