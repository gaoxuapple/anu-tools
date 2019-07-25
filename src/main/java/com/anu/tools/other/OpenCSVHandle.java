package com.anu.tools.other;

import com.anu.tools.utlis.UnicodeUtils;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Describe: 使用OpenCSV 对csv文件的简单操作工具示例
 * Author: gao.xu
 * Mail: gao.xu@ustcinfo.com
 * Phone: 18355150480
 * Date: 2019年07月25日 18:38
 * Copyright: © 2019.Anu Studio., Ltd. All rights reserved.
 */
public class OpenCSVHandle {

    /**
     * 读取csv文件内容
     * @throws Exception
     */
    public void CSVReadAll() throws Exception {
        File csv = new File("readerTest.csv");

        CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(csv), "GBK"), ',')).build();
        Iterator<String[]> iterator = csvReader.iterator();
        while (iterator.hasNext()) {
            String[] nextLine = iterator.next();
            System.out.println(String.join(",", nextLine) + "\n");
        }
    }

    /**
     * 向csv文件中写内容  （这里面写内容中如果包含逗号，会被直接转化为 “, 所以这种方法不适用与内容有逗号的）
     * @throws Exception
     */
    public void CSVWriter() throws Exception {
        File csv = new File("writerTest.csv");
        if (!csv.exists()) {
            csv.createNewFile();
        }

        List<String[]> list = new ArrayList<>();
        String s1 = "123";
        for (int i = 0; i < 22; i++) {
            String[] ss = {String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i)};
            list.add(ss);
        }

        CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csv), "GBK"), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER);
        writer.writeNext(new String[]{"#", "#", "#", "#"});
        writer.writeAll(list);
        writer.flush();
        writer.close();
    }


    /**
     * 这是我工作中的一个例子，解析csv文件，将部分字段内容加密后写会另外一个文件中
     * 其中csv文件中每一行的第三个和第四个字段内容中包含逗号，读取的时候没有问题，写会csv文件的时候需要在前后加上双引号
     * @param fromPath
     * @param toPath
     */
    public static void exchangeAlarm(String fromPath, String toPath) {
        String charset = "UTF-8";
        Long index = 0L;
        try (CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(new File(fromPath)), charset))).build()) {
            Iterator<String[]> iterator = csvReader.iterator();
            List<String[]> dataList = new ArrayList<String[]>();
            String [] head = iterator.next();
            File csv = new File(toPath);
            csv.createNewFile();
            File writename = new File(toPath);
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(  new OutputStreamWriter( new FileOutputStream(writename), charset));
            out.write(String.join(",", head) + "\n");
            out.flush();
            while (iterator.hasNext()) {
                index ++;
                String [] nextLine = iterator.next();
                String alarmrgion1 = nextLine[0];
                String after0 =  UnicodeUtils.exchangeChinese(alarmrgion1);
                if (after0.indexOf(",") > 0) {
                    nextLine[0] = "\"" + after0 + "\"";
                } else {
                    nextLine[0] = after0;
                }

                String mainWarning = nextLine[2];
                String after1 = UnicodeUtils.exchangeChinese(mainWarning);
                if (after1.indexOf(",") > 0) {
                    nextLine[2] = "\"" + after1 + "\"";
                } else {
                    nextLine[2] = after1;
                }

                String alarmrgion2 = nextLine[3];
                String after2 = UnicodeUtils.exchangeChinese(alarmrgion2);
                if (after2.indexOf(",") > 0) {
                    nextLine[3] = "\"" + after2 + "\"";
                } else {
                    nextLine[3] = after2;
                }


                System.out.println(String.join(",", nextLine) + "\n");
                out.write(String.join(",", nextLine) + "\n");
                out.flush();
            }
            out.close();
            csvReader.close();
            System.out.println("一共 "+ index + " 条记录");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String pathname = "E:\\data\\test.csv";
        String outputname = "E:\\data\\test-transcode.csv";
        exchangeAlarm(pathname, outputname);
    }



}
