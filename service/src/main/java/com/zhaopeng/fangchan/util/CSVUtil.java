package com.zhaopeng.fangchan.util;

import com.zhaopeng.fangchan.store.StoreConfig;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;

/**
 * Created by zhaopeng on 2017/9/14.
 */
public class CSVUtil {


    private static final Logger logger = LoggerFactory.getLogger(CSVUtil.class);


    /**
     * @param filePath 文件路径
     * @param content  用逗号分开
     */
    public static void appendContent(String filePath, String content) {
        if(Strings.isNullOrEmpty(content)){
            return;
        }
        try {
            File csv = new File(filePath);
            if (!csv.exists()) {
                csv.getParentFile().mkdirs();
                csv.createNewFile();
            }
            if (csv.length() > StoreConfig.fileSize) {
                //当文件满了之后需要滚动文件
                rollFile(csv);
                csv=new File(filePath);
            }
            OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(csv,true),"GBK");

            BufferedWriter bw = new BufferedWriter(writerStream);
            bw.write(content);
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void rollFile(File file) {


        String name = file.getName();
        String ext = getExtensionName(name);
        String originName = getFileNameNoExt(name);
        File parent = file.getParentFile();
        String childs[] = parent.list();

        int size = childs == null ? 0 : childs.length;
        String newPath = parent.getPath() + File.separator + originName + "_" + size + "." + ext;
        File newFile = new File(newPath);

        try {
            Files.copy(file.toPath(), newFile.toPath());
            file.deleteOnExit();
        } catch (IOException e) {
            logger.error("rollFile error {}", e);
        }
    }

    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    public static String getFileNameNoExt(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public static void main(String args[]) {
        String filePath = "C://heheda//123.txt";
        File file = new File(filePath);

        rollFile(file);
    }

}
