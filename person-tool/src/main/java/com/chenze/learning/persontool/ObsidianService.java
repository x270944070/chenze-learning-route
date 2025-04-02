package com.chenze.learning.persontool;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class ObsidianService {

    public static final String[] titleLevels = {"# ", "## ", "### ", "#### ", "##### ", "###### "};

    public String dirToMindmap(String baseDir, String mindDir){
        File minDirFile = FileUtil.file(baseDir + mindDir);
        if (!minDirFile.exists() || !minDirFile.isDirectory()) {
            return null;
        }

        StringBuilder result = new StringBuilder();

        mindMapDeal(result, minDirFile, 1);
        return result.toString();

    }

    /**
     * 如果file是目录 --------> 以当前file.getName为[标题]进行渲染
     *
     *
     * 如果file是文件 --------> [file.getName](mindDir + 文件名)
     * @param result
     * @param file
     * @param titleLevel
     */
    public void mindMapDeal(StringBuilder result, File file, int titleLevel){
        if (titleLevel > 6) {
            return;
        }

        if (!file.exists()) {
            return;
        }

        if (file.isDirectory()) {
            String titleName = file.getName();
            result.append(titleLevels[titleLevel - 1]).append(titleName);
            result.append("\n");

            File[] secondFiles = file.listFiles();
            if (secondFiles == null) {
                return;
            }

            for (File secondFileDir : secondFiles) {
                mindMapDeal(result, secondFileDir, titleLevel + 1);
            }
        }
        // 文件
        else {
            String mdFileName = file.getName();
            String fileExtensionName = FilenameUtils.getExtension(mdFileName);
            if (!fileExtensionName.equalsIgnoreCase("md")) {
                return;
            }

            result.append(titleLevels[titleLevel - 1]).append("[").append(mdFileName).append("]")
                    // file.getAbsolutePath())
                    .append("(").append(file.getAbsolutePath()).append(")");
            result.append("\n");
        }



    }


}
