package com.zhaopeng.fangchan.util;

import java.io.*;

/**
 * Created by zhaopeng on 2017/9/14.
 */
public class CSVUtil {


    /**
     * @param filePath 文件路径
     * @param content  用逗号分开
     */
    public static void appendContent(String filePath, String content) {
        try {
            File csv = new File(filePath);
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            bw.write(content);
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
