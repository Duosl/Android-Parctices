package com.duoshilin.week01.IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 字节输入流使用示例
 * Created by duoshilin on 2018/12/17.
 */
public class InputStreamTest {

    /**
     * 读文件
     * @param filePath  文件的路径和文件名
     */
    private static void readFile(String filePath){
        InputStream input = null;
        try {
            input = new FileInputStream(filePath);
            byte[] b = new byte[1024];
            input.read(b);
            System.out.println("文件内容为："+new String(b));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        readFile("classes/src/main/java/com/duoshilin/week01/IO/test.txt");
    }

}
