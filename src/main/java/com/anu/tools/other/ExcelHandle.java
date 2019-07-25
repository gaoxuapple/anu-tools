package com.anu.tools.other;

import org.apache.poi.ss.usermodel.*;

import java.io.*;

/**
 * Describe: 使用POI对Excel文件的简单处理操作示例
 * Author: gao.xu
 * Mail: gao.xu@ustcinfo.com
 * Phone: 18355150480
 * Date: 2019年07月23日 16:26
 * Copyright: © 2019.Anu Studio., Ltd. All rights reserved.
 */
public class ExcelHandle {

    /**
     * 工作中的一个例子，对xlsx的简单操作
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        File xlsxFile = new File("E:\\data\\test.xlsx");
        InputStream is = new FileInputStream(xlsxFile);
        Workbook wb = WorkbookFactory.create(is);

        int sheetNum = wb.getNumberOfSheets();
        System.out.println(xlsxFile.getAbsoluteFile() + " 文件有 " + sheetNum + " 个目录");

        Sheet sheet = wb.getSheetAt(0); // 获取第一个工作目录
        int trLength = sheet.getLastRowNum(); // 获取最后一行的行数
        for (int i = 0; i < trLength; i++) {
            Row row = sheet.getRow(i); // 获取一行数据
            if (row == null) {//若行为空，则遍历下一行
                continue;
            }
            if (i == 0) {
                continue;
            }

            // 第5列，下标为4的列 NENAME
            Cell nenameCell = row.getCell(4);
            if (nenameCell.getCellType() == CellType.STRING) {
                String nenameText = nenameCell.getStringCellValue();
                if (!nenameText.equals("")) {
                    String content = matcher(nenameText);
                    nenameCell.setCellValue(content);
                }
            }


            // 第16列，下标为15的列 LOCATEINFO
            Cell locateinfoCell = row.getCell(15);
            if (locateinfoCell.getCellType() == CellType.STRING) {
                String locateinfoText = locateinfoCell.getStringCellValue();
                if (!locateinfoText.equals("")) {
                    String content = matcher(locateinfoText);
                    locateinfoCell.setCellValue(content);
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream("F:\\output.xlsx");
        wb.write(fileOut);
        fileOut.close();

    }


    public static String matcher(String text) throws Exception {
        char[] chinese = new char[text.length()];
        chinese = text.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chinese.length; i++) {
            char c = chinese[i];
            if (String.valueOf(c).matches("[\u4e00-\u9fa5]")) { // 判断是否是汉字
                sb.append((int) c);
            } else if (c >= 65 && c <= 90) { // 判断是否是大写字母
                if (c % 2 == 0) {
                    int cs = c + 1;
                    if (cs > 90) {
                        cs = cs - 26;
                    }
                    sb.append((char) cs);
                } else {
                    int cs = c + 3;
                    if (cs > 90) {
                        cs = cs - 26;
                    }
                    sb.append((char) cs);
                }

            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


}
