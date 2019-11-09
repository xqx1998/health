package com.xqx.test;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author：xingquanxiang createTime：2019/11/9 14:42
 * description:
 */
public class POITest {
    /**
     * 使用poi读取Excel文件
     */
    @Test
    public void testRead() throws IOException {
        //根据路径读取excel文件
        XSSFWorkbook sheets =
                new XSSFWorkbook("G:\\ProjectCodeOuter\\health\\health_parent\\health_common\\src\\test\\poi.xlsx");
        //获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
        XSSFSheet sheet = sheets.getSheetAt(0);
        //遍历工作表获得行对象
        for (Row row : sheet){
            //遍历行对象获得单元格对象
            for (Cell cell : row) {
                //获取单元格中的值
                System.out.println(cell.getStringCellValue());
            }
        }
    }

    /**
     * 使用poi向Excel文件写入数据
     */
    @Test
    public void testWrite() throws IOException {
        String path = "G:\\ProjectCodeOuter\\health\\health_parent\\health_common\\src\\test\\poiWrite.xlsx";
        //在内存中创建一个Excel对象
        XSSFWorkbook excel = new XSSFWorkbook();
        //创建工作簿
        XSSFSheet sheet = excel.createSheet("计科三剑客");
        //创建行，填充单元格
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("年龄");

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("001");
        row1.createCell(1).setCellValue("倖权祥");
        row1.createCell(2).setCellValue("21");
        XSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("002");
        row2.createCell(1).setCellValue("张亚鑫");
        row2.createCell(2).setCellValue("22");
        XSSFRow row3 = sheet.createRow(3);
        row3.createCell(0).setCellValue("003");
        row3.createCell(1).setCellValue("何永辉11");
        row3.createCell(2).setCellValue("23");
        //创建文件
        //通过输出流将workbook对象下载到磁盘
        FileOutputStream fileOutputStream = FileUtils.openOutputStream(new File(path));
        excel.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        excel.close();
    }

}
