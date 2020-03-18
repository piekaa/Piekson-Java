package pl.piekoszek.tcp.client;

import pl.piekoszek.storage.mongo.Mongo;
import pl.piekoszek.tcp.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class TcpClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 27017);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        Connection connection = new Connection(inputStream, outputStream);
        Mongo mongo = new Mongo(connection);


        Dto toInsert = new Dto();
        toInsert._id = "dupa kosciotrupa";
        toInsert._class = "pierwsza klasa";
        toInsert.name = "marian";
        toInsert.password = "qwer";
        mongo.insert("piekoszek", "backend", toInsert);

        List<Dto> accounts = mongo.queryAll("{\"password\":  \"qwer\"}", "piekoszek", "backend", Dto.class);

        for (Dto account : accounts) {
            System.out.println(account);
        }

    }
}
