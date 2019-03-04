package zwz.im.androidapp.utils;

public class StringUtils {

    /**
     * 检查字符串是否是空对象或空字符串
     */
    public static boolean isNull(String str) {
        if (null == str || "".equals(str) || "null".equalsIgnoreCase(str)) {
            return true;
        } else {
            return false;
        }
    }
}
