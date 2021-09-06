package Socket.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true){
            Socket socket =serverSocket.accept();
            MyDowmload dowmload = new MyDowmload(socket);
            Thread thread =new Thread(dowmload);
            thread.start();
        }
    }
}
