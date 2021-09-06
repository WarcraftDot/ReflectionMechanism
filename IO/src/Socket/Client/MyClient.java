package Socket.Client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("", 9999);
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1000];
        int len =-1;
        OutputStream outputStream =new FileOutputStream("");
        while ((len =inputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,len);
        }
        System.out.println("成功");
    }
}
