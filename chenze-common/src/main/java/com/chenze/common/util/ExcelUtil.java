package com.chenze.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelWriter;
import com.chenze.common.util.excel.BaseExcelReadTemplate;
import com.chenze.common.util.excel.BaseExcelTemplate;
import com.google.common.collect.Maps;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chenze
 * @create: 2020-06-04 14:27
 */
public class ExcelUtil {

    public static  <T extends BaseExcelReadTemplate> List<T> readExcel(String fileUrl, Class<T> template) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        return readExcel(fileUrl, template,0,1);
    }


    public static <T extends BaseExcelReadTemplate> List<T> readExcel(String fileUrl, Class<T> template, int headerRowIndex, int startRowIndex) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        DataInputStream in = null;
        URL url = new URL(fileUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        in = new DataInputStream(conn.getInputStream());
        return readExcel(in, template,headerRowIndex,startRowIndex);
    }

    public static <T extends BaseExcelReadTemplate> List<T> readExcel(File file, Class<T> template) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        return readExcel(file, template,0,1);
    }

    public static <T extends BaseExcelReadTemplate> List<T> readExcel(File file, Class<T> template, int headerRowIndex, int startRowIndex) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        return readExcel(Files.newInputStream(file.toPath()), template,headerRowIndex,startRowIndex);
    }




    public static synchronized <T extends BaseExcelReadTemplate> List<T> readExcel(InputStream is, Class<T> template, int headerRowIndex, int startRowIndex) throws IllegalAccessException, InstantiationException, IOException, NoSuchMethodException, InvocationTargetException {
        try {
            ExcelReader excelReader = cn.hutool.poi.excel.ExcelUtil.getReader(is);
            BaseExcelReadTemplate instance = template.getConstructor().newInstance();
            excelReader.setHeaderAlias(instance.getHeaderAlias());
            excelReader.setCellEditor(instance.cellEditor());
            return excelReader.read(headerRowIndex, startRowIndex, template);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }


    /**
     * 可一次写入多个sheet，Map中的key对应sheetName
     * @param dataMap
     * @param <T>
     * @return
     */
    public static synchronized <T extends BaseExcelTemplate> byte[] write(Map<String,List<T>> dataMap) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (dataMap == null || dataMap.isEmpty()) {
            return null;
        }

        Set<String> sheetNameSet = dataMap.keySet();

        try (ExcelWriter writer = new ExcelWriter()) {
            int index = 0;
            for (String sheetName : sheetNameSet) {
                if (StrUtil.isNotBlank(sheetName)) {
                    if (index == 0) {
                        writer.renameSheet(index, sheetName);
                    } else {
                        writer.setSheet(sheetName);
                    }
                }
                List<T> dataList = dataMap.get(sheetName);
                if (CollUtil.isNotEmpty(dataList)) {
                    BaseExcelTemplate instance = dataList.get(0).getClass().getConstructor().newInstance();
                    Map<String, String> headerAlias = instance.getHeaderAlias();
                    writer.setHeaderAlias(headerAlias);
                    writer.write(dataList, true);
                    //自适应宽度
                    writer.autoSizeColumnAll();
                }
                index++;
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            writer.flush(os);
            return os.toByteArray();
        }
    }


    public static <T extends BaseExcelTemplate> byte[] write(List<T> dataList) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Map<String, List<T>> dataMap = Maps.newHashMapWithExpectedSize(1);
        dataMap.put("", dataList);
        return write(dataMap);

    }


    public static <T extends BaseExcelTemplate> void write(List<T> dataList, String pathname) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        byte[] bytes = write(dataList);
        FileUtil.writeBytes(bytes, new File(pathname));
    }



}
