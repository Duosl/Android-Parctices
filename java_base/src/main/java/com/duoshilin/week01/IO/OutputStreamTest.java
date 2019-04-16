package com.duoshilin.week01.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by duoshilin on 2018/12/17.
 */
public class OutputStreamTest {

    /**
     * 写文件操作
     * @param filePath  文件路径+文件名
     * @param content   要写入的文件名
     */
    private static void writeFile(String filePath, String content){
        OutputStream os = null;
        try {
            //1、定义要写入的文件
            os = new FileOutputStream(filePath);

            //2、把要写入的内容转换为byte
            byte[] array = content.getBytes(StandardCharsets.UTF_8);

            //3、写入
            os.write(array);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        writeFile("classes/src/main/java/com/duoshilin/week01/IO/test.txt","hello world！");
    }
}
