package com.chenze.learning.persontool;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObsidianService {

    public static final String[] titleLevels = {"# ", "## ", "### ", "#### ", "##### ", "###### "};

    public String dirToMindmap(String baseDir, String mindDir){
        File minDirFile = FileUtil.file(baseDir + mindDir);
        if (!minDirFile.exists() || !minDirFile.isDirectory()) {
            return null;
        }

        StringBuilder result = new StringBuilder();

        List<String> mindList = new ArrayList<>(Arrays.asList(mindDir.split("/")).subList(0, mindDir.split("/").length - 1));

        mindMapDeal(result, minDirFile, mindList, 1);
        return result.toString();

    }

    boolean lastFileLoop = false;

    /**
     * 如果file是目录 --------> 以当前file.getName为[标题]进行渲染
     *
     *
     * 如果file是文件 --------> [file.getName](mindDirList + 文件名)
     * @param result
     * @param file
     * @param titleLevel
     */
    public void mindMapDeal(StringBuilder result, File file, List<String> mindDirList, int titleLevel){
        if (titleLevel > 6) {
            return;
        }

        if (!file.exists()) {
            return;
        }

        if (file.isDirectory()) {
            if (lastFileLoop) {
                mindDirList.remove(mindDirList.size() - 1);
            }

            String titleName = file.getName();
            result.append(titleLevels[titleLevel - 1]).append(titleName);
            result.append("\n");

            File[] secondFiles = file.listFiles();
            if (secondFiles == null) {
                return;
            }

            mindDirList.add(file.getName());

            for (File secondFileDir : secondFiles) {
                mindMapDeal(result, secondFileDir, mindDirList, titleLevel + 1);
            }
            lastFileLoop = false;
        }
        // 文件
        else {
            lastFileLoop = true;
            String mdFileName = file.getName();
            String fileExtensionName = FilenameUtils.getExtension(mdFileName);
            if (!fileExtensionName.equalsIgnoreCase("md")) {
                return;
            }

            String link = titleLevels[titleLevel - 1] + "[" + mdFileName.replaceAll(".md", "") + "]";
            String path = ("(" + String.join("/", mindDirList) + "/" + mdFileName + ")").replace(" ", "%20");
            result.append(link).append(path);
            result.append("\n");
        }



    }


}
