package org.dms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 驼峰命名转下划线命名
     * @param camelCase 驼峰标识符
     * @return 下划线标识符
     */
    public static String camelToUnderline(String camelCase) {
        if (isBlank(camelCase))
            return camelCase;
        StringBuilder sb = new StringBuilder();
        for (char c : camelCase.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                if (sb.length() != 0)
                    sb.append('_');
                sb.append((char)(c + 32));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线命名转驼峰命名
     * @param underline 下划线标识符
     * @return 驼峰标识符
     */
    public static String underlineToCamel(String underline) {
        if (isBlank(underline))
            return underline;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < underline.length(); ++i) {
            if (underline.charAt(i) == '_' && i + 1 < underline.length()) {
                sb.append((char)(underline.charAt(++i) - 32));
            } else {
                sb.append(underline.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否为null或全空格
     * @param str 字符串
     * @return 真或假
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 如果字符串不为null或空白，返回字符串，否则返回null
     * @param str 字符串
     * @return null或值
     */
    public static String nullOrValue(String str) {
        return isBlank(str) ? null : str;
    }

    /**
     * 如果字符串为null，返回空字符
     * @param str 字符串
     * @return 返回字符
     */
    public static String emptyOrValue(String str) {
        return str == null ? "" : str;
    }

    /**
     * 根据前缀和起始数字产生编号
     * @param prefix 前缀
     * @param width 编号数字宽度
     * @param start 起始数字
     * @return 生成的编号
     */
    public static String generateCode(String prefix, int width, int start) {
        return prefix + String.format("%0" + width + "d", start);
    }

    /**
     * 根据编码规则产生下一个编码
     * @param code 原编码
     * @param width 编码宽度
     * @param increment 增量
     * @return 下一个编码
     */
    public static String generateNextCode(String code, int width, int increment) {
        if (code == null || isBlank(code)) {
            return null;
        }
        String regex = "^(\\D+)(\\d+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(code);
        String prefix = null;
        int number = 0;
        if (!matcher.find())
            return null;
        prefix = matcher.group(1);
        number = Integer.parseInt(matcher.group(2));
        return generateCode(prefix, width, number + increment);
    }

    /**
     * 首字母大写
     * @param str 字符串
     * @return 首字母大写的字符串
     */
    public static String firstToUpper(String str) {
        if (isBlank(str))
            return str;
        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z')
            chars[0] -= 32;
        return String.valueOf(chars);
    }
}
