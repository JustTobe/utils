package com.example.email.test;

import ch.qos.logback.core.joran.conditional.ElseAction;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class IoTest {

    public static void main(String[] args) {

    File file=new File("C:\\Users\\fate\\Desktop\\运筹学.zip");
    InputStream is=null;
        FileOutputStream op=null;

    int length;
    {
        try {
            byte[] b =new byte[256];
            is = new FileInputStream(file);
            BufferedInputStream bufferedInputStream=new BufferedInputStream(is);
            op=new FileOutputStream(new File("C:\\Users\\fate\\Desktop\\test.zip"));
            long start=System.currentTimeMillis();
            if((length=is.read(b))!=-1){

                op.write(b,0,length);
            }
            long end =System.currentTimeMillis();
            System.out.println("time:"+(end-start));
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }finally {
            try {
                op.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    }

}
