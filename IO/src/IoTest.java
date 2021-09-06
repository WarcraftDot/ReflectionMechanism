import java.io.*;

public class IoTest {
    public static void main(String[] args) {
        test05();
    }


    private  static final String CONST_FILEPATH="";
    private  static final String CONST_FILEPATH_CHINESE="";
    private File const_file =null;
    private File const_fileChinese =null;

    public IoTest() {
        this.const_file =new File(CONST_FILEPATH);
        this.const_fileChinese =new File(CONST_FILEPATH_CHINESE);
    }

    /**
     * 字节流读取文件；单个字符读取
     * @param ChineseFile
     */
    private  static  void test01(boolean ChineseFile){
        IoTest ioTest =new IoTest();
        FileInputStream fis =null;
        try {
            if (true ==ChineseFile){
                fis =new FileInputStream(ioTest.const_fileChinese);
            }
            else {
                fis =new FileInputStream(ioTest.const_file);
            }
            int read =0;
            while((read =fis.read())!=-1){
                log((char)read,false);
            }
        } catch (FileNotFoundException e) {
            log("FileNotFoundException"+e);
        } catch (IOException e) {
            log("IOException"+e);

        }
        finally {
            if (fis!=null){
                try {
                    fis.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 字节流读取文件；数组循环读取
     * @return
     */
    private static byte[] test02(){
        IoTest ioTest =new IoTest();
        FileInputStream fis =null;
        int len =512;
        byte[] buffer =new byte[len];
        try {
            fis =new FileInputStream(ioTest.const_file);
            int read ;
            while ((read =fis.read(buffer,0,len))!=-1){
                log(buffer+"",true,true);
            }
            for (byte b: buffer) {
                if (true == Character.isLetterOrDigit((char)b)||(char)b=='\n'){
                    log((char)b,false,false);
                }
            }
        } catch (FileNotFoundException e) {
            log("FileNotFoundException"+e);
        } catch (IOException e) {
            log("IOException"+e);

        }finally {
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;

    }

    /**
     * 字符流读取
     */
    private static void test03(){
        IoTest ioTest =new IoTest();
        FileReader fr =null;
        try {
            fr = new FileReader(ioTest.const_fileChinese);
            int read =0;
            while ((read =fr.read())!=-1){
                log((char)read,false);
            }
        } catch (FileNotFoundException e) {
            log("FileNotFoundException"+e);
        } catch (IOException e) {
            log("IOException"+e);

        }finally {
            if (fr!=null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 字节流写操作
     */
    private static void test04(){
        String outPath ="";
        FileOutputStream fos =null;
        File file = new File(outPath);
        byte[] buffer = test02();
        try {
            fos = new FileOutputStream(file);
            fos.write(buffer);
        } catch (FileNotFoundException e) {
            log("FileNotFoundException"+e);
        } catch (IOException e) {
            log("IOException"+e);

        }finally {
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void  test05(){
        String outPath ="";
        IoTest ioTest =new IoTest();
        FileReader fr= null;
        FileWriter fw =null;
        try {
            fr =new FileReader(ioTest.const_fileChinese);
            StringBuffer buffer = new StringBuffer();
            int read =0;
            while ((read =fr.read())!=-1){
                log((char)read,false);
                buffer.append((char)read);

            }
            File file =new File(outPath);
            fw =new FileWriter(file);
            fw.write(buffer.toString());
        } catch (FileNotFoundException e) {
           log("FileNotFoundException"+e);
        } catch (IOException e) {
            log("IOException"+e);

        }finally {
            if (fw!=null)
            {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr!=null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 日志打印
     * @param msg 输出信息
     * @param b_wrap 是否换行
     */
    private static void log(Object msg,boolean b_wrap){
        if (true ==b_wrap){
            System.out.println(msg+"");
        }else {
            System.out.println(msg+"");
        }
    }

    /**
     *
     * @param msg
     */
    private static  void log(Object msg){
        log(msg,true,true);
    }

    /**
     *
     * @param msg 输出信息
     * @param b_wrap 是否换行
     * @param out 是否输出
     */
    private static  void log(Object msg,boolean b_wrap,boolean out){
        if (true==out){
            if (true ==b_wrap){
                System.out.println(msg+"");
            }else {
                System.out.println(msg+"");
            }
        }
    }
}
