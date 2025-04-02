package com.chenze.work.bit.test;

import org.junit.jupiter.api.Test;

import java.io.File;

public class CaseTest {

    @Test
    public void test1(){

        String folderPath = "C:\\Users\\chenze\\Documents\\obsidian"; // 需要统计的文件夹路径
        File folder = new File(folderPath);
        int fileCount = countFiles(folder);
        System.out.println("文件总数: " + fileCount);

    }


    public static int countFiles(File folder) {
        if (folder == null || !folder.exists() || !folder.isDirectory()) {
            return 0;
        }
        File[] files = folder.listFiles();
        if (files == null) {
            return 0;
        }
        int count = 0;
        for (File file : files) {
            if (file.isFile()) {
                count++;
            } else if (file.isDirectory()) {
                count += countFiles(file); // 递归统计子目录
            }
        }
        return count;
    }
}
