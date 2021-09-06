import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.*;

public class Image2ByteTest {
    public static void main(String[] args) {
        String path ="";
        byte[] bytes =image2byte(path);
        String path2 ="";
        byte2image(bytes,path2);
    }

    private static void byte2image(byte[] bytes, String path) {

        if (bytes.length<3||path.equals("")){
            return;
        }
        try {
            FileImageOutputStream imageOutputStream = new FileImageOutputStream(new File(path));
            imageOutputStream.write(bytes,0,bytes.length);
            imageOutputStream.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
    }

    private static byte[] image2byte(String path) {
        byte[] data=null;
        FileImageInputStream inputStream =null;
        try {
            inputStream = new FileImageInputStream(new File(path));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int numBytesRead =0;
            while ((numBytesRead =inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,numBytesRead);
            }
            data =outputStream.toByteArray();
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String byte2string(byte[] data) {
        if (data==null||data.length<=1){
            return "0x";

        }
        if (data.length>20000){
            return "0x";
        }
        StringBuffer stringBuffer =new StringBuffer();
        int buf[]= new int[data.length];
        for (int i = 0; i < data.length; i++) {
            buf[i] =data[i]<0?(data[i]+256):(data[i]);
        }
        for (int i = 0; i < buf.length; i++) {
            if (buf[i]<16){
                //toHexString()此方法返回的字符串表示的无符号整数参数所表示的值以十六进制（基数为16）。
                stringBuffer.append("0"+Integer.toHexString(buf[i]));
            }
        }
        return stringBuffer.toString();
    }

}
