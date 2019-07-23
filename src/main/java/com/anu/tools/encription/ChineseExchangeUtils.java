package com.anu.tools.encription;

import java.util.HashMap;
import java.util.Map;

/**
 * Describe: 中文字符加密操作
 * Author: gao.xu
 * Mail: gao.xu@ustcinfo.com
 * Phone: 18355150480
 * Date: 2019年07月23日 16:39
 * Copyright: © 2019.Anu Studio., Ltd. All rights reserved.
 */
public class ChineseExchangeUtils {

    private static Map<String, String> encodeMap = new HashMap<String, String>();

    static {
        encodeMap.put("0", "f");
        encodeMap.put("1", "e");
        encodeMap.put("2", "d");
        encodeMap.put("3", "c");
        encodeMap.put("4", "b");
        encodeMap.put("5", "a");
        encodeMap.put("6", "9");
        encodeMap.put("7", "8");
        encodeMap.put("8", "7");
        encodeMap.put("9", "6");
        encodeMap.put("a", "5");
        encodeMap.put("b", "4");
        encodeMap.put("c", "3");
        encodeMap.put("d", "2");
        encodeMap.put("e", "1");
        encodeMap.put("f", "0");

        encodeMap.put("0041", "005a");
        encodeMap.put("0042", "0059");
        encodeMap.put("0043", "0058");
        encodeMap.put("0044", "0057");
        encodeMap.put("0045", "0056");
        encodeMap.put("0046", "0055");
        encodeMap.put("0047", "0054");
        encodeMap.put("0048", "0053");
        encodeMap.put("0049", "0052");
        encodeMap.put("004a", "0051");
        encodeMap.put("004b", "0050");
        encodeMap.put("004c", "004f");
        encodeMap.put("004d", "004e");
        encodeMap.put("004e", "004d");
        encodeMap.put("004f", "004c");
        encodeMap.put("0050", "004b");
        encodeMap.put("0051", "004a");
        encodeMap.put("0052", "0049");
        encodeMap.put("0053", "0048");
        encodeMap.put("0054", "0047");
        encodeMap.put("0055", "0046");
        encodeMap.put("0056", "0045");
        encodeMap.put("0057", "0044");
        encodeMap.put("0058", "0043");
        encodeMap.put("0059", "0042");
        encodeMap.put("005a", "0041");

    }

    public static void main(String[] args) {
        String s = "ADCdf12烟台";
        String tt = exchangeChinese(s);
        System.out.println(tt);
        String ss = exchangeChinese(tt);
        System.out.println(ss);
    }

    /**
     * 汉字编码转化
     * @param dataStr
     * @return
     */
    public static String exchangeChinese(final String dataStr) {
        // 先转成Unicode
        char[] utfBytes = dataStr.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            // 先转成Unicode
            char c = utfBytes[byteIndex];
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }

            // 再将Unicode装成原编码
            char[] chararr = hexB.toCharArray();
            if (String.valueOf(c).matches("[\u4e00-\u9fa5]")) {
                // 如果是汉字，将汉字装成指定的汉字
                String last = String.valueOf(chararr[chararr.length - 1]);
                String encoding = encodeMap.get(last);
                char[] encodChar = encoding.toCharArray();
                chararr[chararr.length - 1] = encodChar[0];
            } else if (String.valueOf(c).matches("[\u0041-\u005a]")) {
                // 如果是大写字母，则将大写字母转化成对应的大写字母
                String encoding =encodeMap.get(hexB);
                chararr =  encoding.toCharArray();
            }

            String charStr = String.valueOf(chararr);
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
        }
        return buffer.toString();
    }

    /**
     * 将汉字转化为对应的Unicode编码
     * @param gbString
     * @return
     */
    public static String encodeUnicode(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        System.out.println("unicodeBytes is: " + unicodeBytes);
        return unicodeBytes;
    }

    /**
     * 将Unicode编码装成对应的汉字
     * @param dataStr
     * @return
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }


}
