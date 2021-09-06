package Socket.Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MyDowmload implements Runnable{
    private Socket socket;
    public MyDowmload(Socket socket) {
        this.socket =socket;
    }

    @Override
    public void run() {
        System.out.println("连接成功");
        try {
            OutputStream outputStream = socket.getOutputStream();
            File file = new File("");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1000];
            int len =-1;
            while ((len =fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }
            fileInputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
